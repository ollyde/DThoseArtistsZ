package search.deezer.oliverdixon.dthoseartistsz.common;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private Retrofit retrofit;

    private static RetrofitSingleton ourInstance = new RetrofitSingleton();
    public static RetrofitSingleton getInstance() {
        ourInstance.init();
        return ourInstance;
    }

    private RetrofitSingleton() {
    }

    private void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
