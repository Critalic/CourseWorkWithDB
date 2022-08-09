package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.CustomerService;
import com.example.CourseWorkWithDB.Services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LogInStrategy extends SomeStrat {
    private final CustomerService customerService;
    private final ValidatorService validatorService;

    public LogInStrategy(CustomerService customerService, ValidatorService validatorService) {
        this.customerService = customerService;
        this.validatorService = validatorService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Object[] values = {request.getParameter("email").trim(),
                    request.getParameter("password").trim()};
            validatorService.validateMethod(customerService, "logIn",values);

            request.getSession().setAttribute("user",
                    customerService.logIn(
                            request.getParameter("email").trim(),
                            request.getParameter("password").trim()
                    )
            );
        } catch (IllegalArgumentException | ConstraintViolationException e) {
            request.setAttribute("errorMessage", e.getMessage());
            forwardToJsp(request, response, "LogIn");
        } catch (NoSuchAlgorithmException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response, "DefinedError");
        }
        response.sendRedirect(request.getContextPath() + "/lots/");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response, "LogIn");
    }
}
