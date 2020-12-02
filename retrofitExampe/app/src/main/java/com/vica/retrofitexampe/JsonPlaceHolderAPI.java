package com.vica.retrofitexampe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {
    @GET("books")
    Call<booksResult> getBooks();

    @GET("books/{id}")
    Call<booksResultById> getBookById(@Path("id") int getId);

    @DELETE("books/{id}")
    Call<Void> deleteById(@Path("id") int getId);

    @POST("books")
    Call<booksValue> createPost(@Body booksValue booksValue);

    @PUT("books/{id}")
    Call<booksValue> editBook(@Body booksValue booksValue,
                                @Path("id") int getId);
}
