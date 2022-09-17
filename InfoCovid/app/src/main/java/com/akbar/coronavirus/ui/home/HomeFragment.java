package com.akbar.coronavirus.ui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.akbar.coronavirus.R;
import com.akbar.coronavirus.ui.Country.KasusNegara;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class HomeFragment extends Fragment {
    private TextView tvTotalKasus, tvTotalMeninggal, tvTotalSembuh, detail_update, waktuUpdate;
    private ProgressBar progressBar;
    private TextView tvMaquee;
    private RequestQueue mQueue;
    CardView cardNegara;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mQueue = Volley.newRequestQueue(getActivity());
        tvMaquee = root.findViewById(R.id.marqueeText);
        tvMaquee.setSelected(true);

        //call view
        tvTotalKasus = root.findViewById(R.id.tvTotalPositif);
        tvTotalMeninggal = root.findViewById(R.id.tvTotalMeninggal);
        tvTotalSembuh = root.findViewById(R.id.tvTotalSembuh);
        detail_update = root.findViewById(R.id.detail_update);
        waktuUpdate = root.findViewById(R.id.waktuUpdate);
        cardNegara = root.findViewById(R.id.card_list_negara);
        progressBar = root.findViewById(R.id.progress_circular_home);

        //call volley
        getData();

        updateTimeParse();

        return root;
    }

    private void getData() {

        String url = "https://api.kawalcorona.com/indonesia";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                JSONArray CovidIndonesia = new JSONArray();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsPost = response.getJSONObject(i);
                        tvTotalKasus.setText(jsPost.getString("positif"));
                        tvTotalMeninggal.setText(jsPost.getString("meninggal"));
                        tvTotalSembuh.setText(jsPost.getString("sembuh"));
                    }

                    //      Membuat klik link detail
                            detail_update.setMovementMethod(LinkMovementMethod.getInstance());
                            detail_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                            browserIntent.setData(Uri.parse("https://www.kawalcorona.com"));
                            startActivity(browserIntent);
                        }
                    });

                    //Membuat klik card negara


                    cardNegara.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getContext(), KasusNegara.class);
                            startActivity(i);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error response", error.toString());
            }
        });

        mQueue.add(arrayRequest);
    }

//    public String getDateCurrentTimeZone(long timestamp){
//        try {
//            Calendar calendar = Calendar.getInstance();
//            TimeZone tm = TimeZone.getDefault();
//            calendar.setTimeInMillis(timestamp * 1000);
//            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date currenTimeZone = (Date) calendar.getTime();
//
//            waktuUpdate.setText(sdf.format(currenTimeZone));
//        } catch (Exception e){
//            e.getMessage();
//        }
//    }

    private void updateTimeParse() {
        String url = "https://api.kawalcorona.com/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < 1; i++) {
                        JSONObject negara = response.getJSONObject(i);
                        JSONObject dataNegara = negara.getJSONObject("attributes");

                        Long unix = dataNegara.getLong("Last_Update");
                        Date date = new Date(unix);
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String dateString = sdf.format(new Date(Long.parseLong(date)))

                        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy HH:mm:ss ");

                        String dateTime = sdf.format(date);
                        String waktuTerbaru = "Diupdate pada ";
                        waktuTerbaru += dateTime;

                        waktuUpdate.setText(waktuTerbaru);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonArrayRequest);
    }


}