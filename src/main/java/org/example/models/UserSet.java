package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
    private final Set<User> users;

    public UserSet(){
        this.users=new HashSet<>();
    }

    public void add(User user){
        this.users.add(user);
    }
}
