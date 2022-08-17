package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Model.Lot;
import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LotEditingStrategy extends SomeStrat{
    private final LotService lotService;
    public LotEditingStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long lotId = Long.parseLong(request.getParameter("lotID"));
        request.getSession().setAttribute("lotId", lotId);
        try {
            Lot lot = lotService.getLotById(lotId);
            if(lot.getOffersQuantity()!=0) lot.setOffers(lotService.getOffers(lotId));
            request.setAttribute("lot", lot);
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }
        forwardToJsp(request, response, "LotEditor");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.execPost(request, response);
    }
}
