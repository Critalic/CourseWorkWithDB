package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Exceptions.InvalidEmailException;
import com.example.CourseWorkWithDB.Exceptions.WrongPasswordException;
import com.example.CourseWorkWithDB.Services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignUpStrategy extends SomeStrat {
    private final CustomerService customerService;

    public SignUpStrategy (CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                customerService.signUp(
                        request.getParameter("email"),
                        request.getParameter("name"),
                        request.getParameter("password1"),
                        request.getParameter("password2")
                );
            } catch (SQLException | InvalidEmailException | WrongPasswordException | NullPointerException | IllegalArgumentException e) {
                request.setAttribute("errorMessage", e.getLocalizedMessage());
                forwardError(request, response,"DefinedError");
                return;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace(); //TODO direct to unknown error
            }
        forwardToJsp(request, response,"LogIn");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "SignUp");
    }
}
