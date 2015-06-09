
package com.unico.test.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vemula.raja.unico package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GcdSumResponse_QNAME = new QName("http://raja.vemula.com/unico", "gcdSumResponse");
    private final static QName _Gcd_QNAME = new QName("http://raja.vemula.com/unico", "gcd");
    private final static QName _GcdList_QNAME = new QName("http://raja.vemula.com/unico", "gcdList");
    private final static QName _GcdResponse_QNAME = new QName("http://raja.vemula.com/unico", "gcdResponse");
    private final static QName _GcdListResponse_QNAME = new QName("http://raja.vemula.com/unico", "gcdListResponse");
    private final static QName _GcdSum_QNAME = new QName("http://raja.vemula.com/unico", "gcdSum");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vemula.raja.unico
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GcdList }
     * 
     */
    public GcdList createGcdList() {
        return new GcdList();
    }

    /**
     * Create an instance of {@link GcdListResponse }
     * 
     */
    public GcdListResponse createGcdListResponse() {
        return new GcdListResponse();
    }

    /**
     * Create an instance of {@link GcdResponse }
     * 
     */
    public GcdResponse createGcdResponse() {
        return new GcdResponse();
    }

    /**
     * Create an instance of {@link GcdSumResponse }
     * 
     */
    public GcdSumResponse createGcdSumResponse() {
        return new GcdSumResponse();
    }

    /**
     * Create an instance of {@link GcdSum }
     * 
     */
    public GcdSum createGcdSum() {
        return new GcdSum();
    }

    /**
     * Create an instance of {@link Gcd }
     * 
     */
    public Gcd createGcd() {
        return new Gcd();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GcdSumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcdSumResponse")
    public JAXBElement<GcdSumResponse> createGcdSumResponse(GcdSumResponse value) {
        return new JAXBElement<GcdSumResponse>(_GcdSumResponse_QNAME, GcdSumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Gcd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcd")
    public JAXBElement<Gcd> createGcd(Gcd value) {
        return new JAXBElement<Gcd>(_Gcd_QNAME, Gcd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GcdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcdList")
    public JAXBElement<GcdList> createGcdList(GcdList value) {
        return new JAXBElement<GcdList>(_GcdList_QNAME, GcdList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GcdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcdResponse")
    public JAXBElement<GcdResponse> createGcdResponse(GcdResponse value) {
        return new JAXBElement<GcdResponse>(_GcdResponse_QNAME, GcdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GcdListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcdListResponse")
    public JAXBElement<GcdListResponse> createGcdListResponse(GcdListResponse value) {
        return new JAXBElement<GcdListResponse>(_GcdListResponse_QNAME, GcdListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GcdSum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://raja.vemula.com/unico", name = "gcdSum")
    public JAXBElement<GcdSum> createGcdSum(GcdSum value) {
        return new JAXBElement<GcdSum>(_GcdSum_QNAME, GcdSum.class, null, value);
    }

}
