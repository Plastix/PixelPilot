package com.mygdx.skystorm.event;

import com.mygdx.skystorm.event.events.GameEvent;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Events {

    private static Map<Class, List<MethodInfo>> methodHandlers = new HashMap<Class, List<MethodInfo>>();

    private static class MethodInfo {
        Method method;
        Listener object;
        Parameter param;
        Class type;
        MethodInfo(Listener o, Method m, Parameter[] params){
            method = m;
            object = o;
            if(params.length == 1) {
                param = params[0];
                type = param.getType();
            }else {
                throw new RuntimeException("Methods marked as EventHandlers must accept exactly 1 parameter event");
            }
        }
    }

    /**
     * Scans the given object's class for {@code EventHandler} annotations
     * Adds each annotated method to the map with the eventType's class as its key.
     * @param handler the EventHandler implementation to scan
     */
    public static void register(Listener handler){
        Method[] methods = handler.getClass().getMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(EventHandler.class)) {
                Class c = method.getParameterTypes()[0];
                List<MethodInfo> methodInfos = methodHandlers.get(c);
                if(methodInfos == null)
                    methodInfos = new ArrayList<MethodInfo>();
                methodInfos.add(new MethodInfo(handler, method, method.getParameters()));
                methodHandlers.put(c, methodInfos);
            }
        }
    }

    /**
     * Calls all registered methods which are mapped to the given event.
     * @param event The GameEvent which is to be passed to all handlers
     */
    public static void emit(GameEvent event){
        Class eventType = event.getClass();
        List<MethodInfo> methodInfos = methodHandlers.get(eventType);
        if(methodInfos != null) {
            for (MethodInfo m : methodInfos) {
                try {
                    if (m.param == null) {
                        m.method.invoke(null);
                    } else if (m.type.equals(eventType)) {
                        m.method.invoke(m.object, event);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
