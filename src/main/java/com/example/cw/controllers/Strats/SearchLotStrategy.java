package com.example.cw.controllers.Strats;

import com.example.cw.entity.Lot;
import com.example.cw.services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchLotStrategy extends SomeStrat {

    LotService lotService;

    public SearchLotStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "SearchLot");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String input = request.getParameter("input").toLowerCase();
        List<Lot> answer = lotService.getActiveLotsByName(input);
        request.setAttribute("lotsFound", answer);

        forwardToJsp(request, response, "SearchLot");
    }
}
