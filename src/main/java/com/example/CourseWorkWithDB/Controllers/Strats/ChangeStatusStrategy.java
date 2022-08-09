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
            long lotId = (long) request.getSession().getAttribute("lotId");
            lotService.changeStatus(lotId);
//            request.getSession().setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
//            request.getSession().setAttribute("lots", lotService.getLots()); //TODO evaluate the need
        forwardToJsp(request,response, "MainPage");
    }
}
