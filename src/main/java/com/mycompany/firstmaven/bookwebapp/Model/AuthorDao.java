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
public class AuthorDao implements IAuthorDao {

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    public AuthorDao(String driverClass, String url, String userName, String password, DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }

    @Override
    public final int updateAuthor(List<String> colName, List<Object> colValues, Object pkValue) throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, password);

        int recsDeleted = db.updateRecord(AUTHOR_TBL, colName, colValues, AUTHOR_PK, pkValue);

        db.closeConnection();

        return recsDeleted;
    }

    @Override
    public final int addAuthor(List<String> colName, List<Object> colValues) throws ClassNotFoundException, SQLException {
        db.openConnection(driverClass, url, userName, password);

        int recsAdded = db.createRecord(AUTHOR_TBL, colName, colValues);

        db.closeConnection();

        return recsAdded;
    }

    @Override
    public final int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("id must be a Integer greater than 0");
        }

        db.openConnection(driverClass, url, userName, password);

        int recsDeleted = db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);

        db.closeConnection();

        return recsDeleted;
    }

    @Override
    public final Author getAuthorById(Integer id) throws SQLException, ClassNotFoundException {

        db.openConnection(driverClass, url, userName, password);

        Map<String, Object> record
                = db.getRecordById(AUTHOR_TBL, AUTHOR_PK, id);

        Author author = new Author();

        Object objRecId = record.get(AUTHOR_PK);
        Integer recId = objRecId == null ? 0 : Integer.parseInt(objRecId.toString());
        author.setAuthorId(recId);

        Object objName = record.get("author_name");
        String authorName = objName == null ? "" : objName.toString();
        author.setAuthorName(authorName);

        Object objRecAdded = record.get("date_added");
        Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
        author.setDateAdded(recAdded);

        db.closeConnection();

        return author;
    }

    @Override
    public final List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException {

        db.openConnection(driverClass, url, userName, password);

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords(AUTHOR_TBL, 0);

        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();

            Object objRecId = rec.get(AUTHOR_PK);
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);

            list.add(author);
        }

        db.closeConnection();

        return list;
    }

    public final DataAccess getDb() {
        return db;
    }

    public final void setDb(DataAccess db) {
        if (db == null) {
            throw new IllegalArgumentException("DataAccess is not valid");
        } else {
            this.db = db;
        }
    }

    public final String getDriverClass() {
        return driverClass;
    }

    public final void setDriverClass(String driverClass) {
        if(driverClass == null || driverClass.isEmpty()){
            throw new IllegalArgumentException("Input is not valid: There must be a driverClass");
        } else {
        this.driverClass = driverClass;
        }
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        if(url == null || url.isEmpty()){
            throw new IllegalArgumentException("Input is not valid: There must be a url");
        } else {
        this.url = url;
        }
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        if(userName == null || userName.isEmpty()){
            throw new IllegalArgumentException("Input is not valid: There must be a userName");
        } else {
        this.userName = userName;
        }
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Input is not valid: There must be a password");
        } else {
        this.password = password;
        }
    }
}
