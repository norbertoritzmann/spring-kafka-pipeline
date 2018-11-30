package com.springkafka.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.springkafka.Binding;
import com.springkafka.OrderEvent;

@Component
@EnableBinding(Processor.class)
public class OrdersEventProcessor {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public KStream<String, Long> process(KStream<String, OrderEvent> events) {
        return events.groupByKey()
            .count(Materialized.as(Binding.SALES_ACCOUNTABILITY))
            .toStream();
    }
}
