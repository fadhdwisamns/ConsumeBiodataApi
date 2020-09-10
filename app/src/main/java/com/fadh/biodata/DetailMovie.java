package com.fadh.biodata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fadh.biodata.adapter.MovieAdapterList;
import com.fadh.biodata.search.SearchMDB;
import com.fadh.biodata.service.APIInterface;
import com.fadh.biodata.service.ApiClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovie extends AppCompatActivity {
    ImageView image;
    Button cari;
    EditText txtCari;
    TextView judul , genre , description , ratting;
    RecyclerView movieRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        image = findViewById(R.id.gambar);
        judul = findViewById(R.id.judulFilm);
        genre = findViewById(R.id.Genre);
        description = findViewById(R.id.description);
        ratting = findViewById(R.id.ratting);
        movieRecycler = findViewById(R.id.movie_recycler);
        txtCari = findViewById(R.id.txtCari);

        cari = findViewById(R.id.btnCari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetailMovie(txtCari.getText().toString());
            }
        });


    }
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    private void callDetailMovie(String tittle) {
        apiInterface = ApiClient.getClient().create(APIInterface.class);
        progressDialog = new ProgressDialog(DetailMovie.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<SearchMDB> call = apiInterface.getDetails(tittle, "7d141b4a" );
        call.enqueue(new Callback<SearchMDB>() {
            @Override
            public void onResponse(Call<SearchMDB> call, Response<SearchMDB> response) {
                progressDialog.dismiss();
                SearchMDB dataMovie = response.body();

                if (dataMovie !=null){


                    MovieAdapterList adapterList = new MovieAdapterList(DetailMovie.this , dataMovie.getSearch());

                    movieRecycler.setLayoutManager(new LinearLayoutManager(DetailMovie.this));
                    movieRecycler.setItemAnimator(new DefaultItemAnimator());
                    movieRecycler.setAdapter(adapterList);



                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(DetailMovie.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DetailMovie.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchMDB> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
