package entity;

import base.domain.BaseEntity;


@SuppressWarnings("unused")
public class User extends BaseEntity<Integer> {
    private String name;
    private String username;
    private String password;

    public User(Integer integer, String name, String username, String password) {
        super(integer);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(Integer integer) {
        super(integer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
