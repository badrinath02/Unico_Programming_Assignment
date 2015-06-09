package com.unico.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UnicoServiceHelper {
	private final static Logger logger = Logger.getLogger(UnicoServiceHelper.class.getName());
	private static Context cntx;
	public static Map<String, String> envProps = 
	        new HashMap<String, String>();
	
	static{
		try {
			loadEnvProperties();
			cntx = getInitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static String pushMessage(int numOne, int numTwo) throws Exception {
		String response = "FAILED";
		try {
			javax.jms.Connection connection = ((ConnectionFactory)cntx.lookup(envProps.get("unico.jms.connection.factory"))).createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queueReq = (Queue)cntx.lookup(envProps.get("unico.jms.queue.name"));

			TextMessage msg = session.createTextMessage("Look into the message headers for numbers");
			msg.setIntProperty("NumberOne", numOne);
			msg.setIntProperty("NumberTwo", numTwo);

			MessageProducer sender = session.createProducer(queueReq);

			sender.send(msg);
			logger.log(Level.SEVERE, "mesaage sent" + "JMSCorrelationID = '" + msg.getJMSMessageID() + "'");

			if(null != msg.getJMSMessageID()){
				response = "SUCCESS";
			}
			connection.close();
		} catch (JMSException e) {
			throw new Exception("Oops!, error has occured on server"+ e.getMessage());
		} catch (NamingException e) {
			throw new Exception("Oops!, error has occured on server"+ e.getMessage());
		} catch(Exception e){
			throw new Exception("Oops!, error has occured on server"+ e.getMessage());
		}
		return response;
	}

	/**
	 * To get an InitialContext object to look up for the remote objects.
	 * @return InitialContext object to look up for the remote objects.
	 * @throws NamingException
	 */
	private static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, envProps.get("unico.naming.provider.url"));
		Context ctx = new InitialContext(env);
		return ctx;
	}

	/**
	 * To get the connection object from DataSource object.
	 * @return java.sql.Connection
	 * @throws SQLException throws exception if any error occurred
	 */
	private static java.sql.Connection getConnection() throws SQLException {
		java.sql.Connection con = null;
		try {
			DataSource ds = (DataSource)cntx.lookup("jdbc/UnicoDS");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * to get a result set of a query
	 * @param query custom query
	 * @return a result set of custom query
	 * @throws SQLException throws an exception if an error occurs
	 */
	private static ResultSet getResultSet(String query) throws SQLException {
		java.sql.Connection con = getConnection();
		ResultSet rs;
		PreparedStatement st = con.prepareStatement(query);
		rs = st.executeQuery();

		return rs;
	}

	/**
	 * Method to get the number of inserted elements
	 * @return List<Integer>
	 * @throws Exception
	 */
	public static List<Integer> list() throws Exception{
		List<Integer> numbers = new ArrayList<Integer>();
		try {
			ResultSet rs = getResultSet("SELECT NUMBER_ONE, NUMBER_TWO FROM UNICO_NUMBERS ORDER BY CRT_DATE DESC");
			if(rs != null){
				ResultSetMetaData metadata = rs.getMetaData();
				int numberOfColumns = metadata.getColumnCount();
				while(rs.next()){
					int i = 1;
					while(i <= numberOfColumns) {
						numbers.add(rs.getInt(i++));
					}
				}
			}
		} catch (SQLException e) {
			throw new Exception("Oops!, Error occured while fetching the list" + e.getMessage());
		}
		return numbers;
	}

	/**
	 * To fetch the list of GCD numbers ever added to the Queue from database
	 * @return List<Integer>
	 * @throws Exception
	 */
	public static List<Integer> getGcdList() throws Exception{
		List<Integer> numbers = new ArrayList<Integer>();
		try {
			ResultSet rs = getResultSet("SELECT GCD_OF_NUMBERS FROM UNICO_NUMBERS ORDER BY CRT_DATE DESC");
			if(rs != null){
				while(rs.next()){
					numbers.add(rs.getInt("GCD_OF_NUMBERS"));
				}
			}
		} catch (SQLException e) {
			throw new Exception("Oops!, error occured while fetching the GCD list" + e.getMessage());
		}
		return numbers;
	}
	
	 private static void loadEnvProperties(){
	    	String env_home = System.getProperty ("env_home");
	    	InputStream starStream = null;
	    	ResourceBundle starBankResource=null;
	    	try {
				starStream = new FileInputStream(env_home+"/environment.properties");
				starBankResource = new PropertyResourceBundle(starStream);
				
				if (starBankResource != null) {
					Enumeration enumm = starBankResource.getKeys();
					String key = "";
					while(enumm.hasMoreElements()){
						key = (String)enumm.nextElement();
						envProps.put((String)key, (String)starBankResource.getObject(key));
					}    
				}		
			} catch (IOException e) {
			} finally {
				if (starStream != null)
					try {
						starStream.close();
					} catch (Exception close) {
						logger.log(Level.SEVERE,""+close);
					}
			}
	    }
	/**
	 * To get the sum of GCDs
	 * @return Integer sum of GCD
	 * @throws Exception
	 */
	public static Integer sumOfGCDs() throws Exception{
		List<Integer> gcdList = getGcdList();
		Integer totalGCD = 0;
		for(int i = 0; i < gcdList.size(); i++){
			totalGCD+=gcdList.get(i);
		}
		return totalGCD;
	}

	/**
	 * Get the Latest numbers GCD from Database.
	 * @return Integer
	 * @throws Exception
	 */
	public static Integer getGCD() throws Exception{
		List<Integer> gcdList = getGcdList();
		return gcdList.get(0);
	}
}
