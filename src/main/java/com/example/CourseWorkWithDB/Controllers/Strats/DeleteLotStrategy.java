package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Services.LotService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteLotStrategy extends SomeStrat {

    private final LotService lotService;

    public DeleteLotStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer user = (Customer) request.getSession().getAttribute("user");
        long lotId = (long) request.getSession().getAttribute("lotId");
        lotService.deleteLot(lotId);
        request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
        forwardToJsp(request, response, "MainPage");
    }
}
