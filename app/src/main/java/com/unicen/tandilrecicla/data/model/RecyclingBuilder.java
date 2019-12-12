package com.unicen.tandilrecicla.data.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    static public Recycling getRecycling(int bottles, int glass, int paperboard, int tetrabriks, int cans) {
        Recycling recycling = new Recycling();
        recycling.setBottles(bottles);
        recycling.setPaperboard(paperboard);
        recycling.setGlass(glass);
        recycling.setTetrabriks(tetrabriks);
        recycling.setCans(cans);
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        recycling.setDate(formatter.format(todayDate));
        return recycling;
    }

}
