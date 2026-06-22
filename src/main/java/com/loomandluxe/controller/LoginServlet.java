package com.loomandluxe.controller;

import java.io.IOException;

import com.loomandluxe.dao.UserDAO;
import com.loomandluxe.dao.impl.UserDAOImpl;
import com.loomandluxe.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // GET FORM DATA
        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        // FETCH USER
        User user =
                userDAO.getUserByEmail(email);

        // VALIDATE LOGIN
        if(user != null &&
           user.getPassword().equals(password)) {

            // CREATE SESSION
            HttpSession session =
                    request.getSession();

            session.setAttribute("loggedInUser", user);

            // REDIRECT HOME
            response.sendRedirect(
                    request.getContextPath() + "/home");

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Invalid email or password!");

            request.getRequestDispatcher(
                    "/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }
    }
}