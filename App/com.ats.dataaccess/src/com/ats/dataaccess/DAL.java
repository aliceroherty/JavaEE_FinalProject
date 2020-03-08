package com.ats.dataaccess;

import com.ats.dataaccess.IParameter.Direction;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * Data Access Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class DAL implements IDAL {
    private String url = "";
    private String userName = "";
    private String password = "";

    /**
     * Execute Non Query performs non query operations on a relation database
     * @param statement The SQL statement to execute. Support for embedded parameterized sql and stored procedures
     * @param params The parameters list
     * @return A list of return values which are output parameters if they are provided 
     * or when no output parameters are provided, the number of rows affected are returned
     */
    @Override
    public List<Object> executeNonQuery(String statement, List<IParameter> params) {
        List<Object> returnValues = new ArrayList();

        try {
            propertiesSetUp();

            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                try (CallableStatement cstmt = conn.prepareCall(statement)) {
                    int i = 1;

                    //Load the input and output parameters (if any)
                    for (IParameter p : params) {
                        if (p.getDirection() == Direction.IN) {
                            cstmt.setObject(i, p.getValue());
                        } else {
                            cstmt.registerOutParameter(i, p.getSQLType());
                        }

                        i++;
                    }

                    boolean returnedRowsEffected = !cstmt.execute();

                    //If there are no output parameters included, add rows affected to the return list
                    if (params.stream().filter(p -> p.getDirection() == Direction.OUT).toArray().length == 0) {
                        if (returnedRowsEffected) {
                            returnValues.add(cstmt.getUpdateCount());
                        }
                    } else {
                        i = 1;
                        //Add all output parameters to the return list
                        for (IParameter p : params) {
                            if (p.getDirection() == Direction.OUT) {
                                returnValues.add(cstmt.getObject(i));
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return returnValues;
    }

    /**
     * Execute Query operations on a relational database
     * @param statement
     * @param params
     * @return 
     */
    @Override
    public CachedRowSet executeFill(String statement, List<IParameter> params) {
        CachedRowSet rowSet = null;

        try {
            propertiesSetUp();
            rowSet = RowSetProvider.newFactory().createCachedRowSet();

            try (Connection conn = DriverManager.getConnection(url, userName, password)) {
                try (PreparedStatement pstmt = conn.prepareStatement(statement)) {
                    loadInputParameters(pstmt, params);
                    
                    //Execute the query and populate the rowSet with the resultset
                    //CachedRowSet is a disconnected technology which we can easily return
                    //The database connection is closed within the try with resources block
                    try (ResultSet rs = (ResultSet) pstmt.executeQuery()) {
                        rowSet.populate(rs);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rowSet;
    }

    /**
     * Executes scalar operations on a relational database
     *
     * @param statement the statement to execute
     * @param params The parameters list
     * @return A single value
     */
    @Override
    public Object executeScalar(String statement, List<IParameter> params) {
        Object returnValue = null;

        try {
            propertiesSetUp();

            try (Connection conn = (Connection) DriverManager.getConnection(url, userName, password)) {
                try (PreparedStatement pstmt = conn.prepareStatement(statement)) {
                    //Load the input parameters
                    loadInputParameters(pstmt, params);
                           
                    //Execute the query and obtain the aggregate value returned from the database
                    try (ResultSet rs = (ResultSet) pstmt.executeQuery()) {
                        if (rs.next()) {
                            returnValue = rs.getObject(1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return returnValue;
    }

    /**
     * Helper method for loading input parameters only
     * @param smt The prepared statement being used
     * @param params The list of parameters to load
     * @throws SQLException 
     */
    private void loadInputParameters(PreparedStatement smt, List<IParameter> params) throws SQLException {
        int i = 1;
        
        if (params != null && params.size() != 0) {
            for (IParameter p : params) {
                smt.setObject(i, p.getValue());
                i++;
            }
        }
    }

    /**
     * Gets properties from db.properties file key values pairs of db url,
     * username, password, driver
     *
     * @throws Exception
     */
    private void propertiesSetUp() throws Exception {
        Properties props = DALHelper.getProperties();
        url = props.getProperty("database.url");
        userName = props.getProperty("database.username");
        password = props.getProperty("database.password");
        Class.forName(props.getProperty("database.driver")).newInstance();
    }
}
