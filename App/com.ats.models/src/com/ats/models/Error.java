package com.ats.models;

/**
 * Error Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class Error implements IError {
    private int code;
    private String description;
    
    public Error() {
    }
    
    public Error(String description) {
        this.description = description;
    }
    
    public Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
