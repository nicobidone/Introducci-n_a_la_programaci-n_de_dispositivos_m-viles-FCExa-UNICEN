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
import com.github.mikephil.charting.utils.MPPointF;
import com.unicen.tandilrecicla.data.HomeRepository;
import com.unicen.tandilrecicla.data.model.Recycling;
import com.unicen.tandilrecicla.data.model.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private HomeRepository homeRepository;

    private PieChart chart;
    private PieData data;

    private Typeface tfRegular;
    private Typeface tfLight;

    private List<Integer> values;

    HomeViewModel(HomeRepository homeRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        this.homeRepository = homeRepository;
        this.values = null;
    }

    void setChartValues(List<Integer> values) {
        this.values = values;
    }

    boolean hasValues() {
        return values != null;
    }

    public LiveData<String> getText() {
        return mText;
    }

    LiveData<Recycling> getTotalRecyclingData(String id) {
        return homeRepository.getTotalRecyclingQuery(id);
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

        setData();

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(75f);
        l.setWordWrapEnabled(true);

        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        boolean showValues = false;
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) > 0) {
                entries.add(new PieEntry((float) values.get(i), parties[i % parties.length]));
                showValues = true;
            }
        }

        if (entries.size() == 0) {
            entries.add(new PieEntry(1, "No recycling"));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Total recycling");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        dataSet.setColors(Utils.getVordiplomColors());
        //dataSet.setSelectionShift(0f);

        data = new PieData(dataSet);

//        Pie Chart without percent
        data.setValueFormatter(new DefaultValueFormatter(0));
        chart.setUsePercentValues(false);

        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tfLight);
        data.setDrawValues(showValues);
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

    private final String[] parties = Utils.getRecyclingNames();

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
