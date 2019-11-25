package com.unicen.tandilrecicla.data.model;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.unicen.tandilrecicla.R;

public class Utils {

    static public String[] getRecyclingPointsAddress() {
        return new String[]{
                "Estación Centro: A.Santamarina 460",
                "Estación Oeste: Av. Lunghi 1950",
                "Estación Norte: Darragueira y Jurado",
                "Estación Vela: Corralon Municipal",
                "Estación Gardey: Corralon Municipal"
        };
    }

    static public RegisteredUser getRegisteredUser() {
        Address address = new Address();
        address.setDepartment("Tandil");
        address.setCity("Tandil");
        address.setNumber(874);
        address.setStreetAddress("Alberdi");
        address.setCity("Tandil");
        address.setState("Buenos Aires");
        address.setZipCode("7000");
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setFirstName("Mauri");
        registeredUser.setLastName("Arroqui");
        registeredUser.setMail("mauriarroqui@gmail.com");
        registeredUser.setUsername("nicob");
        registeredUser.setAddress(address);
        return registeredUser;
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

    static public Integer[] getRecyclingIcons() {
        return new Integer[]{
                R.drawable.ic_024_plastic,
                R.drawable.ic_026_paper_bin,
                R.drawable.ic_027_glass_bin,
                R.drawable.ic_044_pack_of_milk,
                R.drawable.ic_028_metal};
    }

    static public Integer[] getGreyRecyclingIcons() {
        return new Integer[]{
                R.drawable.ic_024_plastic_gs,
                R.drawable.ic_026_paper_bin_gs,
                R.drawable.ic_027_glass_bin_gs,
                R.drawable.ic_044_pack_of_milk_gs,
                R.drawable.ic_028_metal_gs};
    }

    static public String[] getRecyclingNames() {
        return new String[]{
                "Bottles",
                "Paperboard",
                "Glass",
                "Tetrabriks",
                "Cans"};
    }

    static public int[] getVordiplomColors() {
        return ColorTemplate.VORDIPLOM_COLORS;
    }
}
