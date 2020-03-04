package com.ats.repo;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.rowset.CachedRowSet;

/**
 * Base Repository Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public abstract class BaseRepo {
    protected int getByColumnLabel(String name, CachedRowSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        int returnIndex = 0;
        for (int i = 1; i <= cols; ++i) {
            String colLabel = rsmd.getColumnLabel(i);
            String colName = rsmd.getColumnName(i);
            if (colName != null) {
                if (name.equalsIgnoreCase(colName) || name.equalsIgnoreCase(rsmd.getTableName(i) + "." + colName)) {
                    return (i);
                } else if (colLabel != null) {
                    if (name.equalsIgnoreCase(colLabel)) {
                        returnIndex = (i);
                    } else {
                        continue;
                    }
                }
            }
        }

        return returnIndex;
    }

    protected int getInt(String columnName, CachedRowSet rs) throws SQLException {
        int nValue = 0;
        nValue = rs.getInt(getByColumnLabel(columnName,rs));
        return nValue;
    }

    protected double getDouble(String columnName, CachedRowSet rs) throws SQLException {
        double nValue = 0.0;
        nValue = rs.getDouble(getByColumnLabel(columnName,rs));
        return nValue;
    }

    protected Date getDate(String columnName, CachedRowSet rs) throws SQLException{
        Date nValue = new Date();
        nValue = rs.getDate(getByColumnLabel(columnName,rs));
        return nValue;
    }
    
    protected String getString(String columnName, CachedRowSet rs) throws SQLException {
        String nValue = "";
        nValue = rs.getString(getByColumnLabel(columnName, rs));
        return nValue;
    }
    
    protected boolean getBoolean(String columnName, CachedRowSet rs) throws SQLException {
        return rs.getBoolean(getByColumnLabel(columnName, rs));
    }
}
