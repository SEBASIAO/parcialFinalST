package com.vica.retrofitexampe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvresult;
    private Button back,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvresult = findViewById(R.id.tvresult);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.60.3:5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

                Call<booksResult> call = jsonPlaceHolderAPI.getBooks();

                call.enqueue(new Callback<booksResult>() {
                    @Override
                    public void onResponse(Call<booksResult> call, Response<booksResult> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getBaseContext(),""+response.code(),Toast.LENGTH_SHORT).show();
                        }
                        booksResult br = response.body();
                        List<booksValue> bv = br.getBooks();
                        for (booksValue bov : bv){
                            String content ="";

                            content += "author: " +bov.getAuthor()+ " \n";
                            content += "description: " +bov.getDescription()+ " \n";
                            content += "id: " +bov.getId()+ " \n";
                            content += "title: " +bov.getTitle()+ " \n\n";
                            tvresult.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<booksResult> call, Throwable t) {

                    }
                });
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Rest.class);
                startActivity(intent);
            }
        });
    }
}

class booksResult {
    @SerializedName("books")
    private List<booksValue> books;

    public List<booksValue> getBooks(){
        return books;
    }
 }

class booksResultById {
    @SerializedName("book")
    private booksValue books;

    public booksValue getBook(){
        return books;
    }
}

class booksValue{
    @SerializedName("author")
    private String author;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;

    public booksValue(String author, String description, String title) {
        this.author = author;
        this.description = description;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}