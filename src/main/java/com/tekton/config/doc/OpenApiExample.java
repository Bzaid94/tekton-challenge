package com.tekton.config.doc;

public class OpenApiExample {
    private OpenApiExample() { }

    public static final String CALC_SUCCESS = """
        {
          "code": 0,
          "message": "success",
          "success": true,
          "data": {
            "base": 150.0,
            "percentage": 10.0,
            "result": 165.0
          }
        }
        """;

    public static final String CALC_ERROR = """
        {
          "code": 1,
          "message": "No cached percentage available",
          "success": false,
          "data": null
        }
        """;

    public static final String HISTORY_SUCCESS = """
        {
          "code": 0,
          "message": "success",
          "success": true,
          "data": {
            "records": [],
            "page": 0,
            "size": 20,
            "totalPages": 1,
            "totalElements": 0,
            "last": true,
            "first": true
          }
        }
        """;

    public static final String HISTORY_ERROR = """
        {
          "code": 1,
          "message": "No records found",
          "success": false,
          "data": null
        }
        """;

    public static final String DB_ERROR = """
        {
          "code": 1,
          "message": "Database unavailable",
          "success": false,
          "data": null
        }
        """;
}
