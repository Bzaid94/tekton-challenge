package com.tekton.client;

import com.tekton.exception.ExternalServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExternalPercentageClient {

    @Cacheable(value = "percentage", key = "'percentage'", unless = "#result == null")
    public Double fetchPercentage(boolean simulateFailure) {
        if (simulateFailure) {
            throw new ExternalServiceUnavailableException("External service unavailable (simulated)");
        }
        log.info("Fetching percentage from external service");
        return 10.0;
    }

    @Cacheable(value = "percentage", key = "'percentage'", unless = "#result == null")
    public Double getCachedPercentage() {
        log.debug("Reading percentage from cache (no external call).");
        return null;
    }
}
