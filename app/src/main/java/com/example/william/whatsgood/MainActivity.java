package com.example.william.whatsgood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appUrl = "http://10.0.2.2:3000/api";
//        String appUrl = "https://api.forecast.io/forecast/e6b9d63b74c17318574d0fd4a190231b/37.8267,-122.423";
        Log.v(TAG, appUrl);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(appUrl)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG, "Something failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.v(TAG, response.body().string());
                }
                else {
                    Log.i(TAG, "There was an error");
                }
            }
        });

        Log.d(TAG, "Main UI code is running!");
    }
}
