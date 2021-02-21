package ru.geekbrains.myweather;

import android.os.Parcelable;

public class Packet implements Parcelable {
    private int temperature;
    private String cityName;
    private int wind;
    private int pressure;
    private int humidity;

    public int getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public int getWind() {
        return wind;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public Packet(int temperature, String cityName, int wind, int pressure, int humidity) {
        this.temperature = temperature;
        this.cityName = cityName;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    protected Packet(android.os.Parcel in) {
        this.temperature = in.readInt();
        this.cityName = in.readString();
        this.wind = in.readInt();
        this.pressure = in.readInt();
        this.humidity = in.readInt();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeInt(temperature);
        dest.writeString(cityName);
        dest.writeInt(wind);
        dest.writeInt(pressure);
        dest.writeInt(humidity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Packet> CREATOR = new Creator<Packet>() {
        @Override
        public Packet createFromParcel(android.os.Parcel in) {
            return new Packet(in);
        }

        @Override
        public Packet[] newArray(int size) {
            return new Packet[size];
        }
    };
}
