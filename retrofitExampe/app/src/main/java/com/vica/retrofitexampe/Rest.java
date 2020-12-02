package com.vica.retrofitexampe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest extends AppCompatActivity {

    private EditText id,tit,aut,des;
    private Button addB,deleteB,updateB,getB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        id = findViewById(R.id.IDTV);
        tit = findViewById(R.id.TIT);
        aut = findViewById(R.id.AUT);
        des = findViewById(R.id.DESC);

        addB = findViewById(R.id.BTadd);
        deleteB = findViewById(R.id.BTdelete);
        updateB = findViewById(R.id.BTupdate);
        getB = findViewById(R.id.BTget);

        getB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBookById();
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBookById();
            }
        });

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewBook();
            }
        });

        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBookById();
            }
        });

    }
    private void editBookById(){
        if (id.getText().toString().equals("")){
            Toast.makeText(getBaseContext(),"Debes colocar en el campo ID un numero",Toast.LENGTH_SHORT).show();
            return;
        }
        String value= id.getText().toString();
        final int finalValue=Integer.parseInt(value);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.60.3:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        booksValue booksValue = new booksValue(aut.getText().toString(),des.getText().toString(),tit.getText().toString());

        Call<booksValue> call = jsonPlaceHolderAPI.editBook(booksValue, finalValue);

        call.enqueue(new Callback<com.vica.retrofitexampe.booksValue>() {
            @Override
            public void onResponse(Call<com.vica.retrofitexampe.booksValue> call, Response<com.vica.retrofitexampe.booksValue> response) {
                Toast.makeText(getBaseContext(),"Actualizado el id: "+finalValue,Toast.LENGTH_SHORT).show();
                id.setText("");
                aut.setText("");
                tit.setText("");
                des.setText("");
            }

            @Override
            public void onFailure(Call<com.vica.retrofitexampe.booksValue> call, Throwable t) {

            }
        });

    }
    private void addNewBook(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.60.3:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        booksValue booksValue = new booksValue(aut.getText().toString(),des.getText().toString(),tit.getText().toString());

        Call<booksValue> call = jsonPlaceHolderAPI.createPost(booksValue);
        call.enqueue(new Callback<com.vica.retrofitexampe.booksValue>() {
            @Override
            public void onResponse(Call<com.vica.retrofitexampe.booksValue> call, Response<com.vica.retrofitexampe.booksValue> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getBaseContext(),""+response.code(),Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getBaseContext(),"Agregado",Toast.LENGTH_SHORT).show();
                id.setText("");
                aut.setText("");
                tit.setText("");
                des.setText("");
            }

            @Override
            public void onFailure(Call<com.vica.retrofitexampe.booksValue> call, Throwable t) {

            }
        });
    }
    private void deleteBookById(){
        if (id.getText().toString().equals("")){
            Toast.makeText(getBaseContext(),"Debes colocar en el campo ID un numero",Toast.LENGTH_SHORT).show();
            return;
        }
        String value= id.getText().toString();
        int finalValue=Integer.parseInt(value);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.60.3:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<Void> call = jsonPlaceHolderAPI.deleteById(finalValue);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getBaseContext(),""+response.code(),Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getBaseContext(),"Eliminado",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBookById() {
        if (id.getText().toString().equals("")){
            Toast.makeText(getBaseContext(),"Debes colocar en el campo ID un numero",Toast.LENGTH_SHORT).show();
            return;
        }
        String value= id.getText().toString();
        int finalValue=Integer.parseInt(value);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.60.3:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<booksResultById> call = jsonPlaceHolderAPI.getBookById(finalValue);

        call.enqueue(new Callback<booksResultById>() {
            @Override
            public void onResponse(Call<booksResultById> call, Response<booksResultById> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getBaseContext(),""+response.code(),Toast.LENGTH_SHORT).show();
                }

                booksResultById booksResultById = response.body();
                booksValue booksValue = booksResultById.getBook();

                //id.setText(booksValue.getId());
                aut.setText(booksValue.getAuthor());
                tit.setText(booksValue.getTitle());
                des.setText(booksValue.getDescription());
            }

            @Override
            public void onFailure(Call<booksResultById> call, Throwable t) {

            }
        });
    }
}
