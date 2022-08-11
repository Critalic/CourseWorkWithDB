package com.example.CourseWorkWithDB.Controllers.Strats;

import com.example.CourseWorkWithDB.Services.BusinessService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SomeStrat {

    protected int sizeLimit = 1;

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspName) throws
            ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/" + jspName + ".jsp").forward(request, response);
    }

    protected void forwardError(HttpServletRequest request, HttpServletResponse response, String jspName) throws
            ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Errors/" + jspName + ".jsp").forward(request, response);
    }

    public void execGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void execPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected long getPageCount(Integer sumOfRecords) {
        return sumOfRecords % sizeLimit == 0 ? sumOfRecords / sizeLimit
                : Math.floorDiv(sumOfRecords, sizeLimit) + 1;
    }
}
