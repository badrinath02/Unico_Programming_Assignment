
package com.unico.test.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "UnicoSOAPApplication", targetNamespace = "http://raja.vemula.com/unico")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UnicoSOAPApplication {


    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "gcd", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.Gcd")
    @ResponseWrapper(localName = "gcdResponse", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.GcdResponse")
    public int gcd();

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.Integer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "gcdList", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.GcdList")
    @ResponseWrapper(localName = "gcdListResponse", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.GcdListResponse")
    public List<Integer> gcdList();

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "gcdSum", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.GcdSum")
    @ResponseWrapper(localName = "gcdSumResponse", targetNamespace = "http://raja.vemula.com/unico", className = "com.vemula.raja.unico.GcdSumResponse")
    public int gcdSum();

}