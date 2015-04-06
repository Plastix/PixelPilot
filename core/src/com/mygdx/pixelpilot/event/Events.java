package com.mygdx.pixelpilot.event;

import com.mygdx.pixelpilot.event.events.GameEvent;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Events {

    private static Map<Class, List<MethodInfo>> methodHandlers = new HashMap<Class, List<MethodInfo>>();

    private static class MethodInfo {
        Method method;
        WeakReference<Listener> object;
        Class type;
        MethodInfo(Listener o, Method m, Class t){
            method = m;
            object = new WeakReference<Listener>(o);
            type = t;
        }
    }

    /**
     * Scans the given object's class for {@code EventHandler} annotations
     * Adds each annotated method to the map with the eventType's class as its key.
     * @param handler the EventHandler implementation to scan
     */
    public static void register(Listener handler) {
        Method[] methods = handler.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                Class[] classes = method.getParameterTypes();
                if(classes.length != 1) {
                    throw new RuntimeException("Methods marked as EventHandlers must accept exactly 1 parameter event");
                }
                Class c = classes[0];
                List<MethodInfo> methodInfos = methodHandlers.get(c);
                if (methodInfos == null)
                    methodInfos = new ArrayList<MethodInfo>();
                methodInfos.add(new MethodInfo(handler, method, c));
                methodHandlers.put(c, methodInfos);
            }
        }
    }

    /**
     * Calls all registered methods which are mapped to the given event.
     * @param event The GameEvent which is to be passed to all handlers
     * @param source The object which emitted the event
     */
    public static void emit(GameEvent event, Object source){
        event.setSource(source);
        Class eventType = event.getClass();
        List<MethodInfo> methodInfos = methodHandlers.get(eventType);
        if(methodInfos != null) {
            for (MethodInfo m : methodInfos) {
                try {
                    if (m.type.equals(eventType)) {
                        Listener listener = m.object.get();
                        //Make sure our weak reference exists before invoking
                        if(listener != null) {
                            m.method.invoke(listener, event);
                        }else{
                            //If our reference is gone it means our listener got gc'd
                            //So remove the methodInfo from the list
                            methodInfos.remove(m);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
