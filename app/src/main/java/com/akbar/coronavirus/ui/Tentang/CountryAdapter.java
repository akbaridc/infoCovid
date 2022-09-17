package com.akbar.coronavirus.ui.Tentang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akbar.coronavirus.R;
import com.akbar.coronavirus.ui.Provinsi.CovidProvinsiAdapter;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    List<CountryModel> mItems;
    private Context context;

    public CountryAdapter(Context context, List<CountryModel> mItems) {
        this.mItems = mItems;
        this.context = context;

    }
    @NonNull
    @Override
    public com.akbar.coronavirus.ui.Tentang.CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_provinsi ,parent,false);
        com.akbar.coronavirus.ui.Tentang.CountryAdapter.ViewHolder holderData = new com.akbar.coronavirus.ui.Tentang.CountryAdapter.ViewHolder(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull com.akbar.coronavirus.ui.Tentang.CountryAdapter.ViewHolder holder, int position) {
        CountryModel countryModel = mItems.get(position);
        try{
            holder.tvTotalPositif.setText(countryModel.getConfirmed());
            holder.tvProvinsinama.setText(countryModel.getCountry_Region());
        }catch (Exception ea){
            ea.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalPositif, tvProvinsinama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTotalPositif = itemView.findViewById(R.id.tvTotalPositif);
            tvProvinsinama = itemView.findViewById(R.id.tvProvinsiNama);
        }
    }
}
