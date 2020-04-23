package org.jeecg.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ApiContext {
    private static final String KEY_CURRENT_PROVIDER_ID = "KEY_CURRENT_PROVIDER_ID";
    private static final Map<String, Object> mContext = Maps.newConcurrentMap();

    public void setCurrentProviderId(Long providerId) {
        mContext.put(KEY_CURRENT_PROVIDER_ID, providerId);
    }

    public Long getCurrentProviderId() {
        log.info("当前租户ID是：" + KEY_CURRENT_PROVIDER_ID);
        return (Long) mContext.get(KEY_CURRENT_PROVIDER_ID);
    }
}