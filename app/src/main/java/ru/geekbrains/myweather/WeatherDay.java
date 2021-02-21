package ru.geekbrains.myweather;

public class WeatherDay {
    private String nameDay;
    private String dataWeekday;
    private int tempDay;
    private int tempNight;
    private int imageDay;
    private int imageNight;

    public WeatherDay(String nameDay, String dataWeekday, int tempDay, int tempNight, int idImageDay, int idImageNight) {
        this.nameDay = nameDay;
        this.dataWeekday = dataWeekday;
        this.tempDay = tempDay;
        this.tempNight = tempNight;
        this.imageDay = idImageDay;
        this.imageNight = idImageNight;
    }

    public String getNameDay() {
        return nameDay;
    }

    public String getDataWeekday() {
        return dataWeekday;
    }

    public int getTempDay() {
        return tempDay;
    }

    public int getTempNight() {
        return tempNight;
    }

    public int getImageDay() {
        return imageDay;
    }

    public int getImageNight() {
        return imageNight;
    }
}
