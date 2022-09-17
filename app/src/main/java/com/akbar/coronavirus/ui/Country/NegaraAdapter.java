package com.akbar.coronavirus.ui.Country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akbar.coronavirus.R;
import com.akbar.coronavirus.ui.Tentang.CountryAdapter;
import com.akbar.coronavirus.ui.Tentang.CountryModel;

import java.util.List;

public class NegaraAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    List<CountryModel> mItems;
    private Context context;

    public NegaraAdapter(Context context, List<CountryModel> mItems) {
        this.mItems = mItems;
        this.context = context;

    }
    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_provinsi ,parent,false);
        CountryAdapter.ViewHolder holderData = new CountryAdapter.ViewHolder(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        CountryItem countryItem = mItems.get(position);
        try{
            holder.tvTotalPositif.setText(negaraItem.getConfirmed());
            holder.tvProvinsinama.setText(negaraItem.getCountry_Region());
        }catch (Exception ea){
            ea.printStackTrace();
        }


    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalPositif, tvProvinsinama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTotalPositif = itemView.findViewById(R.id.tvTotalPositif);
            tvProvinsinama = itemView.findViewById(R.id.tvProvinsiNama);
        }
    }
}
