package com.logate.summer.configurations;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CacheLoggerConfig implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        log.info("Type u cache:{}, key u cache: {}, oldValue u  cache: {}, newValue u cache: {}",
                cacheEvent.getType(),cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}