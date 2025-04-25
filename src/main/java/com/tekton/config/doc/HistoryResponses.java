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
                description  = "Call history",
                content = @Content(
                        mediaType = "application/json",
                        schema    = @Schema(implementation = ResponseWS.class),
                        examples  = @ExampleObject(name = "success", value = OpenApiExample.HISTORY_SUCCESS)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description  = "No records found",
                content = @Content(
                        mediaType = "application/json",
                        schema    = @Schema(implementation = ResponseWS.class),
                        examples  = @ExampleObject(name = "error", value = OpenApiExample.HISTORY_ERROR)
                )
        ),
        @ApiResponse(
                responseCode = "503",
                description  = "Database error",
                content = @Content(
                        mediaType = "application/json",
                        schema    = @Schema(implementation = ResponseWS.class),
                        examples  = @ExampleObject(name = "error", value = OpenApiExample.DB_ERROR)
                )
        )
})
public @interface HistoryResponses {
}
