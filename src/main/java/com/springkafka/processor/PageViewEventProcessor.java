package com.springkafka.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.springkafka.Binding;
import com.springkafka.PageViewEvent;

@Component
public class PageViewEventProcessor {

    @StreamListener
    @SendTo(Binding.PAGE_COUNT_OUT)
    public KStream<String, Long> process(@Input(Binding.PAGE_VIEWS_IN) KStream<String, PageViewEvent> events) {
        return events.filter((key, value) -> value.getDuration() > 10)
            .map((key, value) -> new KeyValue<>(value.getPage(), "0"))
            .groupByKey()
            .count(Materialized.as(Binding.PAGE_COUNT_MV))
            .toStream();
    }
}
