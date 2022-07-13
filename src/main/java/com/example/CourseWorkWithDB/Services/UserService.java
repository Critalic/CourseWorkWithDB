package com.example.CourseWorkWithDB.Services;

public class UserService {
//    private final EntityManagerFactory managerFactory;
//
//    public UserService(EntityManagerFactory managerFactory) {
//        this.managerFactory = managerFactory;
//    }
//
//    public Customer logIn(String login, String password) throws SQLException, NoSuchAlgorithmException, WrongPasswordException {
//        EmptyValidator.checkIfEmpty(login, "LogIn");
//
//        EntityManager manager = managerFactory.createEntityManager();
//
//        Query query = manager.createNativeQuery("select * from customer where email = ?;");
//
//        Customer user = managerFactory.getUserDAO().getUser(login);
//
//        password = EmptyValidator.checkIfEmpty(password, "Password");
//        password = getHash(password);
//
//        if (!user.getPasswordHash().equals(password)) {
//            throw new WrongPasswordException("Incorrect password!");
//        }
//        return user;
//    }
//
//    public void signUp(String login, String name, String password, String password2) throws
//            NoSuchAlgorithmException, SQLException, WrongPasswordException, InvalidEmailException, NullPointerException, IllegalArgumentException {
//        login = EmptyValidator.checkIfEmpty(login, "Login");
//        name = EmptyValidator.checkIfEmpty(name, "Name");
//        password = EmptyValidator.checkIfEmpty(password, "Password");
//        password2 = EmptyValidator.checkIfEmpty(password2, "Retyped password");
//
//        if (!password.equals(password2)) {
//            throw new WrongPasswordException("Password mismatch!");
//        }
//
//        EmailValidator.validate(login);
//        managerFactory.getUserDAO().createUser(
//                new Customer(name, login, getHash(password))
//        );
//    }
//    private String getHash(String passwordToHash) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(passwordToHash.getBytes());
//        byte[] bytes = md.digest();
//
//        StringBuilder sb = new StringBuilder();
//        for (byte aByte : bytes) {
//            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
//        }
//
//        return sb.toString();
//    }
}