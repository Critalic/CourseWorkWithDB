package com.example.cw.controllers.Strats;

import com.example.cw.services.LotService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutStrategy extends SomeStrat{
    private final LotService lotService;

    public LogOutStrategy( LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getSession().setAttribute("user", null);
//            request.getSession().setAttribute("lots", lotService.getLots());

        forwardToJsp(request, response, "AllLots");
    }
}
