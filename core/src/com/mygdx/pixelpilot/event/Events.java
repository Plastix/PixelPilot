package com.mygdx.pixelpilot.event;

import com.mygdx.pixelpilot.event.events.GameEvent;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.common.Properties;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;

public class Events {

    private static MBassador<GameEvent> bus;

    public static MBassador<GameEvent> getBus() {
        if(bus == null){
            BusConfiguration config = new BusConfiguration();
            config.setProperty(Properties.Common.Id, "Game Event Bus");
            config.setProperty(Properties.Handler.PublicationError, new IPublicationErrorHandler() {
                @Override
                public void handleError(PublicationError error) {
                    System.out.println(error);
                }
            });
            config.addFeature(Feature.SyncPubSub.Default());
            config.addFeature(Feature.AsynchronousHandlerInvocation.Default());
            config.addFeature(Feature.AsynchronousMessageDispatch.Default());
            bus = new MBassador<GameEvent>(config);
        }
        return bus;
    }

}
