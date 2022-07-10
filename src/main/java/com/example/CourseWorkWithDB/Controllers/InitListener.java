package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.IDAOFactory;
import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLDAOFactory;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class InitListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        FrontController frontController = new FrontController();
//        Connection connection = getSQLConnection(frontController);
////        System.out.println(dataSource.getClass().getName());
//
//        IDAOFactory daoFactory = new SQLDAOFactory(connection);
//        StrategySelector strategySelector = new StrategySelector(
//                new UserService(daoFactory),
//                new LotService(daoFactory),
//                new LotOfferService(daoFactory)
//        );
//        servletContextEvent.getServletContext().setAttribute("selector", strategySelector);
//    }

    private Connection getSQLConnection(FrontController frontController) {
        String dataSourceName = frontController.getServletContext().getInitParameter("DataSource");
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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed");
    }
}
