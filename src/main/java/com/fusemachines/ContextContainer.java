package com.fusemachines;

import org.springframework.context.ApplicationContext;
/**
 * 
 * @author aarati
 * <p>It sets and gets application context</p>
 *
 */
public class ContextContainer {
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}
		
	public static void setContext(ApplicationContext context) {
		ContextContainer.context = context;
	}
}
