package com.tekton.service;

import com.tekton.bean.CalculationRequest;
import com.tekton.bean.CalculationResponse;
import com.tekton.bean.ResponseWS;
import com.tekton.client.ExternalPercentageClient;
import com.tekton.exception.NoCachedPercentageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculationService {
    private static final String ENDPOINT = "/api/execute/calculate";
    private static final String SUCCESS_MESSAGE = "success";

    private final ExternalPercentageClient percentageClient;
    private final CallHistoryService historyService;

    public ResponseWS calculate(CalculationRequest request, boolean simulateFailure) {

        double baseSum = request.getNumber1() + request.getNumber2();
        Double percentage;
        boolean success = true;
        String details;

        try {
            percentage = percentageClient.fetchPercentage(simulateFailure);
            log.info("Percentage fetched from external service: {}%", percentage);
        } catch (RuntimeException ex) {
            log.warn("External service unavailable {}. Trying cache", ex.getMessage());
            percentage = percentageClient.getCachedPercentage();
        }

        if (percentage == null) {
            success = false;
            details = "No cached percentage available";
            log.error(details);
            historyService.historyCalls(ENDPOINT, request.toString(), details, null, null, null, success);
            throw new NoCachedPercentageException(details);
        }

        double result = baseSum + (baseSum * percentage / 100);
        CalculationResponse response = new CalculationResponse(baseSum, percentage, result);

        historyService.historyCalls(ENDPOINT, request.toString(), SUCCESS_MESSAGE, baseSum, percentage, result, success);

        return new ResponseWS(0, SUCCESS_MESSAGE, true, response);
    }
}
