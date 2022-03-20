package Hw6;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.*;
import java.util.Properties;

    public class HW6KitaevaMain {

        static Properties prop = new Properties();

        public static void main(String[] args) throws IOException {

            loadProperties();

            OkHttpClient client = new OkHttpClient();

            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(prop.getProperty("BASE_HOST"))
                    .addPathSegment(prop.getProperty("FORECAST"))
                    .addPathSegment(prop.getProperty("FORECAST_TIMEPERIOD"))
                    .addPathSegment(prop.getProperty("SPb_KEY"))
                    .addQueryParameter("apikey", prop.getProperty("API_KEY"))
                    .addQueryParameter("language", "en-en")
                    .addQueryParameter("metric", "true")
                    .build();

            System.out.println(url.toString());

            Request requesthttp = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            String jsonResponse = client.newCall(requesthttp).execute().body().string();
            System.out.println(jsonResponse);

        }

        private static void loadProperties() throws IOException {
            try(FileInputStream configFile = new FileInputStream("Hw6/src/main/resourses/HW6Kitaeva.properties")){
                prop.load(configFile);
            }
        }
    }
