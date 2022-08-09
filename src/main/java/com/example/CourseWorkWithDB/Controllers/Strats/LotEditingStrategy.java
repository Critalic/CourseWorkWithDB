package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Entity.Lot;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import java.io.IOException;
import java.util.Objects;
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
        if (Objects.isNull(request.getParameter("lotID"))) {
            Customer user = (Customer) request.getSession().getAttribute("user");

            request.setAttribute("errorMessage", "Please select a lot");
            request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
            forwardToJsp(request, response, "MainPage");
        }

        long lotId = Long.parseLong(request.getParameter("lotID"));
        Lot lot = lotService.getLotById(lotId);

        request.setAttribute("lot", lot);
        request.setAttribute("lotOffers", lotOfferService.getOffersForLot(lotId));

        request.getSession().setAttribute("lotId", lotId);
        forwardToJsp(request, response, "LotEditor");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        super.execPost(request, response);
    }
}
