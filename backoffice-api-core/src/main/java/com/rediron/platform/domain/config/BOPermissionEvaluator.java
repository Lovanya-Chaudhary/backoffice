
package com.rediron.platform.domain.config;

import java.io.Serializable;
import java.util.Map;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Bottleneck for authorizing requests from the REST service.
 *
 * 
 */
@Component
public class BOPermissionEvaluator implements PermissionEvaluator {

	private final Map<Class, PermissionEvaluator> customEvaluators;

	public BOPermissionEvaluator(Map<Class, PermissionEvaluator> customEvaluators) {
		this.customEvaluators = customEvaluators;
	}

	/**
	 * Evaluates the permission required for the currently logged user on the object
	 * passed as argument to the web service
	 *
	 * @param currentlyLoggedInUser this is injected by the framework automatically
	 *                              (null if no user is logged in on the current
	 *                              session)
	 * @param entityConcerned       the object passed as argument to the REST call
	 * @param permissionRequired    a string containing the permission required
	 *                              (e.g. read, write, delete)
	 * @return
	 */
	@Override
	public boolean hasPermission(Authentication currentlyLoggedInUser, Object entityConcerned,
			Object permissionRequired) {
//	try {
//	    for (Class k : customEvaluators.keySet()) {
//		if (k.isAssignableFrom(entityConcerned.getClass())) {
//		    return customEvaluators.get(k).hasPermission(currentlyLoggedInUser, entityConcerned, permissionRequired);
//		}
//	    }
//	    return ((ImsUser) currentlyLoggedInUser.getPrincipal()).getAuthorities().contains(SupportedUserActivity.findByClassAndPermission(entityConcerned.getClass(), (String) permissionRequired));
//	} catch (Exception e) {
//	    return false;
//	}
		return true;
	}

	/**
	 * Evaluates the permission required for the currently logged user on the object
	 * passed as argument to the web service
	 *
	 * @param currentlyLoggedInUser this is injected by the framework automatically
	 *                              (null if no user is logged in on the current
	 *                              session)
	 * @param idOfEntityConcerned   id of the entity passed as argument to the REST
	 *                              call
	 * @param typeOfEntityConcerned class name of entity concerned
	 * @param permissionRequired    a string containing the permission required
	 *                              (e.g. read, write, delete)
	 * @return
	 */
	@Override
	public boolean hasPermission(Authentication currentlyLoggedInUser, Serializable idOfEntityConcerned,
			String typeOfEntityConcerned, Object permissionRequired) {
//	try {
//	    Class clazz = Class.forName(typeOfEntityConcerned);
//	    for (Class k : customEvaluators.keySet()) {
//		if (k.isAssignableFrom(clazz)) {
//		    return customEvaluators.get(k).hasPermission(currentlyLoggedInUser, idOfEntityConcerned, typeOfEntityConcerned, permissionRequired);
//		}
//	    }
//	    return ((ImsUser) currentlyLoggedInUser.getPrincipal()).getAuthorities().contains(SupportedUserActivity.findByClassAndPermission(clazz, (String) permissionRequired));
//	} catch (Exception e) {
//	    return false;
//	}
		return true;
	}

}
