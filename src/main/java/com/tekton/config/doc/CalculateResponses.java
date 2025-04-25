package com.tekton.config.doc;

import com.tekton.bean.ResponseWS;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description  = "Calculate percentage successfully",
                content = @Content(
                        mediaType = "application/json",
                        schema    = @Schema(implementation = ResponseWS.class),
                        examples  = @ExampleObject(name = "success", value = OpenApiExample.CALC_SUCCESS)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description  = "No cached percentage available",
                content = @Content(
                        mediaType = "application/json",
                        schema    = @Schema(implementation = ResponseWS.class),
                        examples  = @ExampleObject(name = "error", value = OpenApiExample.CALC_ERROR)
                )
        )
})
public @interface CalculateResponses {
}
