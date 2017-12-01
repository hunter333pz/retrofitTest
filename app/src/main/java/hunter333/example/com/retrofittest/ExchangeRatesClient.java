package hunter333.example.com.retrofittest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hunter333 on 30.11.2017 г..
 */

public interface ExchangeRatesClient {

    @GET("/latest")
    Call<ExchangeRatesResponse> exchangeRatesForCountry(
        @Query("base") String base
    );
}
