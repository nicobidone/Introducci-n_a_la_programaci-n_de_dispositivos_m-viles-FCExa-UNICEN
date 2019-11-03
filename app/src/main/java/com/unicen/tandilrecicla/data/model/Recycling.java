package com.unicen.tandilrecicla.data.model;

public class Recycling {

    private int bottles;
    private int tetrabriks;
    private int glass;
    private int paperboard;
    private int cans;
    private String date;
    private String id;

    // Getters
    public int getBottles() {
        return bottles;
    }

    public int getTetrabriks() {
        return tetrabriks;
    }

    public int getGlass() {
        return glass;
    }

    public int getPaperboard() {
        return paperboard;
    }

    public int getCans() {
        return cans;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    // Setters
    public void setBottles(int bottles) {
        this.bottles = bottles;
    }

    public void setTetrabriks(int tetrabriks) {
        this.tetrabriks = tetrabriks;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public void setPaperboard(int paperboard) {
        this.paperboard = paperboard;
    }

    public void setCans(int cans) {
        this.cans = cans;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }
}
