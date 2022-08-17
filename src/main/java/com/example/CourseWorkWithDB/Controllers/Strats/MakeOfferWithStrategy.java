package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Exceptions.LessThanGivenException;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;
import com.example.CourseWorkWithDB.Model.User;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MakeOfferWithStrategy extends SomeStrat {
    LotOfferService lotOfferService;
    private final LotService lotService ;
    public MakeOfferWithStrategy(LotOfferService lotOfferService, LotService lotService) {
        this.lotOfferService = lotOfferService;
        this.lotService = lotService;
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            lotOfferService.createNewOfferWithDescription(
                    Integer.parseInt(request.getParameter("money")),
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
