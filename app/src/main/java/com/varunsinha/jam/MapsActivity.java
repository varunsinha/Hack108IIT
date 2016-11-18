package com.varunsinha.jam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.Map

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {
    private Button logout, zoomin, zoomout;
    private GoogleMap mMap;
   private Button location;


    // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        final Button location = (Button)findViewById(R.id.location);
        location.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadcoords();
            }
        });
       /* LocationManager myManager;
        Myloclistener loc;
        loc = new Myloclistener();
        myManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION);
        }
        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,loc);
*/

    }

   /* public void loadcoords()
    {

        LocationManager mymanager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
       // Double latPoint = mymanager.getCurrentLocation("gps").getLatitude();
        Double longpoint=mymanager.lo
    }*/
    public void onclick(View view)
    {
        if(view.getId()==R.id.zoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

        if(view.getId()==R.id.zoomout){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());

        }
    }

public void onsearch(View view) {
    EditText t1 = (EditText) findViewById(R.id.tf1);
    String location = t1.getText().toString();
    List<Address> addressList = null;
    if (location != null || location.equals("")) {

        Geocoder geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address address = addressList.get(0);

        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        //.d("Latitude",address.getLatitude().toS);
        //Log.d(String.valueOf(address.getLatitude()),"latitude");
        //Log.d(String.valueOf(address.getLongitude()),"longitude");
        //Log.d(locationManager.addGpsStatusListener(address.getLatitude(),"latitude"));
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }





    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        //mMap.setMyLocationEnabled(true);
    }
}
