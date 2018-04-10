package com.s63d.location.event;

import net.reini.rabbitmq.cdi.EventBinder;

import javax.enterprise.context.Dependent;

@Dependent
public class RabbitBinder extends EventBinder {

    @Override
    protected void bindEvents() {
        bind(LocationEvent.class)
                .toQueue("proftaak").autoAck();
    }

}
