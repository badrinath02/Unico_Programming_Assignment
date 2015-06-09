package com.unico.mdb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Database object to load drivers and perform queries
 */
public class DataService {
	
	private final static Logger logger =  Logger.getLogger(DataService.class.getName());
	private static Context cntx;
	private static DataSource ds;
	public static Map<String, String> envProps = 
	        new HashMap<String, String>();
	
	static{
		try {
			loadEnvProperties();
			cntx = getInitialContext();
			ds = (DataSource)cntx.lookup(envProps.get("unico.datasource.jndi.name"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
   
    /**
     * create Database object
     */
    public DataService() {
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
    

    public static Context getInitialContext() throws NamingException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, envProps.get("unico.naming.provider.url"));
		Context ctx = new InitialContext(env);
		return ctx;
	}
    
    /**
     * to load the database base driver
     * @return a database connection
     * @throws SQLException throws an exception if an error occurs
     */
    public static Connection getConnection() throws SQLException {
    	return ds.getConnection();
    }

    /**
     * to get a result set of a query
     * @param query custom query
     * @return a result set of custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static ResultSet getResultSet(String query) throws SQLException {
        Connection con = getConnection();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        rs = st.executeQuery();

        return rs;
    }

    /**
     * to run an update query such as update, delete
     * @param query custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static void runQuery(String query, Object...args) throws SQLException {
        Connection con = getConnection();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        for(int i = 0; i < args.length; i++){
        	st.setObject(i+1, args[i]);
        }
        st.executeUpdate();
    }
}
