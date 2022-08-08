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
    private final ValidatorService validatorService;

    public MakeOfferStrategy(LotOfferService lotOfferService, LotService lotService, ValidatorService validatorService) {
        this.lotOfferService = lotOfferService;
        this.lotService = lotService;
        this.validatorService = validatorService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Customer user = (Customer) request.getSession().getAttribute("user");
            lotOfferService.createOffer(
                    Double.parseDouble(request.getParameter("money")),
                    Long.parseLong((String) request.getSession().getAttribute("lotId")),
                    user.getId(),
                    request.getParameter("text")
            );
            request.getSession().setAttribute("lots", lotService.getLots());  //TODO analyze this
        } catch (SQLException | LessThanZeroException | LessThanGivenException | IllegalArgumentException | NullPointerException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;        }
        forwardToJsp(request, response, "AllLots");
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "NewOffer");
    }
}
