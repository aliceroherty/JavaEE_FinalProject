package com.ats.models;

/**
 * Error Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IError {
    public int getCode();
    public void setCode(int code);
    public String getDescription();
    public void setDescription(String description);
}
