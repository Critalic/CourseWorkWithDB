package com.example.cw.controllers.Strats;

import com.example.cw.entity.Customer;
import com.example.cw.services.LotService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageStrategy extends SomeStrat {

    private final LotService lotService;

    public MainPageStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer user = (Customer) request.getSession().getAttribute("user");
        request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));

        forwardToJsp(request, response, "MainPage");
    }
}
