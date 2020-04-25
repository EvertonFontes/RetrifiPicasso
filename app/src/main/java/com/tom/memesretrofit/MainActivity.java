package com.tom.memesretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Memes> memes = new ArrayList<>();
    private MemeAdapter memeAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //memes.add(new Memes("teste",R.drawable.meme1));

        recyclerView = findViewById(R.id.recycler_memes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitMemes retrofitMemes = retrofit.create(RetrofitMemes.class);

        Call<List<Memes>> call = retrofitMemes.getMemes();
        call.enqueue(new Callback<List<Memes>>() {
            @Override
            public void onResponse(Call<List<Memes>> call, Response<List<Memes>> response) {
                if(response.isSuccessful()){
                    memes = new ArrayList<>(response.body());
                    /*
                    for(Memes m : memes){
                    Log.d("mesage", m.getUrl());
                    }
                    */

                    memeAdapter = new MemeAdapter(memes,MainActivity.this);
                    recyclerView.setAdapter(memeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Memes>> call, Throwable t) {
                t.getMessage();
            }
        });

    }
}
