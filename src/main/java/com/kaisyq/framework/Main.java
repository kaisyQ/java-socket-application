package com.kaisyq.framework;


import java.lang.reflect.InvocationTargetException;

import com.kaisyq.framework.core.di.DependencyInjectionContainer;
import com.kaisyq.framework.core.di.enums.InstanceLifetime;
import com.kaisyq.framework.core.http.server.Server;
import com.kaisyq.framework.core.http.server.exceptions.ServerRunningException;


class Main {
	public static void main(final String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ServerRunningException {

		var container = DependencyInjectionContainer.build();

		var server = new Server();

		server.build();
		
	}
}
	

