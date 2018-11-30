package com.springkafka.source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springkafka.Binding;
import com.springkafka.OrderEvent;

@RestController
@RequestMapping("order")
@EnableBinding(Source.class)
public class OrdersEventSource {

    private final MessageChannel ordersOut;
    private final Log log = LogFactory.getLog(getClass());

    public OrdersEventSource(Binding binding) {
        this.ordersOut = binding.ordersOut();
    }

    @PostMapping
    @SendTo(Source.OUTPUT)
    public OrderEvent save(@RequestBody OrderEvent order) {
        log.info("Order recorded: ");

        Message<OrderEvent> message = MessageBuilder.withPayload(order)
                .setHeader(KafkaHeaders.MESSAGE_KEY, order.getProductId())
                .build();
        try {
            this.ordersOut.send(message);
            log.info("sent " + message.toString());
        } catch (Exception e) {
            log.error(e);
        }

        return order;
    }

//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        List<String> names = Arrays.asList("mfisher", "dyser", "schacko", "abilan", "ozhurakousky", "grussell");
//        List<String> pages = Arrays.asList("blog", "sitemap", "initializr", "news", "colophon", "about");
//        Runnable runnable = () -> {
//            String rPage = pages.get(new Random().nextInt(pages.size()));
//            String rName = pages.get(new Random().nextInt(names.size()));
//            PageViewEvent pageViewEvent = new PageViewEvent(rName, rPage, Math.random() > .5 ? 10 : 1000);
//            Message<PageViewEvent> message = MessageBuilder.withPayload(pageViewEvent)
//                .setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getUserId()
//                    .getBytes())
//                .build();
//            try {
//                this.pageViewsOut.send(message);
//                log.info("sent " + message.toString());
//            } catch (Exception e) {
//                log.error(e);
//            }
//        };
//        Executors.newScheduledThreadPool(1)
//            .scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
//    }
}