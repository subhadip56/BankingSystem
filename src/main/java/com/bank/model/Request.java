package com.bank.model;

public class Request {
    private int requestId;
    private int userId;
    private String requestType;
    private String status;
    private String description;

    public Request() {}

    public Request(int requestId, int userId, String requestType, String status, String description) {
        this.requestId = requestId;
        this.userId = userId;
        this.requestType = requestType;
        this.status = status;
        this.description = description;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}