package ru.geekbrains.myweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends BaseActivity implements Constants{
    private static final int SETTING_CODE = 88;
    private static final String TAG = "MyLog";
    private final String site = "https://www.calend.ru/events/";
    static Packet currentPacket;
    private Toolbar toolbar;

    @SuppressLint({"SetTextI18n", "StringFormatMatches", "StringFormatInvalid"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Здесь вылетит Snackbar
                Snackbar.make(view, "Вы нажали на плавающую кнопку", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        if(DEBUG){
            Log.v(TAG, "Активити: onStart()");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        currentPacket = saveInstanceState.getParcelable(TEXT);
        if(DEBUG){
            Log.v(TAG, "Активити: Повторный запуск! - onRestoreInstanceState()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putParcelable(TEXT, currentPacket);
        if(DEBUG){
            Log.v(TAG, "Активити: onSaveInstanceState()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    //Метод recreate() пересоздаст активити, тем самым применит новую тему.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_CODE){
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // разместить меню в action bar
        // если он присутствует.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Управление касаниями на action bar.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_home:
                Snackbar.make(toolbar, "Вы выбрали пункт меню Главная", Snackbar.LENGTH_LONG)
                        .setAction("Кнопка", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this.getApplicationContext(), "Кнопка в Snackbar нажата", Toast.LENGTH_LONG).show();
                            }
                        }).show();
                return true;
            case R.id.action_settings:
                Snackbar.make(toolbar, "Вы выбрали пункт меню Настройки", Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_devs:
                Snackbar.make(toolbar, "Вы выбрали пункт меню О разработчиках", Snackbar.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}