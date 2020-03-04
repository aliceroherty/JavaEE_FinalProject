package com.ats.dataaccess;

/**
 * DAL Factory Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class DALFactory {
    //Create instance of the requried IDAL
    public static IDAL createInstance(){
        
        //Determine which DAL to return        
        return new DAL();
    }
}
