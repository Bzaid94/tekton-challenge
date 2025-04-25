package com.tekton.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    @Schema(example = "false")
    private boolean status;

    @Schema(example = "No cached percentage")
    private String message;

    @Schema(example = "No cached percentage available")
    private String error;
}
