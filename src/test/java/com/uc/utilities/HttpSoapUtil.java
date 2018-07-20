package com.uc.utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.xmlbeans.XmlFactoryHook;
//import org.w3c.dom.NodeList;
import org.w3c.dom.Document;


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
	            
	                      
	            
	            // basic getElementbyTagName logic
	            SOAPBody sb = soapResponse.getSOAPBody();
	            SOAPBody sb2 = soapResponse.getSOAPBody();
	            System.out.println("sb2: "+sb2);
	            
	            
	            System.out.println("--------NodeList1------");
	            //NodeList nl = sb.getElementsByTagName("ConvertCelsiusToFahernheitResult");
//	            int i = 0;
//	            for (Node n = (Node)nl.item(i); i++<nl.getLength();) {
//					System.out.println(n.getNodeName() + ":"+   n.getValue());
//				}

	            
	            System.out.println("--------NodeList2------");
//	            Node nl2 = sb2.getFirstChild();
//	            
//	            for (Node n = (Node)nl2.item(i); i++<nl2.getLength();) {
//					System.out.println(n.getNodeName() + ":"+   n.getValue());
//				}	            
	            
	            
	            
	            
	            
	            // soap response breakdown
	            
	            //SoapPart
	            System.out.println("--------Breakdown------");
	            SOAPPart soapPart = soapResponse.getSOAPPart();
	            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
	            
	            
	            //SoapEnvelope
	            SOAPBody soapEnvBody = soapEnvelope.getBody();
	            SOAPHeader soapHeader = soapEnvelope.getHeader();
	            
	            
	            
	            //SOAPBody
	            SOAPBody soapBody = soapResponse.getSOAPBody();
	            SOAPFault soapFault = soapEnvBody.getFault();
	            boolean soapFaultPresent = soapEnvBody.hasFault();

	            Iterator iter = soapEnvBody.getChildElements();
	            // this is clunky, need to clean up imports possibly
	            Node resultOuter = (Node) ((org.w3c.dom.Node) iter.next()).getFirstChild();
	            //Node nextChildElement = (Node) ((org.w3c.dom.Node) iter.next()).getChildNodes();
	            
	            
	            System.out.println("soapEnvelope: "+soapEnvelope.toString());
	            System.out.println("soapPart: "+soapPart);
	            System.out.println("soapEnvBody: "+soapEnvBody);
	            System.out.println("soapHeader: "+soapHeader);
	            System.out.println("soapBody: "+soapBody);
	            System.out.println("soapFault: "+soapFault);
	            System.out.println("soapFaultPresent: "+soapFaultPresent);
	            System.out.println("resultOuter: "+resultOuter);
	            //System.out.println("nextChildElement: "+nextChildElement);
	            System.out.println();
	            System.out.println();
	            System.out.println();
	            System.out.println();
	            System.out.println();
	            
	            
            

//	            SOAPElement soapElement = (SoapElement) nodeList.
//	            
	            
	            
	            
	            
	            
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

//
//	    
//	    
	    // REVIEW & BREAKDOWN
	    private static void parseSoapXml() {
	    	//https://stackoverflow.com/questions/44152924/parsing-a-soap-response-using-xpath-java#44162280
	    	try 
	        {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            dbf.setNamespaceAware(true);
	            DocumentBuilder db = dbf.newDocumentBuilder();

	            Document doc = db.parse( new File( "soapResponse.xml" ) );

	            XPathFactory xPathFactory = XPathFactory.newInstance();
	            XPath xpath = xPathFactory.newXPath();
	            javax.xml.namespace.NamespaceContext ns = new javax.xml.namespace.NamespaceContext()
	            {

	                @Override
	                public String getNamespaceURI(String prefix) 
	                {
	                    if ( "soap".equals( prefix ) )
	                    {
	                        return "http://schemas.xmlsoap.org/soap/envelope/";
	                    }
	                    else if ( "xsi".equals( prefix ) )
	                    {
	                        return "http://www.w3.org/2001/XMLSchema-instance";
	                    }
	                    else if ( "xsd".equals( prefix ) )
	                    {
	                        return "http://www.w3.org/2001/XMLSchema";
	                    }
	                    else if ( "xml".equals( prefix ) )
	                    {
	                        return javax.xml.XMLConstants.XML_NS_URI;
	                    }
	                    else if ( "parentns".equals( prefix ) )
	                    {
	                        return "urn:JadeWebServices/NetsuiteCustomer/";
	                    }

	                    return javax.xml.XMLConstants.NULL_NS_URI;
	               }

	               @Override
	               public String getPrefix(String namespaceURI) 
	               {
	                  return null;
	               }

	                @Override
	                public Iterator<?> getPrefixes(String namespaceURI) 
	                {
	                    return null;
	                }

	            };


	             xpath.setNamespaceContext(ns);
	             XPathExpression expr = xpath.compile( "/soap:Envelope/soap:Body/parentns:addParentResponse/parentns:addParentResult/text()" );


	             Object exprEval = expr.evaluate( doc, XPathConstants.STRING );
	             if ( exprEval != null )
	             {
	                System.out.println( "The text of addParentResult is : " + exprEval );
	             }
	        }
	        catch ( Exception e )
	        {
	            e.printStackTrace();
	        }
	     }
	    
	    
	
}
