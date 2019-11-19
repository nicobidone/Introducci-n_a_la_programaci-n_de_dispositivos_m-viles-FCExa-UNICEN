package com.unicen.tandilrecicla.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.unicen.tandilrecicla.data.HomeRepository;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private HomeRepository homeRepository;

    private PieChart chart;
    private PieData data;

    private Typeface tfRegular;
    private Typeface tfLight;

    HomeViewModel(HomeRepository homeRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        this.homeRepository = homeRepository;
    }

    public LiveData<String> getText() {
        return mText;
    }

    LiveData<ResponseBody> getTotalRecycling(String id) {
        return homeRepository.makeReactiveQuery(id);
    }

    void setChartConfiguration(PieChart chart) {

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 5, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfLight);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);

        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        setData(parties.length, 10);

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Total recycling");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        data = new PieData(dataSet);

//        Pie Chart without percent
        data.setValueFormatter(new DefaultValueFormatter(0));
        chart.setUsePercentValues(false);

        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        // actionToggleCurvedSlices
        boolean toSet = !chart.isDrawRoundedSlicesEnabled() || !chart.isDrawHoleEnabled();
        chart.setDrawRoundedSlices(toSet);
        if (toSet && !chart.isDrawHoleEnabled()) {
            chart.setDrawHoleEnabled(true);
        }
        if (toSet && chart.isDrawSlicesUnderHoleEnabled()) {
            chart.setDrawSlicesUnderHole(false);
        }
        chart.invalidate();
    }

    private final String[] parties = new String[]{"Bottles", "Tetrabriks", "Glass", "Paperboard", "Cans"};

    void setTypefaces(Typeface tfRegular, Typeface tfLight) {
        this.tfRegular = tfRegular;
        this.tfLight = tfLight;
    }

    void setPieChart(PieChart chart) {
        this.chart = chart;
    }

    void changeDisplayUserValues() {
        boolean usePercentValuesEnabled = chart.isUsePercentValuesEnabled();
        if (!usePercentValuesEnabled) {
            data.setValueFormatter(new PercentFormatter(chart));
        } else {
            data.setValueFormatter(new DefaultValueFormatter(0));
        }
        chart.setUsePercentValues(!usePercentValuesEnabled);
        chart.invalidate();
    }
}
