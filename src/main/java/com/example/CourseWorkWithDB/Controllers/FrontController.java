package com.example.CourseWorkWithDB.Controllers;


import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.CustomerDAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.LotDAO;
import com.example.CourseWorkWithDB.DAO.JPA.Implementations.LotOfferDAO;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Entity.LotOffer;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.CustomerService;

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
//
//        DAO<Customer> customerDAO = new CustomerDAO(factory);
//        DAO<Lot> lotDAO = new LotDAO(factory);
//        DAO<LotOffer> lotOfferDAO = new LotOfferDAO(factory);
//
//        this.strategySelector = new StrategySelector(
//                new CustomerService(customerDAO),
//                new LotService(factory),
//                new LotOfferService(lotOfferDAO, lotDAO)
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
