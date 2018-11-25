package com.springkafka.source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springkafka.Binding;
import com.springkafka.PageViewEvent;

@RestController
public class PageViewEventSource {

    private final MessageChannel pageViewsOut;
    private final Log log = LogFactory.getLog(getClass());

    public PageViewEventSource(Binding binding) {
        this.pageViewsOut = binding.pageViewsOut();
    }
    
    @PostMapping
    public PageViewEvent save(@RequestBody PageViewEvent pageViewEvent) {
        
        
        return pageViewEvent;
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