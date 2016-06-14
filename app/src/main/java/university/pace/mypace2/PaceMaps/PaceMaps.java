package university.pace.mypace2.PaceMaps;

import android.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import university.pace.mypace2.*;
import university.pace.mypace2.R;

public class PaceMaps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    /** PACE Weschester Longitude/Latitude  **/
    private final double Pace_PLV_LNG = -73.808344;
    private final double Pace_PLV_LAT = 41.128393;
    /** PACE New York Longitude/Latitude  **/
    private final double Pace_NYC_LNG = -74.005043;
    private final double Pace_NYC_LAT = 40.711353;

    /*Gets user Location */


    // Add a marker in Pleasantville and move the camera
    private LatLng PaceUniPLV = new LatLng(Pace_PLV_LAT, Pace_PLV_LNG);

    // Add a marker in NYC and move the camera
    private LatLng PaceUniNYC = new LatLng(Pace_NYC_LAT, Pace_NYC_LNG);

    /*Default Map view*/
    private LatLng Position = PaceUniPLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

            setContentView(university.pace.mypace2.R.layout.activity_pace_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //If a user is currently authenticated, display a logout menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ToggleMap) {
            changeMap();
            //    menuview.logout();
            return true;
        }
        if (id == R.id.MapTypeChange) {
            changeType();  /**Change to satellite**/
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();


        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();

        /*ECHO SHOW USER's LAT    */
        System.out.println("user's latitude" + latitude);
        double longitude = location.getLongitude();
        // LatLng NYC=new LatLng(40.746924,-73.856792);
        // LatLng PLV= new LatLng(43.746729,-73.794852);


        if (mMap != null) {
          /*If latitude is greater than user latitude user is north */


            if (latitude >= Pace_PLV_LAT)

            {


                mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("861 Bedford Rd, Pleasantville, NY 10570"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 15));
                Position = PaceUniPLV;
                Log.d("User is North", "Showing PLV");

            }
           /*If latitude is less than user latitude user is south  */
            if (latitude <= Pace_NYC_LAT)  //TODO:CHANGE THE LAT

            {


                mMap.addMarker(new MarkerOptions().position(PaceUniNYC).title("1 Pace Plaza, New York, NY 10038"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 15));
                Position = PaceUniNYC;
                Log.d("User is South", "Showing NYC");
            }
          /*If latitude is greater than user latitude user is north */
            else {


                mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("861 Bedford Rd, Pleasantville, NY 10570"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 15));

                Log.d("default", "Showing PLV");
            }
/*If latitude not there or something- default */
        }




        /** Premission request to Find current location**/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }

        /** Find current location**/
        mMap.setMyLocationEnabled(true);
        /** Max zoom on School**/
        mMap.getMaxZoomLevel();
    }

    /**
     * Normal to satellite view
     **/
    public void changeType() {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * Show different map manual
     **/
    public void changeMap() {
        if (mMap != null) {

            if (Position == PaceUniPLV) {
                mMap.addMarker(new MarkerOptions().position(PaceUniNYC).title("1 Pace Plaza, New York, NY 10038"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 15));

                Log.d("User toggled", "Showing NYC");
            } else {
                mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("861 Bedford Rd, Pleasantville, NY 10570"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 15));

                Log.d("User toggled", "Showing PLV");

            }

        }
    }


    /** Search any loaction **/
    public void onSearch(View view)
    {

        EditText location_tf= (EditText)findViewById(R.id.address);
        String location= location_tf.getText().toString();

        List<Address> addressList=null;
        if(location==null) {
            if (location != null) {
                Geocoder geocoder = new Geocoder(this);

                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addressList.get(0);

                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

/*Adds Starting view for Bread of Life  Location      */
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }



}