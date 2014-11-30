package com.econsult.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.econsult.model.Corporation;
import com.econsult.model.Corporation_;
import com.econsult.model.ServicePlan;
import com.econsult.model.ServicePlan_;

@Repository
public class AdministrationDao extends AbstractBaseDao{
	
	 
	
	public ServicePlan getServicePlanByName(String name){
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<ServicePlan> cq = cb.createQuery(ServicePlan.class);
		Root<ServicePlan> spRoot = cq.from(ServicePlan.class);
		cq.select(cb.construct(ServicePlan.class, spRoot.get(ServicePlan_.id))).where(cb.equal(spRoot.get(ServicePlan_.name), name));
		return entitymanager.createQuery(cq).getSingleResult();
	}
	
	
	 
	
	public Corporation getCorpByDomain(String domain){
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Corporation> cq = cb.createQuery(Corporation.class);
		Root<Corporation> root = cq.from(Corporation.class);
		cq.select(cb.construct(Corporation.class, root.get(Corporation_.id))).where(cb.equal(root.get(Corporation_.domain), domain));
		return entitymanager.createQuery(cq).getSingleResult();
	}
	
	
	 
	 
	
	public Corporation getCorpAndServiceplanByDomain(String domain){
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Corporation> cq = cb.createQuery(Corporation.class);
		Root<Corporation> root = cq.from(Corporation.class);
		cq.select(cb.construct(Corporation.class, root.get(Corporation_.id), root.get(Corporation_.servicePlan))).where(cb.equal(root.get(Corporation_.domain), domain));
		return entitymanager.createQuery(cq).getSingleResult();
	}

}
