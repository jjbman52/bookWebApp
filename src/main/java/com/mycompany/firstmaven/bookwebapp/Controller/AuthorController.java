/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Controller;

import com.mycompany.firstmaven.bookwebapp.Model.DateUtilities;
import com.mycompany.firstmaven.bookwebapp.Model.Author;
import com.mycompany.firstmaven.bookwebapp.Model.AuthorDao;
import com.mycompany.firstmaven.bookwebapp.Model.AuthorService;
import com.mycompany.firstmaven.bookwebapp.Model.IAuthorDao;
import com.mycompany.firstmaven.bookwebapp.Model.MySqlDataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jordanrehbein
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LIST_ACTION = "list";
    public static final String ADD_ACTION = "add";
    public static final String SAVE_ACTION = "save";
    public static final String EDIT_DELETE_ACTION = "editDelete";
    public static final String DELETE_ACTION = "delete";
    public final DateUtilities du = new DateUtilities();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String destination = "/authorList.jsp";

        try {
            String action = request.getParameter(ACTION);
            String id = request.getParameter(ID);
            IAuthorDao dao = new AuthorDao(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/book",
                    "root", "admin",
                    new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao);

            List<Author> authorList = null;
            Author author = new Author();

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                destination = "/authorList.jsp";
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                destination = "/authorAdd.jsp";
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            } else if (action.equalsIgnoreCase(SAVE_ACTION)) {
                String name = request.getParameter("name").trim();
                if (id == null) {
                    destination = "/authorAdd.jsp";
                    String date = du.toCustomeFormattedString(LocalDateTime.now(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    authorService.addAuthor(Arrays.asList("author_name", "date_added"), Arrays.asList(name, date));
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                } else {
                    destination = "/authorList.jsp";
                    String date = request.getParameter("date").trim();
                    authorService.updateAuthorById(Arrays.asList("author_name", "date_added"), Arrays.asList(name, date), id);
                    authorList = authorService.getAuthorList();
                    request.setAttribute("authorList", authorList);
                }
            } else if (action.equalsIgnoreCase(EDIT_DELETE_ACTION)) {
                destination = "/authorEdit.jsp";
                author = authorService.getAuthor(id);
                request.setAttribute("author", author);
            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                destination = "/authorList.jsp";
                authorService.removeAuthorById(id);
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
            }

        } catch (Exception e) {
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
