package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ContentFragment extends Fragment {

    public ContentFragment(){
        super(R.layout.fragment_content);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.updateButton);
        TextView editText = view.findViewById(R.id.editText);
        Button button1 = view.findViewById(R.id.nextButton);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=ef3a2ea5cec75222cb51a1f3fdc86513");
                        HttpURLConnection connection =
                                (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                        StringBuilder json = new StringBuilder(1024);
                        String tmp = "";
                        while ((tmp = reader.readLine()) != null)
                            json.append(tmp).append("\n");
                        reader.close();
                        JSONObject data = new JSONObject(json.toString());
                        JSONObject data1 = data.getJSONObject("main");
                        String temp = data1.getString("temp");
                        editText.setText(temp);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                try {
                    fragmentTransaction.replace(R.id.fragment_container_view, new ContentFragment2());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}