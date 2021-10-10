package com.mitskevich.course_7sem.model.enums;

public enum Gender {
    MALE("Мужской"), FEMALE("Женский");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
