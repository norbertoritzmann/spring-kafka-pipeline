package com.springkafka.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.springkafka.Binding;
import com.springkafka.OrderEvent;

@Component
public class OrdersEventProcessor {

    @StreamListener
    @SendTo(Binding.SALES_OUT)
    public KStream<String, Long> process(@Input(Binding.ORDERS_IN) KStream<String, OrderEvent> events) {
        return events.groupByKey()
            .count(Materialized.as(Binding.SALES_ACCOUNTABILITY))
            .toStream();
    }
}
