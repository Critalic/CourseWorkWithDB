package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.CustomerService;
import com.example.CourseWorkWithDB.Services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignUpStrategy extends SomeStrat {
    private final CustomerService customerService;
    private final ValidatorService validatorService;

    public SignUpStrategy(CustomerService customerService, ValidatorService validatorService) {
        this.customerService = customerService;
        this.validatorService = validatorService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] values = new String[]{request.getParameter("email"),
                    request.getParameter("name"),
                    request.getParameter("password1"),
                    request.getParameter("password2")};
            validatorService.validateMethod(customerService, "signUp", values);

            customerService.signUp(
                    request.getParameter("email"),
                    request.getParameter("name"),
                    request.getParameter("password1"),
                    request.getParameter("password2")
            );
        } catch (IllegalArgumentException | ConstraintViolationException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardToJsp(request, response, "SignUp");
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response, "DefinedError");
        }

        forwardToJsp(request, response, "LogIn");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "SignUp");
    }
}
