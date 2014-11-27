package com.econsult.eventlisteners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

public class AuditEventListener implements SaveOrUpdateEventListener {

	
	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event)
			throws HibernateException {
		System.out.println("here");
		
	}

}
