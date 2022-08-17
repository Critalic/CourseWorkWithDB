package com.example.cw.controllers;

import com.example.cw.controllers.Strats.*;
import com.example.cw.services.CustomerService;
import com.example.cw.services.LotOfferService;
import com.example.cw.services.LotService;
import com.example.cw.services.ValidatorService;

import javax.validation.ValidatorFactory;


public class StrategySelector {

    private final CustomerService customerService;
    private final LotService lotService;
    private final LotOfferService lotOfferService;
    private final ValidatorService validatorService;

    public StrategySelector(CustomerService customerService, LotService lotService, LotOfferService lotOfferService,
                            ValidatorFactory validatorFactory) {
        this.customerService = customerService;
        this.lotService = lotService;
        this.lotOfferService = lotOfferService;
        this.validatorService = new ValidatorService(validatorFactory);
    }

    public SomeStrat getStrategy(String path) {
        switch (path) {
            case "/logIn":
                return new LogInStrategy(customerService, validatorService);
            case "/signUp":
                return new SignUpStrategy(customerService, validatorService);
            case "/createLot":
                return new NewLotStrategy(lotService, validatorService);
            case "/mainPage":
                return new MainPageStrategy(lotService);
            case "/viewLot":
                return new ViewLotStrategy(lotService);
            case "/editLot":
                return new LotEditingStrategy(lotService, lotOfferService);
            case "/logOut":
                return new LogOutStrategy(lotService);
            case "/changeStatus":
                return new ChangeStatusStrategy(lotService);
            case "/searchLot":
                return new SearchLotStrategy(lotService);
            case "/deleteLot":
                return new DeleteLotStrategy(lotService);
            case "/makeOffer":
                return new MakeOfferStrategy(lotOfferService, lotService);
            case "/generateURL":
                return new URLGeneratorStrategy(lotService);
            default:
                return new ShowLotsStrategy(lotService);
        }

    }
}
