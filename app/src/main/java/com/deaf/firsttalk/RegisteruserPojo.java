package com.deaf.firsttalk;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class RegisteruserPojo {
    private String name;
    private String password;
    public Map<String, Boolean> stars = new HashMap<>();
    public RegisteruserPojo() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("password", password);

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


    public RegisteruserPojo(String name,  String email, String password){
        this.name = name;
        this.password = password;
    }
}
