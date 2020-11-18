package ru.geekbrains.myweather;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class WeekDaySource {

    private List<WeatherDay> dataSource;   // строим этот источник данных
    private Resources resources;    // ресурсы приложения

    public WeekDaySource(Resources resources) {
        dataSource = new ArrayList<>(7);
        this.resources = resources;
    }

    public WeekDaySource build(){
        // строки описаний из ресурсов
        String[] nameDay = resources.getStringArray(R.array.weekday);
        String[] dataWeekday = resources.getStringArray(R.array.data_weekday);
        int tempDay = 12;
        int tempNight = 5;
        // изображения ночью
        int[] imageNight = getImageArray(resources.obtainTypedArray(R.array.pictures_weather_night));
        // изображения днем
        int[] imageDay = getImageArray(resources.obtainTypedArray(R.array.pictures_weather_day));
        // заполнение источника данных
        for (int i = 0; i < nameDay.length; i++) {
            dataSource.add(new WeatherDay(nameDay[i], dataWeekday[i], tempDay, tempNight, imageNight[i], imageDay[i]));
        }
        return this;
    }

    public WeatherDay getWeatherDay(int position) {
        return dataSource.get(position);
    }

    public int size(){
        return dataSource.size();
    }

    private int[] getImageArray(TypedArray pictures){
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        return answer;
    }
}
