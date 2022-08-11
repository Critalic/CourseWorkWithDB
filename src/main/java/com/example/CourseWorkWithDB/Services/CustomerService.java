package com.example.CourseWorkWithDB.Services;

import com.example.CourseWorkWithDB.DAO.JPA.DAO;
import com.example.CourseWorkWithDB.DAO.JPA.DAOFactory;
import com.example.CourseWorkWithDB.Entity.Customer;
import com.example.CourseWorkWithDB.Validators.Email;
import com.example.CourseWorkWithDB.Validators.LastPairMatch;
import com.example.CourseWorkWithDB.Validators.Name;

import javax.validation.constraints.Size;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class CustomerService implements BusinessService {
    private final DAO<Customer> customerDAO;

    public CustomerService(DAOFactory daoFactory) {
        this.customerDAO = daoFactory.getDAO(Customer.class);
    }

    public Customer logIn(@Email String login,
                          @Size(min = 7, message = "password must be at least 7 characters long") String password)
        throws NoSuchAlgorithmException {
        List<Customer> customers = customerDAO.getAll(new Customer().setEmail(login));
        Customer customer = customers.size() == 1 ? customers.get(0) : null;

        if (Objects.isNull(customer)) {
            throw new IllegalArgumentException("Failed to find the specified user - " + login);
        }

        if (!customer.getPasswordHash().equals(getHash(password))) {
            throw new IllegalArgumentException("Specified password doesn't match our records");
        }
        return new Customer().setId(customer.getId()).setEmail(customer.getEmail()).setName(customer.getName());
    }

    @LastPairMatch(message = "Passwords do not match")
    public void signUp(@Email String login, @Name String name, String password,
                       @Size(min = 7, message = "password must be at least 7 characters long") String password2)
            throws NoSuchAlgorithmException {
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

    public Integer getSumOfRecords() {
        return 2;
    }
}