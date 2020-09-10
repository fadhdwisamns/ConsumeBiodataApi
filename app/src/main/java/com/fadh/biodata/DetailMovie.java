package com.fadh.biodata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
                public void onClick(View view) {
                    callDetailMovie(txtCari.getText().toString());
                }
            });

    }
}