package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeStatusStrategy extends SomeStrat {
    private final LotService lotService;
    public ChangeStatusStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long lotId = (long) request.getSession().getAttribute("lotId");
            Lot currentLot= lotService.getLotById(lotId);
            User user = (User) request.getSession().getAttribute("user");
            lotService.setLotStatus(lotId, !currentLot.isActive());
            request.getSession().setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
            request.getSession().setAttribute("lots", lotService.getLots());
        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }
        forwardToJsp(request,response, "MainPage");
    }
}
