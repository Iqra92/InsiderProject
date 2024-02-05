package Automation.model;

public class ErrorResponseModel {
    private int code;
    private String type;
    private String message;

    // Constructors

    public ErrorResponseModel() {
    }

    public ErrorResponseModel(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    // Getters

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    // Setters

    public void setCode(int code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
