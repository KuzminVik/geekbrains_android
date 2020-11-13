package ru.geekbrains.myweather;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment implements Constants{
    static Packet currentPacket;     //Текущий город и его температура в фрагменте

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
//        currentPacket = savedInstanceState.getParcelable(TEXT);
//        TextView tw1 = layout.findViewById(R.id.temperature);
//        tw1.setText(currentPacket.getTemperature());
//        TextView tw2 = layout.findViewById(R.id.viewСity);
//        tw2.setText(currentPacket.getCityName());
//        TextView tw3 = layout.findViewById(R.id.wind);
//        tw3.setText(currentPacket.getWind());
//        TextView tw4 = layout.findViewById(R.id.pressure);
//        tw4.setText(currentPacket.getPressure());
//        TextView tw5 = layout.findViewById(R.id.humidity);
//        tw5.setText((currentPacket.getHumidity()));
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
