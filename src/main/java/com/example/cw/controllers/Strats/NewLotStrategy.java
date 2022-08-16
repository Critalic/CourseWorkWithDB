package com.example.cw.controllers.Strats;

import com.example.cw.entity.Customer;
import com.example.cw.services.LotService;
import com.example.cw.services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.validation.ConstraintViolationException;

public class NewLotStrategy extends SomeStrat{
    private final LotService lotService;
    private final ValidatorService validatorService;

    public NewLotStrategy(LotService lotService, ValidatorService validatorService) {
        this.lotService = lotService;
        this.validatorService = validatorService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "NewLot");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Double price = Double.parseDouble(request.getParameter("lotStartPrice"));
            Customer user = (Customer) request.getSession().getAttribute("user");

            Object[] values = {user.getId(), request.getParameter("lotName"),
                request.getParameter("lotInfo"), price};
            validatorService.validateMethod(lotService, "createNewLot", values);

            lotService.createNewLot(user.getId(), request.getParameter("lotName"),
                request.getParameter("lotInfo"), price);

            request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
        } catch (ConstraintViolationException | IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardToJsp(request, response,"NewLot");
            return;
        }

        forwardToJsp(request, response, "MainPage");
    }
}
