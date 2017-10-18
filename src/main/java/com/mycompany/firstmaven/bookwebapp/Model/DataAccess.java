/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jordanrehbein
 */
public interface DataAccess {

    public abstract void closeConnection() throws SQLException;
    
    public abstract int updateRecord(String tableName, List<String> colNames, List<Object> colValues, String pkColName, Object pkValue) throws ClassNotFoundException, SQLException;
    
    public abstract int createRecord(String tableName, List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException;
    
    public abstract int deleteRecordById(String tableName, String pkColumnName, Object pkValue) throws SQLException, ClassNotFoundException;
    
    public abstract Map<String, Object> getRecordById(String tableName, String pkColName, Object pkValue) throws ClassNotFoundException, SQLException;

    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws ClassNotFoundException, SQLException;

    public abstract void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
}
