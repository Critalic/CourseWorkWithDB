package com.example.cw.controllers.Strats;

import com.example.cw.services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewLotStrategy extends SomeStrat {

    private final LotService lotService;

    public ViewLotStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lot", lotService.getLotById(Long.parseLong(request.getParameter("lotID"))));
        request.getSession().setAttribute("lotId", request.getParameter("lotID"));

        forwardToJsp(request, response, "LotDisplayer");
    }
}
