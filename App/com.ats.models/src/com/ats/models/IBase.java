package com.ats.models;

import java.util.ArrayList;

/**
 * Base Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IBase {
    ArrayList<IError> getErrors();
    
    void addError(IError error);    
}
