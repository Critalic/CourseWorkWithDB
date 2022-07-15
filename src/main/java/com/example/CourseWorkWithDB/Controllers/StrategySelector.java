package com.example.CourseWorkWithDB.Controllers;

//import com.example.CourseWorkWithDB.Controllers.Strats.*;
import com.example.CourseWorkWithDB.Services.LotOfferService;
import com.example.CourseWorkWithDB.Services.LotService;
import com.example.CourseWorkWithDB.Services.CustomerService;


public class StrategySelector {
    private final CustomerService customerService;
    private final LotService lotService;
    private final LotOfferService lotOfferService;

    public StrategySelector(CustomerService customerService, LotService lotService, LotOfferService lotOfferService) {
        this.customerService = customerService;
        this.lotService = lotService;
        this.lotOfferService = lotOfferService;
    }

    public Object getStrategy (String path) {
        return null;
//        switch (path) {
//            case "/logIn":
//                return new LogInStrategy(userService);
//            case "/signUp":
//                return new SignUpStrategy(userService);
//            case "/createLot":
//                return new NewLotStrategy(lotService);
//            case "/mainPage":
//                return new MainPageStrategy(lotService);
//            case "/viewLot":
//                return new ViewLotStrategy(lotService);
//            case "/editLot":
//                return new LotEditingStrategy(lotService);
//            case "/logOut":
//                return new LogOutStrategy( lotService);
//            case "/changeStatus":
//                return new ChangeStatusStrategy(lotService);
//            case "/searchLot":
//                return new SearchLotStrategy(lotService);
//            case "/deleteLot":
//                return new DeleteLotStrategy(lotService);
////            case "/makeOfferWithDescription":
////                return new MakeOfferWithStrategy(lotOfferService, lotService);
//            case "/makeOffer":
//                return new MakeOfferWithoutStrategy(lotOfferService, lotService);
//            case "/generateURL":
//                return new URLGeneratorStrategy(lotService);
//            default:
//                return new ShowLotsStrategy(lotService);
//        }
    }
}
