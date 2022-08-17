package com.example.cw.controllers;


import com.example.cw.dao.DAOFactory;
import com.example.cw.dao.jpa.implementations.JpaDaoFactory;
import com.example.cw.services.CustomerService;
import com.example.cw.services.LotOfferService;
import com.example.cw.services.LotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.io.IOException;

@WebServlet(name = "frontController", urlPatterns = "/lots/*")
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);
    private StrategySelector strategySelector;
    private EntityManagerFactory factory;
    private ValidatorFactory validatorFactory;

    @Override
    public void init(ServletConfig config) {
        strategySelector = (StrategySelector) config.getServletContext().getAttribute("selector");
        factory = Persistence.createEntityManagerFactory("cleverCloud");
        validatorFactory = Validation.buildDefaultValidatorFactory();

        DAOFactory daoFactory = new JpaDaoFactory(factory);

        this.strategySelector = new StrategySelector(
                new CustomerService(daoFactory),
                new LotService(daoFactory),
                new LotOfferService(daoFactory),
                validatorFactory
        );
    }

    private String getPath(HttpServletRequest request) {
        String answer = request.getPathInfo();
        return answer == null ? "/" : answer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            strategySelector.getStrategy(getPath(request)).execGet(request, response);
        } catch (Throwable e) {
            LOGGER.error("Caught exception:", e);
            request.getRequestDispatcher("/WEB-INF/Errors/UndefinedError.html").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            strategySelector.getStrategy(getPath(request)).execPost(request, response);
        } catch (Throwable e) {
            LOGGER.error("Caught exception:", e);
            request.getRequestDispatcher("/WEB-INF/Errors/UndefinedError.html").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        validatorFactory.close();
        factory.close();
    }
}
