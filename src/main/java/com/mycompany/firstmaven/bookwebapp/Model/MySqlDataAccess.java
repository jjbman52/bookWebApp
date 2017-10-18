package com.mycompany.firstmaven.bookwebapp.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

/**
 *
 * @author jordanrehbein
 */
public class MySqlDataAccess implements DataAccess {
private final int ALL_RECORDS = 0;
private final boolean DEBUG = true;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public final void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public final void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    
@Override
    public final int updateRecord(String tableName, List<String> colNames, 
            List<Object> colValues, String pkColName, Object pkValue) throws ClassNotFoundException,
            SQLException {
        
        String sql = "UPDATE " + tableName + " SET ";
        
        for(int i = 0; i < colNames.size(); i++) {
            if(i < colNames.size() - 1){
                sql += colNames.get(i) + " = " + "?, ";
            }else {
                sql += colNames.get(i) + " = " + "?";
            }
        }
        sql += " WHERE " + pkColName + " = " + "?" + ";";
        
        if(DEBUG) System.out.println(sql.toString());
        
        pstmt = conn.prepareStatement(sql);
        
        for(int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i-1));
        }
        
        pstmt.setObject(colValues.size()+1,pkValue);
        
        return pstmt.executeUpdate();
    }
    
@Override
    public final int createRecord(String tableName, List<String> colNames, 
            List<Object> colValues) throws ClassNotFoundException,
            SQLException {
        
        String sql = "Insert INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ","(", ")");
        for(String col : colNames) {
            sj.add(col);
        }
        
        if(DEBUG) System.out.println(sql.toString());
        sql += sj.toString();
        sql += " VALUES ";
        
        sj = new StringJoiner(", ","(", ")");
        for(Object value: colValues) {
            sj.add("?");
        }
        
        sql += sj.toString();
        if(DEBUG) System.out.println(sql.toString());
        
        pstmt = conn.prepareStatement(sql);
        
        for(int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i-1));
        }
        
        return pstmt.executeUpdate();
    }

    @Override
    public final int deleteRecordById(String tableName, String pkColName,
            Object pkValue) throws ClassNotFoundException,
            SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE "
                + pkColName + " = ?";

//        if (pkValue instanceof String) {
//            sql += "'" + pkValue.toString() + "'";
//        } else {
//            sql += Long.parseLong(pkValue.toString());
//        }

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);

        return pstmt.executeUpdate();
    }
    
    public final Map<String, Object> getRecordById(String tableName, String pkColName,
            Object pkValue) throws ClassNotFoundException,
            SQLException {

        String sql = "select * FROM " + tableName + " WHERE " + pkColName + " = '" + pkValue + "';";

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
        }

        return record;
    }

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public final List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select TOP " + maxRecords + " * from " + tableName;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        return rawData;
    }
}
