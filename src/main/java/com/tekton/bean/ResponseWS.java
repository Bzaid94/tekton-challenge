package com.tekton.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response defined for all endpoints")
public class ResponseWS {

    @Schema(example = "0")
    private int code;

    @Schema(example = "success")
    private String message;

    @Schema(example = "true")
    private boolean success;

    @Schema(nullable = true, description = "utilized for all endpoints (optional)")
    private Object data;
}
