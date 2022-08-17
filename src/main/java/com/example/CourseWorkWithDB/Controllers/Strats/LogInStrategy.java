package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Exceptions.WrongPasswordException;
import com.example.CourseWorkWithDB.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LogInStrategy extends SomeStrat {
    private final UserService userService;

    public LogInStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getSession().setAttribute("user",
                    userService.logIn(
                            request.getParameter("email").trim(),
                            request.getParameter("password").trim()
                    )
            );
        } catch (SQLException | NoSuchAlgorithmException | WrongPasswordException e) {
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
