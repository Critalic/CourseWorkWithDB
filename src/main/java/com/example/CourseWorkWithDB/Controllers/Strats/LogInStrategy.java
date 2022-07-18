package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LogInStrategy extends SomeStrat {
    private final CustomerService customerService;

    public LogInStrategy(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        try {
            request.getSession().setAttribute("user",
                    customerService.logIn(
                            request.getParameter("email").trim(),
                            request.getParameter("password").trim()
                    )
            );
        } catch (NoSuchAlgorithmException | WrongPasswordException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }
        response.sendRedirect(request.getContextPath()+"/lots/");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToJsp(request, response,"LogIn");
    }
}
