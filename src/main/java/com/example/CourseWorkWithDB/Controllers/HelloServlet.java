package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLLotDAO;
import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLLotOfferDAO;
import com.example.CourseWorkWithDB.DAO.SQLRealizations.SQLUserDAO;
import com.example.CourseWorkWithDB.Exceptions.DBError;
import com.example.CourseWorkWithDB.Exceptions.NoIDException;
import com.example.CourseWorkWithDB.Model.Lot;
import com.example.CourseWorkWithDB.Model.LotOffer;
import com.example.CourseWorkWithDB.Model.User;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "JDBC Pool connection successfully created";
        Connection connection = null;
        try {
            String dataSourceName = getServletContext().getInitParameter("DataSource");
            Context context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            System.out.println(dataSource.getClass().getName());
            connection = dataSource.getConnection();
            SQLLotOfferDAO sqlLotDAO = new SQLLotOfferDAO(connection);
            LotOffer l2 = new LotOffer( 20, 6, 63 );

            List<LotOffer> l = null;
//            sqlLotDAO.addLotOfferWithoutDescription(l2);
//            l = sqlLotDAO.getAllThatContain("coffe");
            l = sqlLotDAO.getAllForLot(6);

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.write("<h1>Hello World</h1>");
            out.write("<hr/>");
            for(LotOffer lot : l) {
                out.write("<p>" + lot.getCost() + "</p>");
            }
            out.close();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } catch (NoIDException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }
        }
    }
}