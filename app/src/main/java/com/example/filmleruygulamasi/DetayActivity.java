package com.example.filmleruygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetayActivity extends AppCompatActivity {
    private ImageView imageViewResim;
    private TextView textViewFilmAd,textViewFilmYil, textViewFilmYonetmen;
    private Filmler film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        imageViewResim = findViewById(R.id.imageViewResim);
        textViewFilmAd = findViewById(R.id.textViewFilmAd);
        textViewFilmYil = findViewById(R.id.textViewFilmYil);
        textViewFilmYonetmen = findViewById(R.id.textViewFilmYonetmen);

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        //imageViewResim.setImageResource(getResources().getIdentifier(film.getFilm_resim(),"drawable",getPackageName()));

        String url = "http://kasimadalan.pe.hu/filmler/resimler/"+film.getFilm_resim();

        Picasso.get().load(url).resize(400,600).into(imageViewResim);

        textViewFilmAd.setText(film.getFilm_ad());
        textViewFilmYil.setText(String.valueOf(film.getFilm_yil()));
        textViewFilmYonetmen.setText(film.getYonetmen().getYonetmen_ad());

    }
}