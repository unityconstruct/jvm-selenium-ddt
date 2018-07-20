package com.uc.utilities;
import java.util.Iterator;

import javax.xml.soap.*;

import org.w3c.dom.NodeList;


public class HttpSoapUtil {

	    // SAAJ - SOAP Client Testing
	    public static void main(String args[]) {
	        /*
	            The example below requests from the Web Service at:
	             http://www.webservicex.net/uszip.asmx?op=GetInfoByCity


	            To call other WS, change the parameters below, which are:
	             - the SOAP Endpoint URL (that is, where the service is responding from)
	             - the SOAP Action

	            Also change the contents of the method createSoapEnvelope() in this class. It constructs
	             the inner part of the SOAP envelope that is actually sent.
	         */
	        
	    	String soapEndpointUrl = "http://192.168.0.81:49294/TemperatureConverter.asmx";
	    	//String soapEndpointUrl = "http://www.webservicex.net/uszip.asmx";
	    	String soapAction = "http://www.unityconstruct.org/ConvertCelsiusToFahernheit";
	        //String soapAction = "http://www.webserviceX.NET/GetInfoByCity";

	        callSoapWebService(soapEndpointUrl, soapAction);
	        
	    }

	    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String myNamespace = "";
	        String myNamespaceURI = "http://www.unityconstruct.org";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

	            /*
	            Constructed SOAP Request Message:
	            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
	                <SOAP-ENV:Header/>
	                <SOAP-ENV:Body>
	                    <myNamespace:GetInfoByCity>
	                        <myNamespace:USCity>New York</myNamespace:USCity>
	                    </myNamespace:GetInfoByCity>
	                </SOAP-ENV:Body>
	            </SOAP-ENV:Envelope>
	            */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        //SOAPElement soapBodyElem = soapBody.addChildElement("GetInfoByCity", myNamespace);
	        SOAPElement soapBodyElem = soapBody.addChildElement("ConvertCelsiusToFahernhe", myNamespace);
	        //SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("USCity", myNamespace);
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Celsius", myNamespace);
	        //soapBodyElem1.addTextNode("New York");
	        soapBodyElem1.addTextNode("100");
	    }

	    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
	        try {
	            // Create SOAP Connection
	            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	            // Send SOAP Message to SOAP Server
	            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

	            // Print the SOAP Response
	            System.out.println("Response SOAP Message:");
	            soapResponse.writeTo(System.out);
	            System.out.println();

	            soapConnection.close();
	            
	            
	            SOAPBody sb = soapResponse.getSOAPBody();
	            NodeList nl = sb.getElementsByTagName("ConvertCelsiusToFahernheitResult");
	            int i = 0;
	            
	            for (Node n = (Node)nl.item(i); i++<nl.getLength();) {
					System.out.println(n.getNodeName() + ":"+   n.getValue());
				}

	            
	        } catch (Exception e) {
	            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
	            e.printStackTrace();
	        }
	    }

	    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();

	        createSoapEnvelope(soapMessage);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", soapAction);

	        soapMessage.saveChanges();

	        /* Print the request message, just for debugging purposes */
	        System.out.println("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println("\n");

	        return soapMessage;
	    }

	
}
