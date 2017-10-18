/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jordanrehbein
 */
public interface IAuthorDao {
    public abstract int updateAuthor(List<String> colName, List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException;
    
    public abstract int addAuthor(List<String> colName, List<Object> colValues) throws ClassNotFoundException, SQLException;
    
    public abstract Author getAuthorById(Integer id) throws SQLException, ClassNotFoundException;

    public abstract List<Author> getListOfAuthors() throws ClassNotFoundException, SQLException;
    
    public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;
}
