package com.tekton.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationResponse {
    private Double base;
    private double percentage;
    private Double result;
}
