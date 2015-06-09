package com.unico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
        portName = "UnicoSOAPApplicationPort",
        serviceName = "UnicoSOAPApplicationService",
        targetNamespace = "http://raja.vemula.com/unico",
        endpointInterface = "com.unico.services.UnicoSOAPApplication")
public class UnicoSOAPApplicationService implements UnicoSOAPApplication {
	private static final Logger logger = Logger.getLogger(UnicoSOAPApplication.class.toString());
	
	/* (non-Javadoc)
	 * @see com.unico.services.UnicoSOAPApplicationInt#gcd()
	 */
	@Override
	public int gcd(){
		int gcd = 0;
		try {
			gcd = UnicoServiceHelper.getGCD();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return gcd;
	}
	
	/* (non-Javadoc)
	 * @see com.unico.services.UnicoSOAPApplicationInt#gcdList()
	 */
	@Override
	public List<Integer> gcdList(){
		List<Integer> gcdList = new ArrayList<Integer>();
		try {
			gcdList = UnicoServiceHelper.getGcdList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return gcdList;
	}
	
	/* (non-Javadoc)
	 * @see com.unico.services.UnicoSOAPApplicationInt#gcdSum()
	 */
	@Override
	public int gcdSum(){
		int someOfGCDs = 0;
		try {
			someOfGCDs = UnicoServiceHelper.sumOfGCDs();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return someOfGCDs;
	}
}
