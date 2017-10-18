package com.mycompany.firstmaven.bookwebapp.Model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author jordanrehbein
 */
public class MockAuthorDao implements IAuthorDao {

    public MockAuthorDao() {
    }
    
    @Override
    public List<Author> getListOfAuthors() throws ClassNotFoundException, SQLException{
        List<Author> list = Arrays.asList(
                new Author(1, "Mark Twain", new Date()),
                new Author(2, "Stephen King", new Date()),
                new Author(3, "George Orwell", new Date())
        );
        
        return list;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        IAuthorDao dao = new MockAuthorDao();
    
        List<Author> list = dao.getListOfAuthors();
        
        for(Author a : list){
            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + ", " + a.getDateAdded());
        }
    }

    @Override
    public int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        return 1;
    }

    @Override
    public int addAuthor(List<String> colName, List<Object> colValues) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAuthor(List<String> colName, List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Author getAuthorById(Integer id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
