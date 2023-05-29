package com.example.m214_retrofit_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);
        add = findViewById(R.id.button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(i);

            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.48.3.250/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<List<Movie>> call = movieApi.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.code()==200)
                {
                    List<Movie> movies = response.body();

                    MovieAdapter ma = new MovieAdapter(movies,getApplicationContext(),R.layout.movie_item);
                    listView.setAdapter(ma);
                }



            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error contacting API", Toast.LENGTH_SHORT).show();
            }
        });


    }
}