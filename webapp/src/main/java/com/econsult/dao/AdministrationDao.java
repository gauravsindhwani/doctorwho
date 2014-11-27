package com.econsult.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.econsult.model.Account;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.ServicePlan;
import com.econsult.model.ServicePlan_;

@Repository
@Transactional
public class AdministrationDao extends AbstractDao{

	public Corporation saveCorporation(Corporation corp){
		entitymanager.persist(corp);
		return corp;
	}
	
	public ServicePlan saveServicePlan(ServicePlan servicePlan){
		entitymanager.persist(servicePlan);
		return servicePlan;
	}
	
	public ServicePlan getServicePlanByName(String name){
		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<ServicePlan> cq = cb.createQuery(ServicePlan.class);
		Root<ServicePlan> spRoot = cq.from(ServicePlan.class);
		cq.select(cb.construct(ServicePlan.class, spRoot.get(ServicePlan_.id))).where(cb.equal(spRoot.get(ServicePlan_.name), name));
		return entitymanager.createQuery(cq).getSingleResult();
	}
	
	public Account saveAccount(Account account){
		entitymanager.persist(account);
		return account;
	}
	
	public CorpAccount saveCorpAccount(CorpAccount corpAccount){
		entitymanager.persist(corpAccount);
		return corpAccount;
	}
}
