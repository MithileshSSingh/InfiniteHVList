package com.example.hvlist.mvp.beans;

import java.util.ArrayList;

/**
 * Created by mithilesh on 4/24/17.
 */
public class BeanDateDetail {

    private String date;
    private Boolean isSelected;
    private ArrayList<String> detail;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public ArrayList<String> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<String> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "BeanDateDetail{" +
                "date='" + date + '\'' +
                ", isSelected=" + isSelected +
                ", detail=" + detail +
                '}';
    }
}
