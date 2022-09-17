package com.akbar.coronavirus.ui.Provinsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.akbar.coronavirus.R;

import java.util.ArrayList;
import java.util.List;

public class CovidProvinsiAdapter extends RecyclerView.Adapter<CovidProvinsiAdapter.ViewHolder> {

    List<ProvinsiModel> mItems;
    private Context context;

    public CovidProvinsiAdapter(Context context, List<ProvinsiModel> mItems){
        this.mItems = mItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CovidProvinsiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_provinsi ,parent,false);
        CovidProvinsiAdapter.ViewHolder holderData = new CovidProvinsiAdapter.ViewHolder((layout));
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull CovidProvinsiAdapter.ViewHolder holder, int position) {
        ProvinsiModel covidProvinsi = mItems.get(position);
        try{
            holder.tvTotalPositif.setText(covidProvinsi.getKasus_Posi());
            holder.tvProvinsinama.setText(covidProvinsi.getProvinsi());
        }catch (Exception ea){
            ea.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
