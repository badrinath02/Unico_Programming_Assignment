package com.unico.test.client;

import java.util.Random;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WorkerThread implements Runnable {
	
	private String command;
    
    public WorkerThread(String s){
        this.command=s;
    }

    
    private void invokeSOAPService(){
    	UnicoSOAPApplication application = new UnicoSOAPApplicationService().getUnicoSOAPApplicationPort();
    	System.out.println("Result from SOAP GCD == " + application.gcd());
    	System.out.println("Result from SOAP GCD SUM == " + application.gcdSum());
    	System.out.println("Result from SOAP GCD LIST == " + application.gcdList());
    }
	private void processCommand(int one, int two) {
        try {
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource service = client.resource("http://localhost:7001/unico/rest/");
            System.out.println(service.path("unico").path("restful").path(one+"").path(two+"").accept(MediaType.TEXT_HTML).get(String.class));
            invokeSOAPService();
            System.out.println(service.path("unico").path("restful").path("list").accept(MediaType.APPLICATION_JSON).get(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	 @Override
	 public void run() {
		 System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
		 processCommand((new Random()).nextInt(1000), (new Random()).nextInt(1000));
		 System.out.println(Thread.currentThread().getName()+" End.");
	 }
	
	 @Override
	 public String toString(){
		 return this.command;
	 }
}
