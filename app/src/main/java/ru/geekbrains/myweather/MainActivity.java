package ru.geekbrains.myweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements Constants{
    private static final String TAG = "MyLog";
    private final String site = "https://www.calend.ru/events/";
    static Packet currentPacket;

    @SuppressLint({"SetTextI18n", "StringFormatMatches", "StringFormatInvalid"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получаем пакет от активити с выбором городов и передаем его в бандле в фрагмент
        if (savedInstanceState == null){
            currentPacket = getIntent().getParcelableExtra(TEXT);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            MainFragment f = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY, currentPacket);
            f.setArguments(bundle);
            ft.replace((R.id.weather_framelayout), f).commit();

        }

        WeekDaySource sourceData = new WeekDaySource(getResources());
        initRecyclerView(sourceData.build());

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
        currentPacket = saveInstanceState.getParcelable(TEXT);

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
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putParcelable(TEXT, currentPacket);
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

    private void initRecyclerView(WeekDaySource sourceData){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
// Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // Установим адаптер
        WeatherListAdapter adapter = new WeatherListAdapter(sourceData);
        recyclerView.setAdapter(adapter);
    }
}