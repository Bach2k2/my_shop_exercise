package com.example.my_shop_exercise.model.entity;

import java.text.SimpleDateFormat;

public class CategoryEntity {
    private String cId;
    private String cName;

    public CategoryEntity() {
    }

    public CategoryEntity(String cId, String cName) {
        this.cId = cId;
        this.cName = cName;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
