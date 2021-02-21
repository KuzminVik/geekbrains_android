package ru.geekbrains.myweather;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String NAME_SHARED_PREFERENCE = "SHARED";
    private static final String IS_DARK_THEME = "IS_DARK_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            setTheme(R.style.Theme_MyWeather_Dark);
        } else {
            setTheme(R.style.Theme_MyWeather);
        }
    }

    protected boolean isDarkTheme() {
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию true
        return sharedPref.getBoolean(IS_DARK_THEME, false);
    }

    // Сохранение настроек
    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_DARK_THEME, isDarkTheme);
        editor.apply();
    }
}
