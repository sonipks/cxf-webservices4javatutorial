package com.pankaj.learn;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.pankaj.learn.dto.SumRequest;
import com.pankaj.learn.dto.SumResponse;

@WebService(name = "SumWs")
public interface SumWS {
	
	@WebResult(name = "response") SumResponse calculateSum(@WebParam SumRequest request);

}
