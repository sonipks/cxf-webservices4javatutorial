package com.pankaj.learn;

import com.pankaj.learn.dto.SumRequest;
import com.pankaj.learn.dto.SumResponse;

public class SumWSImpl implements SumWS {

	@Override
	public SumResponse calculateSum(SumRequest request) {
		int result=request.getNum1()+request.getNum2();
		SumResponse response = new SumResponse();
		response.setResult(result);
		return response;
	}

}
