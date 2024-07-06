package com.kaisyq.framework;


import java.lang.reflect.InvocationTargetException;

import com.kaisyq.framework.core.di.DependencyInjectionContainer;
import com.kaisyq.framework.core.http.core.server.HttpServer;


class Main {
	public static void main(final String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		var container = DependencyInjectionContainer.build();

		HttpServer.build(8000).run();
	}
}
	

