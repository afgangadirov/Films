package com.example.filmleruygulamasi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilmlerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView filmRv;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter adapter;
    private Kategoriler kategori;
    //private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        toolbar = findViewById(R.id.toolbar);
        filmRv = findViewById(R.id.filmRv);

        kategori = (Kategoriler) getIntent().getSerializableExtra("nesne");

        toolbar.setTitle(kategori.getKategori_ad());
        setSupportActionBar(toolbar);

        filmRv.setHasFixedSize(true);
        filmRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        /*vt = new Veritabani(this);

        filmlerArrayList = new FilmlerDao().tumFilmlerByKategoriId(vt,kategori.getKategori_id());*/

        filmGetir(kategori.getKategori_id());



    }


    public void filmGetir(final int kategori_id){

        String url = "http://kasimadalan.pe.hu/filmler/filmler_by_kategori_id.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("cavab",response);

                filmlerArrayList = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray filmler = jsonObject.getJSONArray("filmler");

                    for (int i = 0; i< filmler.length(); i++){
                        JSONObject f = filmler.getJSONObject(i);

                        int film_id = f.getInt("film_id");
                        String film_ad = f.getString("film_ad");
                        int film_yil = f.getInt("film_yil");
                        String film_resim = f.getString("film_resim");

                        JSONObject k = f.getJSONObject("kategori");
                        int kategori_id = k.getInt("kategori_id");
                        String kategori_ad = k.getString("kategori_ad");

                        JSONObject y = f.getJSONObject("yonetmen");
                        int yonetmen_id = y.getInt("yonetmen_id");
                        String yonetmen_ad = y.getString("yonetmen_ad");

                        Kategoriler kategori = new Kategoriler(kategori_id,kategori_ad);
                        Yonetmenler yonetmen = new Yonetmenler(yonetmen_id,yonetmen_ad);

                        Filmler film = new Filmler(film_id,film_ad,film_yil,film_resim,kategori,yonetmen);

                        filmlerArrayList.add(film);

                    }

                    adapter = new FilmlerAdapter(FilmlerActivity.this,filmlerArrayList);
                    filmRv.setAdapter(adapter);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("kategori_id", String.valueOf(kategori_id));

                return params;
            }
        };


        Volley.newRequestQueue(FilmlerActivity.this).add(stringRequest);



    }





}