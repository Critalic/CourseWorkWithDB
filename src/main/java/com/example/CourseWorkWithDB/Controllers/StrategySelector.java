package com.example.CourseWorkWithDB.Controllers;

import com.example.CourseWorkWithDB.Controllers.Strats.*;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.UserService;


public class StrategySelector {
    private final UserService userService;
    private final LotService lotService;
    private final LotOfferService lotOfferService;

    public StrategySelector(UserService userService, LotService lotService, LotOfferService lotOfferService) {
        this.userService = userService;
        this.lotService = lotService;
        this.lotOfferService = lotOfferService;
    }

    public SomeStrat getStrategy (String path) {
        switch (path) {
            case "/logIn":
                return new LogInStrategy(userService);
            case "/signUp":
                return new SignUpStrategy(userService);
            case "/createLot":
                return new NewLotStrategy(lotService);
            case "/mainPage":
                return new MainPageStrategy(lotService);
            case "/viewLot":
                return new ViewLotStrategy(lotService);
            case "/editLot":
                return new LotEditingStrategy(lotService);
            case "/logOut":
                return new LogOutStrategy( lotService);
            case "/changeStatus":
                return new ChangeStatusStrategy(lotService);
            case "/searchLot":
                return new SearchLotStrategy(lotService);
            case "/deleteLot":
                return new DeleteLotStrategy(lotService);
//            case "/makeOfferWithDescription":
//                return new MakeOfferWithStrategy(lotOfferService, lotService);
            case "/makeOffer":
                return new MakeOfferWithoutStrategy(lotOfferService, lotService);
            case "/generateURL":
                return new URLGeneratorStrategy(lotService);
            default:
                return new ShowLotsStrategy(lotService);
        }
    }
}
