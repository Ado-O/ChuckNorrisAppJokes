package com.example.android.chucknorrisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button button;
    private TextView textView2;

    //retrofit
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    GitHubClient client = retrofit.create(GitHubClient.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView2 = (TextView) findViewById(R.id.textView2);


    }
    //button
    public void btn(View view) {
        final String text = spinner.getSelectedItem().toString();
        //brani prvi odabranu kategoriju
         int nesta = spinner.getSelectedItemPosition();
        //pozivanje api

        //konstrukcija koja pita da li prva kategorija u pitanju pa je brani
        if(0 == nesta){
            textView2.setText("Choose category :)");

        }else{
            //Retrofit
            client.reposForUser(text).enqueue(new Callback<GitHubRepo>() {
                @Override
                public void onResponse(Call<GitHubRepo> call, Response<GitHubRepo> response) {
                    GitHubRepo repos = response.body();
                    textView2.setText(repos.getValue());

                }
                @Override
                public void onFailure(Call<GitHubRepo> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}
