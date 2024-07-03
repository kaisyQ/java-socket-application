package com.kaisyq.framework.core.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kaisyq.framework.core.di.enums.InstanceLifetime;

/**
 * @TODO
 * Добавить возможность создавать классы как Singleton и обычно
 * 
 * 
 * Также надо подумать, как сделать DependecyInjectionConfig - класс, в котором будут прописаны packages
 * классы которых нужно будет идексировать DI
 * 
 * 
 * В идеале далее добавить кастомную аннотацию, для более четкого определния индексации классов 
 */

public final class DependencyInjectionContainer {

    private static DependencyInjectionContainer instance;

    private final Map<Class<?>, Class<?>> container;

    private final Map<Class<?>, Class<?>> classInstances;

	private DependencyInjectionContainer() {
		this.container = new HashMap<Class<?>, Class<?>>();
        this.classInstances = new HashMap<Class<?>, Class<?>>();
	}

    public<T, V extends T> void bind(Class<T> abstractionClass, Class<V> realizationClass, InstanceLifetime lifetime) {
		container.put(abstractionClass, realizationClass);
    }

    @SuppressWarnings("unchecked")
    public<T> T get(Class<T> classVariable) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        @SuppressWarnings("rawtypes")
        Constructor constructor = null;

        Class<?> containerClass = this.container.get(classVariable);
        ArrayList<Object> contstructrorArguments = new ArrayList<Object>();

        /**
         * @TODO
         * исправить костыль, надо получать не нулевой, а непустой конструктор
         * плюс дополнительно надо добавить проверку, на то, нужно ли вообще подкидывать зависимости
         */
        if (containerClass == null) {
            constructor = classVariable.getConstructors()[0];
        } else {
            constructor = containerClass.getConstructors()[0];
        }

        Class<?>[] parameters = constructor.getParameterTypes();

        for (Class<?> parameter: parameters) {
            contstructrorArguments.add((Object)get(parameter));
        }

        constructor.setAccessible(true);

        return (T)constructor.newInstance(contstructrorArguments.toArray());
    }
    
    public Map<Class<?>, Class<?>> getContainer() {
        return this.container;
    }

    public static DependencyInjectionContainer build() {

        if (instance == null) {
            instance = new DependencyInjectionContainer();
        }

        return instance;
    }
}
