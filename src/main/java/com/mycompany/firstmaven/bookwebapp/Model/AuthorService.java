package com.mycompany.firstmaven.bookwebapp.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jordanrehbein
 */
public class AuthorService {

    private IAuthorDao authorDao;

    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public final int updateAuthorById(List<String> colNames, List<Object> colValues, String id) throws ClassNotFoundException, SQLException {
        if (colNames == null || colNames.isEmpty() || colValues == null || colValues.isEmpty() || id == null || id.isEmpty()) {
            throw new IllegalArgumentException("colNames and colValues are required");
        } else {
            return authorDao.updateAuthor(colNames, colValues, id);
        }
    }

    public final int addAuthor(List<String> colNames, List<Object> colValues) throws ClassNotFoundException, SQLException {
        if (colNames == null || colNames.isEmpty() || colValues == null || colValues.isEmpty()) {
            throw new IllegalArgumentException("colNames and colValues are required");
        } else {
            return authorDao.addAuthor(colNames, colValues);
        }
    }

    public final int removeAuthorById(String id) throws ClassNotFoundException, SQLException, NumberFormatException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        Integer value = Integer.parseInt(id);

        return authorDao.removeAuthorById(value);
    }

    public Author getAuthor(String id) throws SQLException, ClassNotFoundException {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        Integer value = Integer.parseInt(id);

        return authorDao.getAuthorById(value);
    }

    public List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();
    }

    public IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public final void setAuthorDao(IAuthorDao authorDao) {
        if (authorDao == null) {
            throw new IllegalArgumentException("authorDao is not valid");
        } else {
            this.authorDao = authorDao;
        }
    }
}
