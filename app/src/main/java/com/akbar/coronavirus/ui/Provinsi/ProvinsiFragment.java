package com.akbar.coronavirus.ui.Provinsi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.akbar.coronavirus.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProvinsiFragment extends Fragment {


    RecyclerView.LayoutManager mManager;
    ProgressBar progressBar;
    ProvinsiModel provinsiModel;
    CovidProvinsiAdapter mAdapter;
    List<ProvinsiModel> mItems;
    RecyclerView tempatdata;
    TextView tvTotalProvinsi;


    private static final String TAG = ProvinsiFragment.class.getSimpleName();
    String url = "https://api.kawalcorona.com/indonesia/provinsi";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_provinsi, container, false);


        mItems = new ArrayList<>();


        //call view\
        getDataFromServer();
        mAdapter = new CovidProvinsiAdapter(getContext(), mItems);
        progressBar = root.findViewById(R.id.progress_circular_provinsi);
        mManager = new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false);
        tvTotalProvinsi = root.findViewById(R.id.tvTotalProvinsi);
        tempatdata = root.findViewById(R.id.rvCovidProvinsi);
        tempatdata.setLayoutManager(mManager);
        tempatdata.setHasFixedSize(true);
        tempatdata.setAdapter(mAdapter);

        ItemClickSupport.addTo(tempatdata).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidProvinsi(mItems.get(position));
            }
        });

        return root;

    }

    private void showSelectedCovidProvinsi(ProvinsiModel provinsiModel){
        Intent covidCovidProvinsiDetail = new Intent(getActivity(), CovidProvinsiDetail.class);
        covidCovidProvinsiDetail.putExtra("EXTRA_COVID", provinsiModel);
        startActivity(covidCovidProvinsiDetail);
    }

    private void getDataFromServer() {


//        covidProvinsis = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                    try {

                        JSONArray datakom = new JSONArray(response);
                        for(int i = 0; i < datakom.length(); i++) {
                            try {
                                JSONObject ja = datakom.getJSONObject(i);
                                ProvinsiModel md = new ProvinsiModel();

                                JSONObject dataobj = ja.getJSONObject("attributes");
                                md.setKasus_Posi(dataobj.getString("Kasus_Posi"));
                                md.setProvinsi(dataobj.getString("Provinsi"));
                                md.setKasus_Meni(dataobj.getString("Kasus_Meni"));
                                md.setKasus_Semb(dataobj.getString("Kasus_Semb"));

                                mItems.add(md); // ketika data ada , maka di tempel ke mItems

                            } catch (Exception ea) {
                                Log.e("erronya atas",""+ea);
                                ea.printStackTrace();

                            }
                        }
                        tvTotalProvinsi.setText(datakom.length()+ "Provinsi");
                        mAdapter.notifyDataSetChanged();//buat nampilkan data ketika sukses
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("erronya ",""+e);
                    }
                    }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse: "+error);
            }
        });

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}