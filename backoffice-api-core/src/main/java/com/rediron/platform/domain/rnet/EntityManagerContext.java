package com.rediron.platform.domain.rnet;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lovanya.chaudhary
 *
 */
public class EntityManagerContext extends ThreadLocal<EntityManagerContext> {
	private static final EntityManagerContext context = new EntityManagerContext();

	private String auditUser;
	private Map<Object, Object> map = new HashMap<Object, Object>();

	@Override
	protected EntityManagerContext initialValue() {
		return new EntityManagerContext();
	}

	public static Map<Object, Object> map() {
		return context.get().map;
	}

	public static String getAuditUser() {
		return context.get().auditUser;
	}

	public static void setAuditUser(String auditUser) {
		context.get().auditUser = auditUser;
	}
}
