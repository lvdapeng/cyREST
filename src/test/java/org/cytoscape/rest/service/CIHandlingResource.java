package org.cytoscape.rest.service;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cytoscape.ci.CIWrapping;
import org.cytoscape.ci.model.CIError;
import org.cytoscape.rest.internal.CIErrorFactoryImpl;
import org.cytoscape.rest.internal.CIExceptionFactoryImpl;
import org.cytoscape.rest.internal.model.MessageModel;


@Path("/ciresource")
public class CIHandlingResource {
	
	private URI logLocation = URI.create("dummyLog");
	
	@Path("/success")
	@GET    
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public MessageModel success() {
		return new MessageModel("Hello!");
	}
	
	@Path("/successFromEmptyResponse")
	@GET    
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public Response successFromEmptyResponse() {
		return Response.ok().build();
	}
	
	@Path("/successFromEntityResponse")
	@GET    
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public Response successFromEntityResponse() {
		return Response.ok(new MessageModel("Hello!")).build();
	}
	
	@Path("/successFromStringResponse")
	@GET    
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public Response successFromStringResponse() {
		return Response.ok("\"Hello!\"", MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/fail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public String fail() throws Exception {
		throw new Exception("Kaboom.");
	}
	
	@Path("/fail404")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public Response fail404() throws Exception {
		return Response.status(404).entity("String").build();
	}
	
	@Path("/failwithresource")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public String failWithCIError() throws WebApplicationException {
		CIError ciError = new CIErrorFactoryImpl(logLocation).getCIError(500, "urn:cytoscape:ci:ci-wrap-test:v1:fail-with-ci-error:errors:1", "Intentional fail to report with CI Resource.", URI.create("http://www.google.ca"));
		throw new CIExceptionFactoryImpl().getCIException(500, new CIError[]{ciError});
	}
	
	@Path("/failwithresource501")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public String failWithCIError501() throws WebApplicationException {
		CIError ciError = new CIErrorFactoryImpl(logLocation).getCIError(500, "urn:cytoscape:ci:ci-wrap-test:v1:fail-with-ci-error:errors:1", "Intentional fail to report with CI Resource.", URI.create("http://www.google.ca"));
		throw new CIExceptionFactoryImpl().getCIException(501, new CIError[]{ciError});
	}
	
	@Path("/failwithautolinkedresource")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CIWrapping
	public String failWithAutoLinkedCIError() throws WebApplicationException {
		CIError ciError = new CIErrorFactoryImpl(logLocation).getCIError(500, "urn:cytoscape:ci:ci-wrap-test:v1:fail-with-ci-error:errors:1", "Intentional fail to report with CI Resource.");
		throw new CIExceptionFactoryImpl().getCIException(500, new CIError[]{ciError});
	}
	
}
