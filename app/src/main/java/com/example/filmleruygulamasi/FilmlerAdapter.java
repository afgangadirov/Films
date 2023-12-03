package com.example.filmleruygulamasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmlerAdapter extends RecyclerView.Adapter<FilmlerAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Filmler> filmlerList;

    public FilmlerAdapter(Context mContext, List<Filmler> filmlerList) {
        this.mContext = mContext;
        this.filmlerList = filmlerList;
    }





    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_tasarim, parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Filmler film = filmlerList.get(position);

        //holder.imageViewFilmResim.setImageResource(mContext.getResources().getIdentifier(film.getFilm_resim(),"drawable",mContext.getPackageName()));

        String url = "http://kasimadalan.pe.hu/filmler/resimler/"+film.getFilm_resim();

        Picasso.get().load(url).into(holder.imageViewFilmResim);



        holder.textViewFilmAd.setText(film.getFilm_ad());

        holder.film_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,DetayActivity.class);
                intent.putExtra("nesne",film);

                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return filmlerList.size();
    }








    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private CardView film_card;
        private ImageView imageViewFilmResim;
        private TextView textViewFilmAd;


        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            film_card = itemView.findViewById(R.id.film_card);
            imageViewFilmResim = itemView.findViewById(R.id.imageViewFilmResim);
            textViewFilmAd = itemView.findViewById(R.id.textViewFilmAd);

        }
    }






}
