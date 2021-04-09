package com.koaladev.ruikartiffintracker.Model;

public class Record {
    private int id;
    private String date;
    private String lunch="No";
    private String dinner="No";
    private int quantity=0;
    private int rate=0;


    //Constructors
    public Record() {
    }

    public Record(String date, String lunch, String dinner, int quantity, int rate) {
        this.date = date;
        this.lunch = lunch;
        this.dinner = dinner;
        this.quantity = quantity;
        this.rate = rate;
    }

    public Record(int id, String date, String lunch, String dinner, int quantity, int rate) {
        this.id = id;
        this.date = date;
        this.lunch = lunch;
        this.dinner = dinner;
        this.quantity = quantity;
        this.rate = rate;
    }

    //Getter and Setter Methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
