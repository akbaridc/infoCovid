package com.akbar.coronavirus.ui.Tentang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.akbar.coronavirus.R;

public class CountryDetail extends AppCompatActivity {

    TextView tvDetailCountryName, tvDetailTotalCountryConfirmed, tvDetailTotalCountryDeaths, tvDetailTotalCountryRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        //call view
        tvDetailCountryName = findViewById(R.id.tvDetailCountryName);
        tvDetailTotalCountryConfirmed = findViewById(R.id.tvDetailTotalCountryConfirmed);
        tvDetailTotalCountryDeaths = findViewById(R.id.tvDetailTotalCountryDeaths);
        tvDetailTotalCountryRecovered = findViewById(R.id.tvDetailTotalCountryRecovered);

        //call provinsi model
        CountryModel countryModel = getIntent().getParcelableExtra("EXTRA_COVID");

        //set text view
        tvDetailCountryName.setText(countryModel.getCountry_Region());
        tvDetailTotalCountryConfirmed.setText(countryModel.getConfirmed());
        tvDetailTotalCountryDeaths.setText(countryModel.getDeaths());
        tvDetailTotalCountryRecovered.setText(countryModel.getRecovered());

    }
}
