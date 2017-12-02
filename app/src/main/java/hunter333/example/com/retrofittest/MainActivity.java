package hunter333.example.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ExchangeRates rates = new ExchangeRates();

    ExchangeRatesClient client = ServiceGenerator.createService(ExchangeRatesClient.class);

    Call<ExchangeRatesResponse> call = client.exchangeRatesForCountry("BGN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getExchangeRatesCall();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void getExchangeRatesCall() {
        call.enqueue(new Callback<ExchangeRatesResponse>() {
            @Override
            public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {

                if (response.body().getRates() != null) {

                    rates.setExchangeRates(response.body().getRates());

                    for (Map.Entry<String, Double> rate : rates.getExchangeRates().entrySet()) {
                        Log.d("Key", rate.getKey());
                        Log.d("Value", rate.getValue().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExchangeRatesResponse> call, Throwable t) {
                Log.e("Request", "error");
            }
        });
    }
}
