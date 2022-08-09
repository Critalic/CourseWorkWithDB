package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MakeOfferStrategy extends SomeStrat {

    private final LotOfferService lotOfferService;
    private final LotService lotService;

    public MakeOfferStrategy(LotOfferService lotOfferService, LotService lotService) {
        this.lotOfferService = lotOfferService;
        this.lotService = lotService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            Customer user = (Customer) request.getSession().getAttribute("user");
            lotOfferService.createOffer(
                Double.parseDouble(request.getParameter("money")),
                Long.parseLong((String) request.getSession().getAttribute("lotId")),
                user.getId(),
                request.getParameter("text")
            );
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            forwardToJsp(request, response, "NewOffer");
        }
        request.setAttribute("lots", lotService.getLots());
        forwardToJsp(request, response, "AllLots");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "NewOffer");
    }
}
