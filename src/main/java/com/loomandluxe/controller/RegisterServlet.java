package com.loomandluxe.controller;

import java.io.IOException;
import jakarta.servlet.http.HttpSession;

import com.loomandluxe.dao.UserDAO;
import com.loomandluxe.dao.impl.UserDAOImpl;
import com.loomandluxe.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
                "/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // GET FORM DATA
        String fullName =
                request.getParameter("fullName");

        String email =
                request.getParameter("email");

        String phone =
                request.getParameter("phone");

        String password =
                request.getParameter("password");

        String addressLine1 =
                request.getParameter("addressLine1");

        String addressLine2 =
                request.getParameter("addressLine2");

        String city =
                request.getParameter("city");

        String state =
                request.getParameter("state");

        String country =
                request.getParameter("country");

        String pincode =
                request.getParameter("pincode");

        // CHECK EXISTING EMAIL
        User existingUser =
                userDAO.getUserByEmail(email);

        if(existingUser != null) {

            request.setAttribute(
                    "errorMessage",
                    "Email already registered!");

            request.getRequestDispatcher(
                    "/WEB-INF/views/register.jsp")
                    .forward(request, response);

            return;
        }

        // CREATE USER OBJECT
        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        user.setAddressLine1(addressLine1);
        user.setAddressLine2(addressLine2);

        user.setCity(city);
        user.setState(state);
        user.setCountry(country);
        user.setPincode(pincode);

        // SAVE USER
        boolean isRegistered =
                userDAO.addUser(user);

        if(isRegistered) {

            // FETCH NEWLY REGISTERED USER
            User registeredUser =
                    userDAO.getUserByEmail(email);

            // CREATE SESSION
            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedInUser",
                    registeredUser);

            // REDIRECT TO HOME
            response.sendRedirect(
                    request.getContextPath() + "/home");

        } 
        else {

            request.setAttribute(
                    "errorMessage",
                    "Registration failed!");

            request.getRequestDispatcher(
                    "/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }
}