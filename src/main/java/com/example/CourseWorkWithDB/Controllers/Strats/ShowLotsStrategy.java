package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.LotService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

public class ShowLotsStrategy extends SomeStrat {

    LotService lotService;

    public ShowLotsStrategy(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;

        if (!isNull(request.getParameter("pageNumber"))) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }

        request.setAttribute("lots", lotService.getActiveLots(pageNumber, sizeLimit));
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("numberOfPages", getPageCount(lotService.getSumOfRecords()));
        forwardToJsp(request, response, "AllLots");
    }
}
