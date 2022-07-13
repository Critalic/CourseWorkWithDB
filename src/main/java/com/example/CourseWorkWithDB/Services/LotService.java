package com.example.CourseWorkWithDB.Services;

public class LotService {
//    private final EntityManagerFactory managerFactory;
//
//    public LotService(EntityManagerFactory managerFactory) {
//        this.managerFactory = managerFactory;
//    }
//
//    public List<Lot> getLots() throws SQLException {
//        return managerFactory.getLotDAO().getAll();
//    }
//
//    public List<Lot> getLotsWithOwner(long ownerID) throws SQLException {
//        return managerFactory.getLotDAO().getAllWithOwner(ownerID);
//    }
//
//    public List<LotOffer> getOffers (long lotId) throws SQLException {
//        return managerFactory.getLotOfferDAO().getAllForLot(lotId);
//    }
//
//    public List<Lot> getLotsByName(String name) throws SQLException {
//        name = EmptyValidator.checkIfEmpty(name, "Name");
//        return managerFactory.getLotDAO().getAllThatContain(name);
//    }
//
//    public Lot getLotById(long id) throws SQLException {
//        ExampleMatcher
//        return managerFactory.getLotDAO().getLot(id);
//    }
//
//    public void setLotStatus(long lotId, boolean value)
//            throws SQLException {
//        managerFactory.getLotDAO().updateStatus(lotId, value);
//    }
//
//    public void deleteLot(long lotId, Customer owner) throws SQLException {
//            managerFactory.getLotDAO().deleteLot(lotId);
//    }
//
//    public String generateURL () {
//        return  UUID.randomUUID().toString();
//    }
//
//    public void createNewLot(long ownerID, String name, String description, int startPrice)
//            throws LessThanZeroException, SQLException, IllegalArgumentException, NullPointerException {
//        name = EmptyValidator.checkIfEmpty(name, "Name");
//        description = EmptyValidator.checkIfEmpty(description, "Description");
//        startPrice = (int) NumberValidator.moreThanZero(startPrice, "Start price");
//        managerFactory.getLotDAO().addLot(new Lot(name, description, startPrice, ownerID));
//    }

}
