package com.example.m214_retrofit_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {

    EditText eid,etitle,eimage;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        eid = findViewById(R.id.ed_id);
        etitle = findViewById(R.id.ed_titre);
        eimage = findViewById(R.id.ed_img);
        add = findViewById(R.id.button2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(eid.getText().toString());
                String title = etitle.getText().toString();
                String img = eimage.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://13.48.3.250/RestAPi_Movie/")
                        // as we are sending data in json format so
                        // we have to add Gson converter factory
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                MovieApi retrofitAPI = retrofit.create(MovieApi.class);


                Call<Movie> call = retrofitAPI.createMovie(id,title,img);

                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Toast.makeText(AddActivity.this, "Movie inserted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}