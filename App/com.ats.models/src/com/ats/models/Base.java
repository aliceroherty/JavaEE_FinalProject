package com.ats.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class Base implements IBase, Serializable {
    private ArrayList<IError> errors;

    @Override
    public ArrayList<IError> getErrors() {
        return this.errors;
    }

    @Override
    public void addError(IError error) {
        errors.add(error);
    }
}
