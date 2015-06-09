package com.unico.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://raja.vemula.com/unico")
public interface UnicoSOAPApplication {

	public abstract int gcd();

	public abstract List<Integer> gcdList();

	public abstract int gcdSum();

}