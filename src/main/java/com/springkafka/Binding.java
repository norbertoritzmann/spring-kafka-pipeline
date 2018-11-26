package com.springkafka;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Binding {

    String ORDERS_OUT = "orders_out";
    String ORDERS_IN = "orders_in";
    String SALES_ACCOUNTABILITY = "sales_account";
    String SALES_OUT = "sales_out";
    String SALES_IN = "sales_in";

    @Input(ORDERS_IN)
    KStream<String, OrderEvent> pageViewsIn();

    @Output(ORDERS_OUT)
    MessageChannel ordersOut();

    @Output(SALES_OUT)
    KStream<String, Long> salesAnalyticsOut();

    @Input(SALES_IN)
    KTable<String, Long> salesIn();
}
