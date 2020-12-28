package com.pankaj.learn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.9
 * 2020-12-27T21:45:34.252-06:00
 * Generated source version: 3.1.9
 * 
 */
@WebService(targetNamespace = "http://learn.pankaj.com/", name = "SumWs")
@XmlSeeAlso({ObjectFactory.class})
public interface SumWs {

    @WebMethod
    @RequestWrapper(localName = "calculateSum", targetNamespace = "http://learn.pankaj.com/", className = "com.pankaj.learn.CalculateSum")
    @ResponseWrapper(localName = "calculateSumResponse", targetNamespace = "http://learn.pankaj.com/", className = "com.pankaj.learn.CalculateSumResponse")
    @WebResult(name = "response", targetNamespace = "")
    public com.pankaj.learn.SumResponse calculateSum(
        @WebParam(name = "arg0", targetNamespace = "")
        com.pankaj.learn.SumRequest arg0
    );
}
