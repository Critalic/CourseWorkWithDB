package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Services.LotService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeStatusStrategy extends SomeStrat {

    private final LotService lotService;

    public ChangeStatusStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer user = (Customer) request.getSession().getAttribute("user");
        long lotId = (long) request.getSession().getAttribute("lotId");
        lotService.changeStatus(lotId);
        request.setAttribute("ownersLots", lotService.getLotsWithOwner(user.getId()));
//            request.getSession().setAttribute("lots", lotService.getLots()); //TODO evaluate the need
        forwardToJsp(request, response, "MainPage");
    }
}
