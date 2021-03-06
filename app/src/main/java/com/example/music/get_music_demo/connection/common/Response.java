package com.example.music.get_music_demo.connection.common;

public class Response {
    private boolean success;
    private int code;
    private String message;

    private boolean isHttpSuccess = true;

    public boolean isHttpSuccess() {
        return isHttpSuccess;
    }

    public void setHttpSuccess(boolean isHttpSuccess) {
        this.isHttpSuccess = isHttpSuccess;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
