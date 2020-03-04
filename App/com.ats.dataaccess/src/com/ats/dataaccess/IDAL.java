package com.ats.dataaccess;

import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 * Data Access Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IDAL {
    List<Object> executeNonQuery(String statement, List<IParameter> params);
    CachedRowSet executeFill(String statement, List<IParameter> params);
    Object executeScalar(String statement,List<IParameter> params);
}
