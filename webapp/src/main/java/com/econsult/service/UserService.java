package com.econsult.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.econsult.model.Doctor;
import com.econsult.model.MedicalInfo;
import com.econsult.model.Patient;
import com.googlecode.genericdao.dao.jpa.GeneralDAO;

@Component
@Path("/u/")
@Transactional
public class UserService {
	
	@Autowired
	private GeneralDAO generalDao;
	
	@GET
	@Path("/doctor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Doctor getDoctor(@PathParam("id") long id){
		return generalDao.find(Doctor.class, id);
	}
	
	@GET
	@Path("/patient/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Patient getPatient(@PathParam("id") long id){
		return generalDao.find(Patient.class, id);
	}
	
	@POST
	@Path("/doctor/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public long saveDoctor(Doctor doctor){
		return generalDao.save(doctor).getId();
	}
	
	
	@POST
	@Path("/patient/")
	@Consumes(MediaType.APPLICATION_JSON)
	public long savePatient(Patient patient){
		return generalDao.save(patient).getId();
	}
	
	@PUT
	@Path("/patient/")
	@Consumes(MediaType.APPLICATION_JSON)
	public long updatePatient(Patient patient){
		return generalDao.merge(patient).getId();
	}
	
	
	
	
	@POST
	@Path("/medicalinfo/")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveMedicalInfo(MedicalInfo medicalInfo){
		generalDao.save(medicalInfo);
		return true;
	}
	
	@PUT
	@Path("/medicalinfo/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateMedicalInfo(MedicalInfo medicalInfo){
		generalDao.merge(medicalInfo);
		return true;
	}
	
	
	@GET
	@Path("/medicalinfo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MedicalInfo getMedicalInfo(@PathParam("id") long id){
		return generalDao.find(MedicalInfo.class, id);
	}
}
