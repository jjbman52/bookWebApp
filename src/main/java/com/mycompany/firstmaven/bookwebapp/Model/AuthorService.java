package com.mycompany.firstmaven.bookwebapp.Model;

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

    public final List<Author> getAuthorList() {
        return Arrays.asList(
                new Author(1, "Mark Twain", new Date()),
                new Author(2, "Stephen King", new Date()),
                new Author(3, "George Orwell", new Date()));
    }
}
