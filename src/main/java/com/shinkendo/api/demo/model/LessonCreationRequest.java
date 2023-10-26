package com.shinkendo.api.demo.model;

import java.util.List;
import java.util.UUID;

public class LessonCreationRequest {
    private String name;
    private List<UUID> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getUsers() {
        return users;
    }

    public void setUsers(List<UUID> users) {
        this.users = users;
    }
}
