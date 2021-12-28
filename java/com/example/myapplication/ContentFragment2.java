package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ContentFragment2 extends Fragment {

    ArrayList<City> cities = new ArrayList<>();

    ContentFragment2() throws IOException, JSONException {
        super(R.layout.fragment_content2);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            setInitialData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = view.findViewById(R.id.list);
        CityAdapter adapter = new CityAdapter(view.getContext(), cities);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() throws IOException, JSONException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String[] citiesNames = {"Moscow", "London", "Leeds"};
            for (String s : citiesNames) {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + s + "&appid=ef3a2ea5cec75222cb51a1f3fdc86513");
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
                cities.add(new City(s, temp));
            }
        }
    }

}
