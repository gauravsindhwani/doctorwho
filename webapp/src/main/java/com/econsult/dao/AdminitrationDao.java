package com.econsult.dao;

import com.econsult.model.Account;
import com.econsult.model.CorpAccount;
import com.econsult.model.Corporation;
import com.econsult.model.ServicePlan;

public interface AdminitrationDao {

	public abstract Corporation saveCorporation(Corporation corp);

	public abstract Corporation getCorporation(long id);

	public abstract Account getAccount(long id);

	public abstract ServicePlan saveServicePlan(ServicePlan servicePlan);

	public abstract ServicePlan getServicePlan(long id);

	public abstract ServicePlan getServicePlanByName(String name);

	public abstract Corporation getCorpByDomain(String domain);

	public abstract Corporation getCorpAndServiceplanByDomain(String domain);

	public abstract Account saveAccount(Account account);

	public abstract CorpAccount saveCorpAccount(CorpAccount corpAccount);

}