package ru.geekbrains.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionActivity extends AppCompatActivity implements Constants{
    private static final String TAG = "MyLogSelectionActivity";
    private String SELECT_CITY = null;
    private Boolean SELECT_WIND = false;
    private Boolean SELECT_PRESSURE = false;
    Packet currentPacket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);


        TextView twSity = findViewById(R.id.s6);   //Здесь пока что выбран конкретный текствью с Калугой для установки слушателя
        twSity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECT_CITY = twSity.getText().toString();
                int temperature = 15;              //Температура для выбранного города приходит с удаленного сервера
                int wind = 3;                      //Сила ветра для выбранного города приходит с удаленного сервера
                int pressure = 765;                //Давление для выбранного города приходит с удаленного сервера
                int humidity = 85;                 //Влажность для выбранного города приходит с удаленного сервера
                currentPacket = new Packet(temperature, SELECT_CITY, wind, pressure, humidity);
                twSity.setBackgroundResource(R.color.select_city);
                twSity.setTextColor(getColor(R.color.white));
                Toast.makeText(getApplicationContext(), "SELECT_CITY выбран: "+SELECT_CITY, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Активити: SELECT_CITY выбран: "+SELECT_CITY);
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
        if(SELECT_CITY!=null){
            saveInstanceState.putString(TEXT, SELECT_CITY);
            saveInstanceState.putBoolean(KEY_1, SELECT_WIND);
            saveInstanceState.putBoolean(KEY_2, SELECT_PRESSURE);
            saveInstanceState.putParcelable(TEXT, currentPacket);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "восстановление состояния SelectionActivity", Toast.LENGTH_SHORT).show();
        SELECT_CITY = saveInstanceState.getString(TEXT);
        currentPacket = saveInstanceState.getParcelable(TEXT);
        if(SELECT_CITY!=null){
            if(SELECT_CITY.equals("Калуга")){
                TextView twK = findViewById(R.id.s6);
                twK.setBackgroundResource(R.color.select_city);
                twK.setTextColor(getColor(R.color.white));
            }
        }
        SELECT_WIND = saveInstanceState.getBoolean(KEY_1);
        if(SELECT_WIND){
            CheckBox cb = findViewById(R.id.checkBox1);
            cb.setChecked(true);
        }
        SELECT_PRESSURE = saveInstanceState.getBoolean(KEY_2);
        if(SELECT_PRESSURE){
            CheckBox cb = findViewById(R.id.checkBox2);
            cb.setChecked(true);
        }
    }

    public void onClickButtonFind(View view) {
        if(currentPacket!=null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(TEXT, currentPacket);
            intent.putExtra(KEY_1, SELECT_WIND);
            intent.putExtra(KEY_2, SELECT_PRESSURE);
            startActivity(intent);
        }
    }
}