package com.example.music.get_music_demo.connection;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("Success")
    private boolean success;
    @SerializedName("Code")
    private String code;
    @SerializedName("Message")
    private String message;

    private boolean isHttpSuccess = true;
    private String errorMsg;

    public boolean isHttpSuccess() {
        return isHttpSuccess;
    }

    public void setHttpSuccess(boolean isHttpSuccess) {
        this.isHttpSuccess = isHttpSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
