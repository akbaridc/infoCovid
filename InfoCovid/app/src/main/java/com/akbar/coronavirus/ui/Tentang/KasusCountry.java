package com.akbar.coronavirus.ui.Tentang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akbar.coronavirus.R;
import com.akbar.coronavirus.ui.Country.CovidCountryDetail;
import com.akbar.coronavirus.ui.Provinsi.ItemClickSupport;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KasusCountry extends AppCompatActivity {

    RecyclerView.LayoutManager mManager;
    ProgressBar progressBar;
    CountryModel countryModel;
    CountryAdapter mAdapter;
    List<CountryModel> mItems;
    RecyclerView tempatdata;
    TextView tvTotalNegara;


    private static final String TAG = KasusCountry.class.getSimpleName();
    String url = "https://api.kawalcorona.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_country);
        mItems = new ArrayList<>();

        //call view\
        getDataFromServer();
        mAdapter = new CountryAdapter(getApplicationContext(), mItems);
        progressBar = findViewById(R.id.progress_circular_provinsi);
        mManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false);
        tvTotalNegara = findViewById(R.id.tvTotalNegara);
        tempatdata = findViewById(R.id.rvCovidNegara);
        tempatdata.setLayoutManager(mManager);
        tempatdata.setHasFixedSize(true);
        tempatdata.setAdapter(mAdapter);

        ItemClickSupport.addTo(tempatdata).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                ItemDetailCountry(mItems.get(position));
            }
        });

        return ;

    }

    private void ItemDetailCountry(CountryModel countryModel){
        Intent i = new Intent(getApplicationContext(), CovidCountryDetail.class);
        i.putExtra("EXTRA_COVID", countryModel);
        startActivity(i);
    }


    private void getDataFromServer() {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {

                    JSONArray datakom = new JSONArray(response);
                    for(int i = 0; i < datakom.length(); i++) {
                        try {
                            JSONObject ja = datakom.getJSONObject(i);
                            CountryModel md = new CountryModel();

                            JSONObject dataobj = ja.getJSONObject("attributes");
                            md.setConfirmed(dataobj.getString("Confirmed"));
                            md.setCountry_Region(dataobj.getString("Country_Region"));
                            md.setDeaths(dataobj.getString("Deaths"));
                            md.setRecovered(dataobj.getString("Recovered"));

                            mItems.add(md);
                            // ketika data ada , maka di tempel ke mItems

                        } catch (Exception ea) {
                            Log.e("erronya atas",""+ea);
                            ea.printStackTrace();

                        }
                    }
                    tvTotalNegara.setText(datakom.length()+"Negara");
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

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}
