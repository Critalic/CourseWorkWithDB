package com.example.CourseWorkWithDB.Controllers;


import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLDAOFactory;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
        IDAOFactory daoFactory = new SQLDAOFactory(getSQLConnection());
        this.strategySelector = new StrategySelector(
                new UserService(daoFactory),
                new LotService(daoFactory),
                new LotOfferService(daoFactory)
        );
    }

    private String getPath (HttpServletRequest reqest) {
        String answer = reqest.getPathInfo();
        if(answer == null) answer = "/";
        return answer;
    }

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        strategySelector.getStrategy(getPath(reqest)).execGet(reqest, response);
    }

    @Override
    protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        strategySelector.getStrategy(getPath(reqest)).execPost(reqest, response);
    }

    private Connection getSQLConnection() {
        Context context = null;
        DataSource dataSource = null;
        Connection connection = null;
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            dataSource = (DataSource) context.lookup("jdbc/mysql");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
