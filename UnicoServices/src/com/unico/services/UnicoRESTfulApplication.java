package com.unico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/unico/restful")
public class UnicoRESTfulApplication {
	private final static Logger logger = Logger.getLogger(UnicoRESTfulApplication.class.getName());
	
	  @GET
	  @Path("/{n1}/{n2}")
	  public String push(@PathParam("n1") int num1, @PathParam("n2") int num2) {
		 String STATUS = "FAILED";
		 try{
			 STATUS = UnicoServiceHelper.pushMessage(num1, num2);
		 }catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return STATUS;
	  }
	  
	  @GET
	  @Path("/list")
	  @Produces(MediaType.APPLICATION_JSON)
	  public JSONObject list(){
		  List<Integer> list = new ArrayList<Integer>();
		  try {
			  list = UnicoServiceHelper.list();
		  } catch (Exception e1) {
			  logger.log(Level.SEVERE, "Error has occured while fetching the list of numbers from service." + e1.getMessage());
		  }
		  JSONObject jsonObject = new JSONObject();
		  try {
			  jsonObject.put("elements", list);
		  } catch (JSONException e) {
			  logger.log(Level.SEVERE, "Error has occured while preparing the JSON object.");
		  }
		  return jsonObject;
	  }
}
