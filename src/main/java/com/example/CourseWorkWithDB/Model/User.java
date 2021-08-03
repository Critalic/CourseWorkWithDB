package com.example.CourseWorkWithDB.Model;

public class User {
    private long id;
    private String name;
    private String login;
    private String passwordHash;

    public User(long id, String name, String login, String passwordHash) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public User(String name, String login, String passwordHash) {
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        return getPasswordHash().equals(user.getPasswordHash());
    }
}
