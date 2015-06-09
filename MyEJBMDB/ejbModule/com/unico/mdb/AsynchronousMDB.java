package com.unico.mdb;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Message-Driven Bean implementation class for: AsynchronousMDB
 *
 */
@MessageDriven(mappedName = "jms/UnicoJMSQueue", name = "AsynchronousMDB",
		activationConfig = {
			@ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		})
public class AsynchronousMDB implements MessageListener {
	private final static Logger logger = Logger.getLogger(AsynchronousMDB.class.getName());
    /**
     * Default constructor. 
     */
    public AsynchronousMDB() {
    	logger.log(Level.SEVERE, "AsynchronousMDB obj instantiated .... ");
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			TextMessage msg = (TextMessage)message;
			if(null != msg){
				logger.log(Level.SEVERE, msg.getIntProperty("NumberOne") + " " + msg.getIntProperty("NumberTwo") + "\t" + msg.getText());
				BigInteger b1 = BigInteger.valueOf(msg.getIntProperty("NumberOne"));
				BigInteger b2 = BigInteger.valueOf(msg.getIntProperty("NumberTwo"));
				BigInteger gcd = b1.gcd(b2);
				logger.log(Level.SEVERE, "The GCD is => " + gcd.intValue());
				
				try {
					DataService.runQuery("INSERT INTO UNICO_NUMBERS VALUES(?, ?, ?, SYSDATE)", b1.intValue(), b2.intValue(), gcd.intValue());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
    }
}
