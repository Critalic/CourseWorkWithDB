package com.example.CourseWorkWithDB.Controllers;


import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLDAOFactory;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "frontController", urlPatterns = "/lots/*")
public class FrontController extends HttpServlet {
    private StrategySelector strategySelector;

    @Override
    public void init(ServletConfig config) throws ServletException {
//        strategySelector = (StrategySelector) config.getServletContext().getAttribute("selector");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
        this.strategySelector = new StrategySelector(
                new UserService(factory),
                new LotService(factory),
                new LotOfferService(factory)
        );
    }

    private String getPath(HttpServletRequest reqest) {
        String answer = reqest.getPathInfo();
        return answer == null ? "/" : answer;
    }

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        strategySelector.getStrategy(getPath(reqest)).execGet(reqest, response);
    }

    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        strategySelector.getStrategy(getPath(reqest)).execPost(reqest, response);
    }
}
