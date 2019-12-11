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
