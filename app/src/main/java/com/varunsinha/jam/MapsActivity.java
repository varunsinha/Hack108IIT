package com.varunsinha.jam;

import android.Manifest;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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
    private Button  zoomin, zoomout;
    private GoogleMap mMap;
    private TextView tv;
    public EditText pn,newname;
    public long phone_number;
    String lat, lon,s1;
    String glat,glon;
    Button add,cancel;
    String name;
    // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        //  Firebase.setAndroidContext(this);
        tv = (TextView) findViewById(R.id.tv);
        add = (Button) findViewById(R.id.add);
        cancel=(Button) findViewById(R.id.cross);
        pn=(EditText)findViewById(R.id.phno);
        newname=(EditText)findViewById(R.id.name);
        }


    public void onclick(View view) {
        if (view.getId() == R.id.zoomin) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

        if (view.getId() == R.id.zoomout) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());

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
     * call {@link //setUpMap()} once when {@link #mMap} is not null.
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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(MapsActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }
             // Check if we were successful in obtaining the map.
            if (mMap != null) {
               // setUpMap();
                mMap.setMyLocationEnabled(true);

                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                    @Override
                    public void onMyLocationChange(Location arg0) {
                        // TODO Auto-generated method stub

                        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(arg0.getLatitude(), arg0.getLongitude()));
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);

                        mMap.moveCamera(center);
                        mMap.animateCamera(zoom);


                        glat=String.valueOf(arg0.getLatitude());
                        glon=String.valueOf(arg0.getLongitude());

                       // tv.setText("lat" + arg0.getLatitude() + "lon" + arg0.getLongitude());
                        Firebase.setAndroidContext(MapsActivity.this);

                        add = (Button) findViewById(R.id.add);
                        add.setOnClickListener(new View.OnClickListener() {




                            @Override
                            public void onClick(View v) {

                                Firebase ref = new Firebase("https://jamlatlon.firebaseio.com/");
                                name=newname.getText().toString();
                                phone_number=Long.parseLong(pn.getText().toString());
                                //Getting values to store

                                //Creating Person object
                                addperson person = new addperson();
                                person.setName(name);
                                person.setPhoneNumber(phone_number);
                                person.setLatitude(Float.valueOf(glat));
                                person.setLongitude(Float.valueOf(glon));

                                //appending
                                Firebase appendlist = ref.child("addperson").push();
                                appendlist.setValue(person);

                                //Value event listener for realtime data update
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                            //Getting the data from snapshot
                                            addperson person = postSnapshot.getValue(addperson.class);

                                            //Adding it to a string
                                            String string = "Latitude" + person.getLatitude() + "\nLongitude: " + person.getLongitude() + "\n\n";

                                            //Displaying it on textview
                                            tv.setText(string);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        System.out.println("The read failed: " + firebaseError.getMessage());
                                    }
                                });

                            }
                        });




                    }
                });

            }

            }


    }


}


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
   /* private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
    }*/

