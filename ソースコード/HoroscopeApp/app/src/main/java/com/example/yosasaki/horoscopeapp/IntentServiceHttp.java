package com.example.yosasaki.horoscopeapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class IntentServiceHttp extends IntentService {
    private static final String LOGTAG = "IntentServiceHttp";
    static int cnt = 0;
    static JSONObject[] jsonObject;
    private String dateTmp;

    public IntentServiceHttp(String name) {
        super(name);
    }

    public IntentServiceHttp() {
        super("IntentServiceHttp");
        Log.i(LOGTAG, "Constractor");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        InputStream inputStream;
        HttpURLConnection urlConnection = null;
        Log.i(LOGTAG, "onHandleIntent - start");

        try {
            String urlTmp = "http://api.jugemkey.jp/api/horoscope/free/";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd");
            dateTmp = sdf.format(MainActivity.dateHoro);
            URL url = new URL(urlTmp + dateTmp);
            BufferedReader reader = null;

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return;
            }
            String jsonData = buffer.toString();
                Log.d("httpResponse", jsonData);
                String data = jsonData.replaceAll("\\{\"horoscope\":","");
                JSONObject json = new JSONObject(data);
                JSONArray dataArray  = json.getJSONArray(dateTmp);

                int count = dataArray.length();
                jsonObject = new JSONObject[count];
                for (int i=0; i<count; i++){
                    jsonObject[i] = dataArray.getJSONObject(i);
                }
            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Intent broadcastIntent = new Intent(getApplicationContext(), BroadcastReceiverHttp.class);
            broadcastIntent.putExtra("status", 0);
            broadcastIntent.setAction("HTTP_ACTION");
            sendBroadcast(broadcastIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOGTAG,"onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(LOGTAG,"onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(LOGTAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOGTAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.i(LOGTAG,"setIntentRedelivery");
        super.setIntentRedelivery(enabled);
    }
}
