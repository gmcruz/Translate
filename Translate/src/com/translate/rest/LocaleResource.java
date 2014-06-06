package com.translate.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.translate.LocaleManagerServiceLocal;
import com.translate.domain.Locale;


@Path("/locales")
@Stateless
public class LocaleResource {
	
	static Logger logger = Logger.getLogger(LocaleResource.class);
	
	@EJB
	private LocaleManagerServiceLocal localeService;
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/locale/{id: \\d+}")
	public Locale getLocaleByIdXmlJson(@PathParam("id") int id){
		return localeService.getLocaleById(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Locale> getAllLocales(){
		return localeService.getAllLocales();		
	}

	
	
}
