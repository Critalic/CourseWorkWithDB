package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowLotsStrategy extends SomeStrat {

    LotService lotService;

    public ShowLotsStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("lots", lotService.getLots());
        forwardToJsp(request, response, "AllLots");
    }
}
