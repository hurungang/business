
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "LinkWS", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://mb345.com/WS/linkWS.asmx?wsdl")
public class LinkWS
    extends Service
{

    private final static URL LINKWS_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://mb345.com/WS/linkWS.asmx?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        LINKWS_WSDL_LOCATION = url;
    }

    public LinkWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LinkWS() {
        super(LINKWS_WSDL_LOCATION, new QName("http://tempuri.org/", "LinkWS"));
    }

    /**
     * 
     * @return
     *     returns LinkWSSoap
     */
    @WebEndpoint(name = "LinkWSSoap")
    public LinkWSSoap getLinkWSSoap() {
        return (LinkWSSoap)super.getPort(new QName("http://tempuri.org/", "LinkWSSoap"), LinkWSSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LinkWSSoap
     */
    @WebEndpoint(name = "LinkWSSoap")
    public LinkWSSoap getLinkWSSoap(WebServiceFeature... features) {
        return (LinkWSSoap)super.getPort(new QName("http://tempuri.org/", "LinkWSSoap"), LinkWSSoap.class, features);
    }

}
