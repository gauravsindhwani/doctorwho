package com.econsult.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.econsult.model.Account;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.Corporation_;
import com.econsult.model.ServicePlan;
import com.econsult.model.ServicePlan_;

@Repository
@Transactional
public class AdministrationDao extends AbstractDao{

	public Corporation saveCorporation(Corporation corp){
		entitymanager.persist(corp);
		return corp;
	}
	
	public Corporation getCorporation(long id){
		return entitymanager.find(Corporation.class, id);
	}
	
	public Account getAccount(long id){
		return entitymanager.find(Account.class, id);
	}
	
	public ServicePlan saveServicePlan(ServicePlan servicePlan){
		entitymanager.persist(servicePlan);
		return servicePlan;
	}
	
	public ServicePlan getServicePlan(long id){
		return entitymanager.find(ServicePlan.class, id);
	}
	
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
	
	public Account saveAccount(Account account){
		entitymanager.persist(account);
		return account;
	}
	
	public CorpAccount saveCorpAccount(CorpAccount corpAccount){
		entitymanager.persist(corpAccount);
		return corpAccount;
	}
}
