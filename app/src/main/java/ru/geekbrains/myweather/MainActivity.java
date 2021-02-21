package ru.geekbrains.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants{
    private static final String TAG = "MyLog";
    private final String site = "https://www.calend.ru/events/";
    static Packet currentPacket;
    MainFragment mainFragment;

    @SuppressLint({"SetTextI18n", "StringFormatMatches", "StringFormatInvalid"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPacket = getIntent().getParcelableExtra(TEXT);
        android.app.Fragment f = getFragmentManager().findFragmentById(R.id.weather_fragment);
        ((TextView) f.getView().findViewById(R.id.temperature)).setText(getString(R.string.temperature, currentPacket.getTemperature()));
        ((TextView) f.getView().findViewById(R.id.viewСity)).setText(currentPacket.getCityName());
        ((TextView) f.getView().findViewById(R.id.wind)).setText(getString(R.string.wind, currentPacket.getWind()));
        ((TextView) f.getView().findViewById(R.id.pressure)).setText(getString(R.string.pressure, currentPacket.getPressure()));
        ((TextView) f.getView().findViewById(R.id.humidity)).setText(getString(R.string.humidity, currentPacket.getHumidity()));

        Button button = findViewById(R.id.goToUrl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(site);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });

        Button buttonSearch = findViewById(R.id.search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Активити: onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.d(TAG, "Активити: Повторный запуск! - onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Активити: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Активити: onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "Активити: onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Активити: onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Активити: onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Активити: onDestroy()");
    }


}