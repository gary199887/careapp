package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DoctorReservationActivity extends AppCompatActivity {

    private ListView adr_listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reservation);

        adr_listView = (ListView) findViewById(R.id.adr_listView);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"一", "二", "三"});
        adr_listView.setAdapter(listAdapter);

        Button reservationButton = (Button)findViewById(R.id.adr_button);


        reservationButton.setOnClickListener((v) -> {
            startActivity(new Intent(DoctorReservationActivity.this, ViewDivisionActivity.class));
            finish();
        });
    }
}
