package edu.project3;

public enum HttpStatusCodes {
    OK("200", "OK"),
    NOT_FOUND("404", "Not found"),
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    BAD_REQUEST("400", "Bad Request"),
    N_A("999", "N/A");

    private final String code;
    private final String message;

    HttpStatusCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpStatusCodes getByCode(String code) {
        for (HttpStatusCodes status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return N_A;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
