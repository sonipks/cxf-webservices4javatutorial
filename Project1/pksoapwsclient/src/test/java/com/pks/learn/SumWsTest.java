package com.pks.learn;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.pankaj.learn.SumRequest;
import com.pankaj.learn.SumResponse;
import com.pankaj.learn.SumWSService;
import com.pankaj.learn.SumWs;

public class SumWsTest {

	@Test
	public void calculateSum() {
		try {
			SumWSService service = new SumWSService(new URL("http://localhost:8080/sumws/services/sumService?wsdl"));
			SumWs port = service.getSumWsPort();
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
