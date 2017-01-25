package search.deezer.oliverdixon.dthoseartistsz.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfiguration {

    private Retrofit retrofit;

    private static RetrofitConfiguration ourInstance = new RetrofitConfiguration();
    public static RetrofitConfiguration getInstance() {
        ourInstance.init();
        return ourInstance;
    }

    private RetrofitConfiguration() {
    }

    private void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deezer.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
