package com.example.cw.controllers.Strats;

import com.example.cw.entity.Customer;
import com.example.cw.services.LotService;
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
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer user = (Customer) request.getSession().getAttribute("user");
        long lotId = Long.parseLong(request.getParameter("lotId"));
        lotService.deleteLot(lotId);
        request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
        forwardToJsp(request, response, "MainPage");
    }
}
