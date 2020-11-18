package ru.geekbrains.myweather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment implements Constants{
    private static final String TAG = "MyLog";
    private Packet currentPacket;     //Текущий город и данные о его погоде
    private TextView tmp;
    private TextView name;
    private TextView wind;
    private TextView pressure;
    private TextView humidity;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
//        setRetainInstance(true);
        if(getArguments() != null){
            currentPacket = getArguments().getParcelable(KEY);
            Log.d(TAG, String.valueOf("Вывели температуру в фрагменте "+currentPacket.getTemperature()));
        }else{
            Log.d(TAG, "getArguments() == null");
        }
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        useCurrentPacket();
    }

public void useCurrentPacket (){
    if(currentPacket!= null){
        tmp = getActivity().findViewById(R.id.temperature);
        tmp.setText(getString(R.string.temperature, currentPacket.getTemperature()));
        name = getActivity().findViewById(R.id.viewСity);
        name.setText(currentPacket.getCityName());
        wind = getActivity().findViewById(R.id.wind);
        wind.setText(getString(R.string.wind, currentPacket.getWind()));
        pressure = getActivity().findViewById(R.id.pressure);
        pressure.setText(getString(R.string.pressure, currentPacket.getPressure()));
        humidity = getActivity().findViewById(R.id.humidity);
        humidity.setText(getString(R.string.humidity, currentPacket.getHumidity()));
    }else{
        Log.d(TAG, "currentPacket == null");
    }
}
}
