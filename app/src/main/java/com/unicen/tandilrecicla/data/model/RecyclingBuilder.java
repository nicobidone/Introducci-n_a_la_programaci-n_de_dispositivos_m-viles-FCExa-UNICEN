package com.unicen.tandilrecicla.data.model;

public class RecyclingBuilder {

    public static Recycling getRecyclingEmpty() {
        Recycling recycling = new Recycling();
        recycling.setBottles(0);
        recycling.setCans(0);
        recycling.setGlass(0);
        recycling.setPaperboard(0);
        recycling.setTetrabriks(0);
        recycling.setDate("");
        recycling.setId("");
        return recycling;
    }

    static public Recycling getRecycling() {
        Recycling recycling = new Recycling();
        recycling.setBottles(1);
        recycling.setTetrabriks(5);
        recycling.setGlass(3);
        recycling.setPaperboard(4);
        recycling.setCans(2);
        recycling.setDate("2018-11-29");
        return recycling;
    }

}
