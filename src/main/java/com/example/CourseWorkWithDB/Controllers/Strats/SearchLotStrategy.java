package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Model.Lot;
import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class SearchLotStrategy extends SomeStrat{
    LotService lotService;
    public SearchLotStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "SearchLot");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String input = request.getParameter("input");
            input = input.toLowerCase(Locale.ROOT);
            List<Lot> answer =  lotService.getLotsByName(input);
            request.setAttribute("lotsFound", answer);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }
        forwardToJsp(request, response, "SearchLot");
    }
}
