package com.pks.learn;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Test;

import com.pankaj.learn.SumRequest;
import com.pankaj.learn.SumResponse;
import com.pankaj.learn.SumWSService;
import com.pankaj.learn.SumWs;

/**
 * @author soni_
 * 
 * 1. Get clientside endpoint instance
 * Outgoing message should have the username token profile. Hence use out interceptors
 *
 */
public class SumWsTest {

	@Test
	public void calculateSum() {
		try {
			SumWSService service = new SumWSService(new URL("http://localhost:8080/sumws/services/sumService?wsdl"));
			SumWs port = service.getSumWsPort();
			Client client = ClientProxy.getClient(port);
			Endpoint endpoint = client.getEndpoint();

			HashMap<String, Object> outProps = new HashMap<>();
			outProps.put(WSHandlerConstants.ACTION, "UsernameToken Encrypt");
			outProps.put(WSHandlerConstants.USER, "myuser");
			outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());
			
			outProps.put(WSHandlerConstants.ENCRYPTION_USER, "myservicekey");
			outProps.put(WSHandlerConstants.ENC_PROP_FILE, "etc/clientKeyStore.properties");
			
			
			
			WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

			endpoint.getOutInterceptors().add(wssOut);
			
			HashMap<String, Object> inProps = new HashMap<>();
			inProps.put(WSHandlerConstants.ACTION, "Encrypt");
			inProps.put(WSHandlerConstants.DEC_PROP_FILE, "etc/clientKeyStore.properties");
			inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());
			WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps);
			endpoint.getInInterceptors().add(wssIn);

			SumRequest sumRequest = new SumRequest();
			sumRequest.setNum1(2);
			sumRequest.setNum2(5);
			SumResponse response = port.calculateSum(sumRequest);
			assertNotNull(response);
			assertEquals(7, response.getResult());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
