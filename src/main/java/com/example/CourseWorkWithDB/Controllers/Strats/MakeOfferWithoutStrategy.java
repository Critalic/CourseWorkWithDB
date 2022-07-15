package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Exceptions.LessThanGivenException;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MakeOfferWithoutStrategy extends SomeStrat {
    private final LotOfferService lotOfferService;
    private final LotService lotService;

    public MakeOfferWithoutStrategy(LotOfferService lotOfferService, LotService lotService) {
        this.lotOfferService = lotOfferService;
        this.lotService = lotService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Customer user = (Customer) request.getSession().getAttribute("user");
            lotOfferService.createNewOfferWithoutDescription(
                    Double.parseDouble(request.getParameter("money")),
                    Long.parseLong((String) request.getSession().getAttribute("lotId")),
                    user.getId()
            );
            request.getSession().setAttribute("lots", lotService.getLots());
        } catch (SQLException | LessThanZeroException | LessThanGivenException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }

        forwardToJsp(request, response, "AllLots");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "NewOffer");
    }
}
