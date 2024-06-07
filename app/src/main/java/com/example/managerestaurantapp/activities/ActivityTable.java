package com.example.managerestaurantapp.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.managerestaurantapp.R;
import com.example.managerestaurantapp.adapters.VPAdapterTable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityTable extends AppCompatActivity {

    TabLayout tabLayoutFloor;
    ViewPager2 vpTable;
    VPAdapterTable adapterTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tabLayoutFloor = (TabLayout) findViewById(R.id.tabLayoutFloor);
        vpTable = (ViewPager2) findViewById(R.id.vpTable);

        adapterTable = new VPAdapterTable(this);
        vpTable.setAdapter(adapterTable);

        new TabLayoutMediator(tabLayoutFloor, vpTable, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Tầng Trệt");
                        break;
                    case 1:
                        tab.setText("Tầng 1");
                        break;
                    default:
                        break;
                }
            }
        }).attach();
    }
}