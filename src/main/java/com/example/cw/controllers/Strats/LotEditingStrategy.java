package com.example.cw.controllers.Strats;

import com.example.cw.entity.Lot;
import com.example.cw.services.LotOfferService;
import com.example.cw.services.LotService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LotEditingStrategy extends SomeStrat {

    private final LotService lotService;
    private final LotOfferService lotOfferService;

    public LotEditingStrategy(LotService lotService, LotOfferService lotOfferService) {
        this.lotService = lotService;
        this.lotOfferService = lotOfferService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long lotId = Long.parseLong(request.getParameter("lotID"));
        Lot lot = lotService.getLotById(lotId);

        request.setAttribute("lot", lot);
        request.setAttribute("lotOffers", lotOfferService.getOffersForLot(lotId));

        request.setAttribute("lotId", lotId);
        forwardToJsp(request, response, "LotEditor");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        super.execPost(request, response);
    }
}
