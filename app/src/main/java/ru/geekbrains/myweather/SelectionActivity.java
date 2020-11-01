package ru.geekbrains.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionActivity extends AppCompatActivity {
    private static final String TAG = "MyLogSelectionActivity";
    private String SELECT_CITY;
    private Boolean SELECT_WIND;
    private Boolean SELECT_PRESSURE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(".MainActivity");
//                startActivity(intent);
//            }
//        });

        TextView twKaluga = findViewById(R.id.s6);
        twKaluga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECT_CITY = "Калуга";
                twKaluga.setBackgroundResource(R.color.select_city);
                twKaluga.setTextColor(getColor(R.color.white));
                Toast.makeText(getApplicationContext(), "SELECT_CITY выбран", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Активити: SELECT_CITY выбран");
            }
        });

        CheckBox cbWind = (CheckBox) findViewById(R.id.checkBox1);
        cbWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SELECT_WIND = isChecked;
            }
        });

        CheckBox cbPressure = (CheckBox) findViewById(R.id.checkBox2);
        cbPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SELECT_PRESSURE = isChecked;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "сохранение состояния SelectionActivity", Toast.LENGTH_SHORT).show();
        saveInstanceState.putString("city", SELECT_CITY);
        saveInstanceState.putBoolean("wind", SELECT_WIND);
        saveInstanceState.putBoolean("pressure", SELECT_PRESSURE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "восстановление состояния SelectionActivity", Toast.LENGTH_SHORT).show();
        SELECT_CITY = saveInstanceState.getString("city");
        if(SELECT_CITY == "Калуга"){
            TextView twK = findViewById(R.id.s6);
            twK.setBackgroundResource(R.color.select_city);
            twK.setTextColor(getColor(R.color.white));
        }
        SELECT_WIND = saveInstanceState.getBoolean("wind");
        if(SELECT_WIND){
            CheckBox cb = findViewById(R.id.checkBox1);
            cb.setChecked(true);
        }
        SELECT_PRESSURE = saveInstanceState.getBoolean("pressure");
        if(SELECT_PRESSURE){
            CheckBox cb = findViewById(R.id.checkBox2);
            cb.setChecked(true);
        }
    }


    public void onClickButtonFind(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city", SELECT_CITY);
        intent.putExtra("wind", SELECT_WIND);
        intent.putExtra("pressure", SELECT_PRESSURE);
        startActivity(intent);
    }
}