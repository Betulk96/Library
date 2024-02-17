package com.BK.Library.entity.user;

public enum RoleName {
    ADMIN("Admin"),
    MEMBER("Üye"),
    EMPLOYEE("Çalışan");

    public final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
