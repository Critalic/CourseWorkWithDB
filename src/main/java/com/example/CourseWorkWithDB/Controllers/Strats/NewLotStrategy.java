package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class NewLotStrategy extends SomeStrat{
    private final LotService lotService;

    public NewLotStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response, "NewLot");
    }

    @Override
    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            String lotName = EmptyValidator.checkIfEmpty(request.getParameter("lotName"), "Lot's name");
            String lotInfo = request.getParameter("lotInfo");
            int price = Integer.parseInt(
                    EmptyValidator.checkIfEmpty(request.getParameter("lotStartPrice"), "Start price"));
            lotService.createNewLot(user.getId(), lotName, user.getLogin(), price);
            request.getSession().setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
        } catch (SQLException | LessThanZeroException | NullPointerException | IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getLocalizedMessage());
            forwardError(request, response,"DefinedError");
            return;
        }

        forwardToJsp(request, response, "MainPage");
    }
}
