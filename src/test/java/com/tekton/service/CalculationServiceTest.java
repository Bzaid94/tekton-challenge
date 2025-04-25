package com.tekton.service;

import com.tekton.bean.CalculationRequest;
import com.tekton.bean.CalculationResponse;
import com.tekton.bean.ResponseWS;
import com.tekton.client.ExternalPercentageClient;
import com.tekton.exception.NoCachedPercentageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

    @Mock
    private ExternalPercentageClient percentageClient;

    @Mock
    private CallHistoryService historyService;

    @InjectMocks
    private CalculationService calculationService;

    private CalculationRequest request;

    @BeforeEach
    void setUp() {
        request = new CalculationRequest();
        request.setNumber1(100.0);
        request.setNumber2(50.0);
    }

    @Test
    void calculate_WhenExternalServiceAvailable_ReturnsExpectedResult() {
        when(percentageClient.fetchPercentage(false)).thenReturn(10.0);

        ResponseWS response = calculationService.calculate(request, false);

        CalculationResponse data = (CalculationResponse) response.getData();
        assertEquals(165.0, data.getResult());

        verify(historyService).historyCalls(
                eq("/api/execute/calculate"), eq(request.toString()), eq("success"),
                eq(150.0), eq(10.0), eq(165.0), eq(true));
    }

    @Test
    void calculate_WhenExternalFails_UsesCachedPercentage() {
        when(percentageClient.fetchPercentage(true))
                .thenThrow(new RuntimeException("simulated"));
        when(percentageClient.getCachedPercentage()).thenReturn(5.0);

        ResponseWS response = calculationService.calculate(request, true);

        CalculationResponse data = (CalculationResponse) response.getData();
        assertEquals(157.5, data.getResult());
    }


    @Test
    void calculate_WhenNoCachedPercentage_ThrowsException() {
        when(percentageClient.fetchPercentage(true))
                .thenThrow(new RuntimeException("simulated"));
        when(percentageClient.getCachedPercentage()).thenReturn(null);

        assertThrows(NoCachedPercentageException.class,
                () -> calculationService.calculate(request, true));

        verify(historyService).historyCalls(
                eq("/api/execute/calculate"), eq(request.toString()),
                eq("No cached percentage available"),
                isNull(),
                isNull(),
                isNull(),
                eq(false));
    }
}
