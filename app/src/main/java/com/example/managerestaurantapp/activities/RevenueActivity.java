package com.example.managerestaurantapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerestaurantapp.R;
import com.example.managerestaurantapp.models.Revenue;
import com.example.managerestaurantapp.services.ApiService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevenueActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog, endDatePickerDialog;
    private Button buttonStartDate, buttonEndDate, buttonThongKe;

    private TextView textViewMaxDoanhThu, textViewMinDoanhThu;
    private ArrayList arrayList;
    private List<Revenue> lstRV = null;

    BarChart barChart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);
        initDatePicker();
        initEndDatePicker();
        addControls();
        addEvents();
        //initBarChart();

    }
    //    void initBarChart() {
//
//    }
//
//    private List<BarEntry> getBarEntries(List<Revenue> revenueData) {
//
//        return barEntries;
//    }
    private void addEvents()
    {
        buttonThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startDate = buttonStartDate.getText().toString();
                String endDate = buttonEndDate.getText().toString();
                ApiService.apiService.getMaxDoanhThu(startDate, endDate).enqueue(new Callback<Revenue>() {
                    @Override
                    public void onResponse(Call<Revenue> call, Response<Revenue> response) {
                        Toast.makeText(RevenueActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Revenue rv = response.body();
                        if (rv!= null)
                        {
                            textViewMaxDoanhThu.setText(rv.getDate());
                        }
                    }

                    @Override
                    public void onFailure(Call<Revenue> call, Throwable throwable) {
                        Toast.makeText(RevenueActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                ApiService.apiService.getMinDoanhThu(startDate, endDate).enqueue(new Callback<Revenue>() {
                    @Override
                    public void onResponse(Call<Revenue> call, Response<Revenue> response) {
                        Toast.makeText(RevenueActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Revenue rv = response.body();
                        if (rv!= null)
                        {
                            textViewMinDoanhThu.setText(rv.getDate());
                        }
                    }

                    @Override
                    public void onFailure(Call<Revenue> call, Throwable throwable) {
                        Toast.makeText(RevenueActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                ApiService.apiService.getDoanhThu(startDate, endDate).enqueue(new Callback<List<Revenue>>() {
                    @Override
                    public void onResponse(Call<List<Revenue>> call, Response<List<Revenue>> response) {
                        lstRV = response.body();
                        List<BarEntry> barEntries = new ArrayList<>();
                        for (Revenue revenue : lstRV) {
                            barEntries.add(new BarEntry(
                                    Float.parseFloat(revenue.getDate().substring(revenue.getDate().length() - 2))
                                    , Float.parseFloat(revenue.getRevenue())));


                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries,"Doanh thu");
                        BarData barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextSize(16f);
                        barChart.setFitBars(true);
                        barChart.getDescription().setEnabled(false);
                        barChart.setDrawGridBackground(false);
                        barChart.animateY(1000);
                        barChart.invalidate();
                    }

                    @Override
                    public void onFailure(Call<List<Revenue>> call, Throwable throwable) {

                    }
                });
            }
        });

    }
    String getToday()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month+=1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }
    void addControls()
    {
        buttonThongKe = (Button) findViewById(R.id.buttonThongKe);
        buttonStartDate = (Button) findViewById(R.id.buttonStartDate);
        buttonStartDate.setText(getToday());
        buttonEndDate = (Button) findViewById(R.id.buttonEndDate);
        buttonEndDate.setText(getToday());
        textViewMaxDoanhThu = (TextView) findViewById(R.id.textViewDoanhThuMax);
        textViewMinDoanhThu = (TextView) findViewById(R.id.textViewDoanhThuMin);
        barChart = (BarChart) findViewById(R.id.barchart);
    }
    void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = makeDateString(day, month, year);
                buttonStartDate.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener,year, month, day);


    }
    void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            monthOfYear += 1;
            String date = makeDateString(dayOfMonth, monthOfYear, year);
            buttonEndDate.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        endDatePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);
    }
    String makeDateString(int day, int month, int year)
    {
        return year + "-" + month + "-" + day;
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    public void openEndDate(View view)
    {
        endDatePickerDialog.show();
    }
}