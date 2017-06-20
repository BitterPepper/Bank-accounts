package com.developer.rest.RestBankAcc;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("operationclient")
public class RestService {
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
	public Response postForm(@FormParam("nameFrom") String nameFrom,
			@FormParam("nameTo") String nameTo,
			@FormParam("amount") BigDecimal amount,
			@FormParam("param") List<String> param,
			@Context ServletContext context) {

	    String oper = param.get(0);
		String errorStr = AccountService.performOperation(nameFrom, nameTo, amount, oper);

		UriBuilder uriBuilder = UriBuilder.fromUri(URI.create(context.getContextPath()));
		uriBuilder.path("/index.jsp");
		uriBuilder.queryParam("errors", errorStr);
	    uriBuilder.queryParam("accounts", AccountService.getAccounts());
	    URI uri = uriBuilder.build();
	    return Response.seeOther(uri).build();
	    }
}