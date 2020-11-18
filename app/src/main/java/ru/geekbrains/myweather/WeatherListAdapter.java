package ru.geekbrains.myweather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder>{
    private WeekDaySource dataSource;
    private AdapterView.OnItemClickListener itemClickListener;

    public WeatherListAdapter(WeekDaySource dataSource){
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @NonNull
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Создаем новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item_layout, viewGroup, false);
        // Здесь можно установить всякие параметры
        ViewHolder vh = new ViewHolder(v);
        if (itemClickListener != null) {
            vh.setOnClickListener((OnItemClickListener) itemClickListener);
        }
        Log.d("WeatherListAdapter", "onCreateViewHolder");
        return vh;
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(@NonNull WeatherListAdapter.ViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран используя ViewHolder
        WeatherDay weatherDay = dataSource.getWeatherDay(i);
        viewHolder.setData(weatherDay.getNameDay(), weatherDay.getDataWeekday(), weatherDay.getTempDay(), weatherDay.getTempNight(), weatherDay.getImageDay(), weatherDay.getImageNight());
        Log.d("WeatherListAdapter", "onBindViewHolder");
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = (AdapterView.OnItemClickListener) itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameDay;
        private TextView dataWeekday;
        private TextView tempDay;
        private TextView tempNight;
        private ImageView imageDay;
        private ImageView imageNight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameDay = itemView.findViewById(R.id.textView_week_day);
            dataWeekday = itemView.findViewById(R.id.textView_dataWeekday);
            tempDay = itemView.findViewById(R.id.textView_tempDay);
            tempNight = itemView.findViewById(R.id.textView_tempNight);
            imageDay = itemView.findViewById(R.id.imageView_day);
            imageNight = itemView.findViewById(R.id.imageView_night);
        }

        public void setOnClickListener(final OnItemClickListener listener){
            nameDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Получаем позицию адаптера
                    int adapterPosition = getAdapterPosition();
                    // Проверяем ее на корректность
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    listener.onItemClick(v, adapterPosition);
                }
            });
        }


        public void setData(String nameDay, String dataWeekday, int tempDay, int tempNight, int idImageDay, int idImageNight){
            getNameDay().setText((nameDay));
            getDataWeekday().setText(dataWeekday);
            getTempDay().setText(String.valueOf(tempDay));
            getTempNight().setText(String.valueOf(tempNight));
            getImageDay().setImageResource(idImageDay);
            getImageNight().setImageResource(idImageNight);
        }

        public TextView getNameDay() {
            return nameDay;
        }

        public TextView getDataWeekday(){
            return dataWeekday;
        }

        public TextView getTempDay() {
            return tempDay;
        }

        public TextView getTempNight() {
            return tempNight;
        }

        public ImageView getImageDay() {
            return imageDay;
        }

        public ImageView getImageNight() {
            return imageNight;
        }
    }
}
