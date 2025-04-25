package com.tekton.service;

import com.tekton.bean.ResponseWS;
import com.tekton.postgres.entity.CallHistory;
import com.tekton.postgres.repository.CallHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallHistoryService {

    private final CallHistoryRepository repository;

    @Async
    public void historyCalls(String endpoint, String parameters, String details, Double base, Double percentage, Double result, boolean success) {
        CallHistory entity = CallHistory.builder()
                .timestamp(LocalDateTime.now())
                .endpoint(endpoint)
                .parameters(parameters)
                .details(details)
                .base(base)
                .percentage(percentage)
                .result(result)
                .success(success)
                .build();
        repository.save(entity);
        log.info("Saved call history");
    }

    public ResponseWS getHistory(Pageable pageable) {

        Page<CallHistory> page = repository.findAll(pageable);

        if (page.getContent().isEmpty()) {
            return new ResponseWS(1, "no records found", false, null);
        }

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("records", page.getContent());
        data.put("page", page.getNumber());
        data.put("size", page.getSize());
        data.put("totalPages", page.getTotalPages());
        data.put("totalElements", page.getTotalElements());

        return new ResponseWS(0, "success", true, data);
    }
}
