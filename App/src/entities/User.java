package entities;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String password;
    private String username;

    private List<Vote> votes;
    public List<Vote> getVotesList() {
        return votes;
    }

    public void setVotesList(List<Vote> votes) {
        this.votes = votes;
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

