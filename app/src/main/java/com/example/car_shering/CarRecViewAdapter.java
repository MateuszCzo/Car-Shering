package com.example.car_shering;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRecViewAdapter extends RecyclerView.Adapter<CarRecViewAdapter.ViewHolder> {

    private ArrayList<Car> cars = new ArrayList<>();
    private Context context;

    public CarRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.typ_nadwozia.setText(cars.get(position).getTyp_nadwozia());
        holder.marka_pojazdu.setText(cars.get(position).getMarka_pojazdu());
        holder.cena.setText(Integer.toString(cars.get(position).getCana()));
        holder.pojemnosc.setText(Float.toString(cars.get(position).getPojemnosc()));
        holder.rodzaj_paliwa.setText(cars.get(position).getRodzaj_paliwa());
        holder.przebieg.setText(Integer.toString(cars.get(position).getPrzebieg()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp","car clicked id:" + cars.get(position).getId_pojazdu());
                Intent intent = new Intent(context, ContactActivity.class);
                intent.putExtra("car_id", cars.get(position).getId_pojazdu());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout parent;
        private TextView typ_nadwozia;
        private TextView marka_pojazdu;
        private TextView cena;
        private TextView pojemnosc;
        private TextView rodzaj_paliwa;
        private TextView przebieg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typ_nadwozia = itemView.findViewById(R.id.typ_nadwozia);
            marka_pojazdu = itemView.findViewById(R.id.marka_pojazdu);
            cena = itemView.findViewById(R.id.cena);
            pojemnosc = itemView.findViewById(R.id.pojemnosc);
            rodzaj_paliwa = itemView.findViewById(R.id.rodzaj_paliwa);
            przebieg = itemView.findViewById(R.id.przebieg);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
