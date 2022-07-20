package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Validators.EmailValidator;
import com.example.CourseWorkWithDB.Validators.EmptyValidator;

import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class CustomerService {
    private final DAO<Customer> customerDAO;

    public CustomerService(DAOFactory daoFactory) {
        this.customerDAO = daoFactory.getDAO(Customer.class);
    }

    public Customer logIn(String login, String password) throws NoSuchAlgorithmException {
        EmptyValidator.checkIfEmpty(login, "LogIn");
        password = EmptyValidator.checkIfEmpty(password, "Password"); //TODO refactor

        List<Customer> customers = customerDAO.getAll(new Customer().setEmail(login));
        Customer customer = customers.size() == 1 ? customers.get(0) : null;

        if (Objects.isNull(customer)) {
            throw new RuntimeException("Data base error");
        }

        password = getHash(password);

        if (!customer.getPasswordHash().equals(password)) {
            throw new WrongPasswordException("Incorrect password!");
        }
        return customer;
    }

    public void signUp(String login, String name, String password, String password2) throws NoSuchAlgorithmException {
        login = EmptyValidator.checkIfEmpty(login, "Login");
        name = EmptyValidator.checkIfEmpty(name, "Name");
        password = EmptyValidator.checkIfEmpty(password, "Password");
        password2 = EmptyValidator.checkIfEmpty(password2, "Retyped password");

        if (!password.equals(password2)) {
            throw new WrongPasswordException("Password mismatch!");
        }

        EmailValidator.validate(login);
        customerDAO.save(new Customer(name, login, getHash(password)));
    }

    private String getHash(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}