package ru.geekbrains.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionActivity extends BaseActivity implements Constants{
    private static final String TAG = "MyLogSelectionActivity";
    private String select_city = null;
    private Boolean select_wind = false;
    private Boolean select_pressure = false;
    Packet currentPacket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        SwitchCompat switchCompat = findViewById(R.id.switch_compat);
        switchCompat.setChecked(isDarkTheme());
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });


        TextView twCity = findViewById(R.id.s6);   //Здесь пока что выбран конкретный текствью с Калугой для установки слушателя
        twCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_city = twCity.getText().toString();
                int temperature = 15;              //Температура для выбранного города приходит с удаленного сервера
                int wind = 3;                      //Сила ветра для выбранного города приходит с удаленного сервера
                int pressure = 765;                //Давление для выбранного города приходит с удаленного сервера
                int humidity = 85;                 //Влажность для выбранного города приходит с удаленного сервера
                currentPacket = new Packet(temperature, select_city, wind, pressure, humidity);
                twCity.setBackgroundResource(R.color.select_city);
                twCity.setTextColor(getColor(R.color.white));
                Toast.makeText(getApplicationContext(), "SELECT_CITY выбран: "+ select_city, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Активити: SELECT_CITY выбран: "+ select_city);
            }
        });

        CheckBox cbWind = (CheckBox) findViewById(R.id.checkBox1);
        cbWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                select_wind = isChecked;
            }
        });

        CheckBox cbPressure = (CheckBox) findViewById(R.id.checkBox2);
        cbPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                select_pressure = isChecked;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "сохранение состояния SelectionActivity", Toast.LENGTH_SHORT).show();
        if(select_city !=null){
            saveInstanceState.putString(TEXT, select_city);
            saveInstanceState.putBoolean(KEY_1, select_wind);
            saveInstanceState.putBoolean(KEY_2, select_pressure);
            saveInstanceState.putParcelable(TEXT, currentPacket);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "восстановление состояния SelectionActivity", Toast.LENGTH_SHORT).show();
        select_city = saveInstanceState.getString(TEXT);
        currentPacket = saveInstanceState.getParcelable(TEXT);
        if(select_city !=null){
            if(select_city.equals("Калуга")){  //Временный хардкод, пока мы не получаем данные с сервера
                TextView twK = findViewById(R.id.s6);
                twK.setBackgroundResource(R.color.select_city);
                twK.setTextColor(getColor(R.color.white));
            }
        }
        select_wind = saveInstanceState.getBoolean(KEY_1);
        if(select_wind){
            CheckBox cb = findViewById(R.id.checkBox1);
            cb.setChecked(true);
        }
        select_pressure = saveInstanceState.getBoolean(KEY_2);
        if(select_pressure){
            CheckBox cb = findViewById(R.id.checkBox2);
            cb.setChecked(true);
        }
    }

    public void onClickButtonFind(View view) {
        if(currentPacket!=null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(TEXT, currentPacket);
            intent.putExtra(KEY_1, select_wind);
            intent.putExtra(KEY_2, select_pressure);
            startActivity(intent);
        }
    }
}