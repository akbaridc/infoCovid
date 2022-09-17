package com.akbar.coronavirus.ui.Provinsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.akbar.coronavirus.R;

public class CovidProvinsiDetail extends AppCompatActivity {

    TextView tvDetailProvinsiNama, tvDetailTotalPositif, tvDetailTotalMeninggal, tvDetailtotalSembuh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_provinsi_detail);

        //call view
        tvDetailProvinsiNama = findViewById(R.id.tvDetailProvinsiNama);
        tvDetailTotalPositif = findViewById(R.id.tvDetailTotalPositif);
        tvDetailTotalMeninggal = findViewById(R.id.tvDetailTotalMeninggal);
        tvDetailtotalSembuh = findViewById(R.id.tvDetailTotalSembuh);

        //call provinsi model
        ProvinsiModel provinsiModel = getIntent().getParcelableExtra("EXTRA_COVID");

        //set text view
        tvDetailProvinsiNama.setText(provinsiModel.getProvinsi());
        tvDetailTotalPositif.setText(provinsiModel.getKasus_Posi());
        tvDetailTotalMeninggal.setText(provinsiModel.getKasus_Meni());
        tvDetailtotalSembuh.setText(provinsiModel.getKasus_Semb());
    }
}
