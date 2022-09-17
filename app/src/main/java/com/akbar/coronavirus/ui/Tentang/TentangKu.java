package com.akbar.coronavirus.ui.Tentang;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.akbar.coronavirus.R;

public class TentangKu extends Fragment {
    CardView cardTentang;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tentang, container, false);

        cardTentang = root.findViewById(R.id.card_list_tentang);

        //call data
        getData();

        return root;
    }

    private void getData() {


        cardTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.instagram.com/akbaridc_/"));
                startActivity(browserIntent);
            }
        });
    }

}
