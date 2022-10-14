package ru.lab3.Models;
import javax.validation.constraints.Size;

public class User {
    private String name;

    @Size(min = 1, message="required")
    private String pass;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}
