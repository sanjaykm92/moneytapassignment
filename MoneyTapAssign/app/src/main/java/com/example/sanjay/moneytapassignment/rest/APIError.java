package com.example.sanjay.moneytapassignment.rest;

public class APIError {
    private int statusCode;
    private String message;

    public APIError() {
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int status() {
        return statusCode;
    }


    public String message() {
        if(status() == 504)
            return "Server timeout. Please try again.";
        else if (status() == 500)
            return "Internal Server Error";

        return "Unknown Error";
    }
}
