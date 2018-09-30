package org.techtown.seoulapp_trial1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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