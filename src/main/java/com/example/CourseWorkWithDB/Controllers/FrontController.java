package com.example.CourseWorkWithDB.Controllers;


import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.UserService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "frontController", urlPatterns = "/lots/*")
public class FrontController extends HttpServlet {
    private StrategySelector strategySelector;

    @Override
    public void init(ServletConfig config) throws ServletException {
//        strategySelector = (StrategySelector) config.getServletContext().getAttribute("selector");
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cleverCloud");
//        this.strategySelector = new StrategySelector(
//                new UserService(factory),
//                new LotService(factory),
//                new LotOfferService(factory)
//        );
    }

    private String getPath(HttpServletRequest reqest) {
        String answer = reqest.getPathInfo();
        return answer == null ? "/" : answer;
    }

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
//        strategySelector.getStrategy(getPath(reqest)).execGet(reqest, response);
    }

    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
//        strategySelector.getStrategy(getPath(reqest)).execPost(reqest, response);
    }
}
