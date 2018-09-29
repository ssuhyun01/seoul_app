package com.test.webapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InternetThread(new TextHandler()).start();
            }
        });
    }

    private class TextHandler extends Handler {
        public void handleMessage (Message msg) {
            Bundle bun = msg.getData();
            text.setText(bun.getString("HTML"));
        }
    }

    private class InternetThread extends Thread {
        Handler handler;

        public InternetThread (Handler handler) {
            this.handler = handler;
        }

        public void run () {
            String html = connect();

            Bundle bundle = new Bundle();

            JSONObject json1 = null;
            String output = "";
            try {
                json1 = new JSONObject(html);
                JSONArray json2 = json1.getJSONObject("SbChcHealth").getJSONArray("row");
                for (int i = 0; i < json2.length(); i++) {
                    JSONObject json3 = json2.getJSONObject(i);
                    if (json3.getString("WRITER").equals("건강관리과") == false) continue;
                    output += json3.getString("TITLE") + "\n" + json3.getString("CONTENT") + "\n\n";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            bundle.putString("HTML", output);
            Message msg = handler.obtainMessage();
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        private String connect () {
            try {
                URL url = new URL("http://openapi.seoul.go.kr:8088/444543414e74746e37335048576a43/json/SbChcHealth/1/100/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int resCode = connection.getResponseCode();
                if (resCode != HttpURLConnection.HTTP_OK)
                    throw new Error("Code: " + resCode);

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String data = "", buf;

                while ((buf = reader.readLine()) != null) {
                    data += buf + "\n";
                }

                return data;
            } catch (Exception e) {
                return e.toString();
            }
        }
    }
}
