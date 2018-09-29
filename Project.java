package com.test.webapplication;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


import android.content.Context;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.app.Fragment;

import org.junit.Test;

import org.junit.runner.RunWith;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static org.junit.Assert.*;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.555744, 126.970431)));

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);

        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(37.581917, 126.969138))
                .title("종로구보건소");
        marker.position(new LatLng(37.563913, 127.015472))
                .title("중구보건소");
        marker.position(new LatLng(37.532541, 126.990139))
                .title("용산구보건소");
        marker.position(new LatLng(37.567050, 127.033183))
                .title("성동구보건소");
        marker.position(new LatLng(37.538382, 127.082407))
                .title("광진구보건소");
        marker.position(new LatLng(37.574606, 127.040157))
                .title("동대문구보건소");
        marker.position(new LatLng(37.606560, 127.092751))
                .title("중랑구보건소");
        marker.position(new LatLng(37.602646, 127.039518))
                .title("성북구보건소");
        marker.position(new LatLng(37.632121, 127.038769))
                .title("강북구보건소");
        marker.position(new LatLng(37.657949, 127.038776))
                .title("도봉구보건소");
        marker.position(new LatLng(37.654583, 127.057010))
                .title("노원구부건소");
        marker.position(new LatLng(37.602474, 126.928856))
                .title("은평구보건소");
        marker.position(new LatLng(37.578636, 126.936260))
                .title("서대문구보건소");
        marker.position(new LatLng(37.5264591, 126.8933456))
                .title("영등포구보건소");
        marker.position(new LatLng(37.5265263, 126.8605146))
                .title("마포구보건소");
        marker.position(new LatLng(37.517543, 126.863682))
                .title("양천구보건소");
        marker.position(new LatLng(37.5496095, 126.8660883))
                .title("강서구보건소");
        marker.position(new LatLng(37.5000802, 126.8871354))
                .title("구로구보건소");
        marker.position(new LatLng(37.457054, 126.8937627))
                .title("금천구보건소");
        marker.position(new LatLng(37.4941175, 126.9407415))
                .title("동작구보건소");
        marker.position(new LatLng(37.4784382, 126.9489248))
                .title("관악구보건소");
        marker.position(new LatLng(37.4836057, 127.03125))
                .title("서초구보건소");
        marker.position(new LatLng(37.5026642, 127.0562548))
                .title("강남구보건소");
        marker.position(new LatLng(37.5145472, 127.1043533))
                .title("송파구보건소");
        marker.position(new LatLng(37.5292407, 127.1233508))
                .title("강동구보건소");

        googleMap.addMarker(marker).showInfoWindow();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), marker.getTitle() + "클릭했음",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)


public class ExampleInstrumentedTest
{
    @Test

    public void useAppContext()
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("org.techtown.mainscreen_trial4", appContext.getPackageName());
    }
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest
{
    @Test

    public void addition_isCorrect()
    {
        assertEquals(4, 2 + 2);
    }
}

public class MainActivity extends AppCompatActivity
{
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter
{
    public FragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override

    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                return HealthTime.newInstance();

            case 1:
                return Location.newInstance();

            case 2:
                return Schedule.newInstance();

            case 3:
                return RecommendList.newInstance();

            default:
                return null;
        }
    }

    @Override

    public int getCount()
    {
        return 0;
    }
}

public class HealthTime extends Fragment
{

    public HealthTime()
    {
        // Required empty public constructor
    }

    public static HealthTime newInstance()
    {
        HealthTime fragment = new HealthTime();
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_time, container, false);
    }

}

public class Location extends Fragment
{
    public Location()
    {
        // Required empty public constructor
    }

    public static Location newInstance()
    {
        Location fragment = new Location();
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }
}

public class MainActivity extends AppCompatActivity
{
    private ImageButton imageButton;
    private

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

public class RecommendList extends Fragment
{
    public RecommendList()
    {
        // Required empty public constructor
    }

    public static RecommendList newInstance()
    {
        RecommendList fragment = new RecommendList();
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend_list, container, false);
    }
}

public class Schedule extends Fragment
{
    public Schedule()
    {
        // Required empty public constructor
    }

    public static Schedule newInstance(String param1, String param2)
    {
        Schedule fragment = new Schedule();
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }
}
