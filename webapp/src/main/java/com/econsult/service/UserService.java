package com.econsult.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.econsult.dao.UserDao;
import com.econsult.model.Doctor;
import com.econsult.model.Patient;

@Component
@Path("/econsult/u/")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@GET
	@Path("/doctor/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor getDoctor(@PathParam("Id") long Id){
		return userDao.getDoctor(Id);
	}
	
	@POST
	@Path("/doctor/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public long saveDoctor(Doctor doctor){
		doctor.setUpdatedOnDate(new Date());
		return userDao.saveDoctor(doctor).getId();
	}
	
	
	@POST
	@Path("/patient/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public long savePatient(Patient patient){
		patient.setUpdatedOnDate(new Date());
		return userDao.savePatient(patient).getId();
	}
}
