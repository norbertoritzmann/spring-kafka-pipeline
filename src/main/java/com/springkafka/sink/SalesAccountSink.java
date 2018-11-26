package com.springkafka.sink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.springkafka.Binding;

@Component
public class SalesAccountSink {

    private final Log log = LogFactory.getLog(getClass());

    @StreamListener
    public void process(@Input((Binding.SALES_IN)) KTable<String, Long> counts) {
        counts.toStream()
            .foreach((key, value) -> log.info(key + "=" + value));
    }
}