package com.springkafka.sink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class SalesAccountSink {

    private final Log log = LogFactory.getLog(getClass());

    @StreamListener(Sink.INPUT)
    public void process(KTable<String, Long> counts) {
        counts.toStream()
            .foreach((key, value) -> log.info(key + "=" + value));
    }
}