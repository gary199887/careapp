package org.changken.careapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView dataTextView;
    Button getDataButton, addDataButton;

    /**
     * 初始化相關View元件
     */
    protected void initial() {
        dataTextView = (TextView) findViewById(R.id.data_text_view);
        getDataButton = (Button) findViewById(R.id.get_data_button);
        addDataButton = (Button) findViewById(R.id.add_data_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化元件
        initial();

        //List按鈕的邏輯
        getDataButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });

        //Add按鈕的邏輯
        addDataButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
            startActivity(intent);
        });
    }
}
