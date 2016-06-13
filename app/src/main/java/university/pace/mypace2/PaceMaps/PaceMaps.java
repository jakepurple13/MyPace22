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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();


        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        // LatLng NYC=new LatLng(40.746924,-73.856792);
        // LatLng PLV= new LatLng(43.746729,-73.794852);


        // Add a marker in Pleasantville and move the camera
        LatLng PaceUniPLV = new LatLng(Pace_PLV_LAT,Pace_PLV_LNG);

        // Add a marker in NYC and move the camera
        LatLng PaceUniNYC = new LatLng(Pace_NYC_LAT,Pace_NYC_LNG);


          /*If latitude is greater than user latitude user is north */
        if(latitude>=Pace_PLV_LAT )

        {


            mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("861 Bedford Rd, Pleasantville, NY 10570"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 15));

            Log.d("User is North","Showing PLV");
        }
           /*If latitude is less than user latitude user is south  */
      else  if(latitude<=Pace_NYC_LAT)

        {


            mMap.addMarker(new MarkerOptions().position(PaceUniNYC).title("1 Pace Plaza, New York, NY 10038"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 15));

            Log.d("User is South","Showing NYC");
        }
          /*If latitude is greater than user latitude user is north */
else
        {


            mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("861 Bedford Rd, Pleasantville, NY 10570"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 15));

            Log.d("default","Showing PLV");
        }
/*If latitude not there or something- default */


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


    /*  public double FindDistance() {
    /*Gets user Location
          double latitude = location.getLatitude();
       double longitude = location.getLongitude();
          double lat2 = Pace_PLV_LAT;
          double lon2 = Pace_PLV_LNG;                    /*Gets PLV Location
        double distance = distance(latitude, longitude, lat2, lon2);
        double miles=(distance/8 *5) ; //distance in miles
        return  (miles* 5280.0 );//distance in feet
    }
    */


 /*   public static  double distance(double lat1, double lon1, double lat2, double lon2)
    {
Gets longitude and latitude of both points
        double RadiusofEarth=6371;
        double dlat=deg2rad(lat2-lat1);
        double dlon=deg2rad(lon2-lon1);
        double nesscaryMath= Math.sin(dlat/2)*Math.sin(dlat/2)+Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))
                *Math.sin(dlon/2)*Math.sin(dlon/2);
        double calculation=2 * Math.atan2(Math.sqrt(nesscaryMath),Math.sqrt(1-nesscaryMath));
        double dist=RadiusofEarth*calculation;  //Distance in km
        return (dist);
    } */

    /*Converts Degrees to radians
    private static  double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }
private  LatLng getUserLocation(){

    LatLng UserLocation;
    double latitude = location.getLatitude();
     double longitude = location.getLongitude();
    LatLng userLatLng= new LatLng(latitude,longitude);

    UserLocation=userLatLng;

    return UserLocation;
}
    */
    /*Gets user Location versus Bread of Life Distance Only
    public  void ShowMyDistance(View view)
    {   try {
        Toast.makeText(getApplicationContext(), "You are " + ((int) FindDistance()) + " Miles Away From Bread Of Life", Toast.LENGTH_LONG).show();
    }
    catch (Exception nd)
    {
    }
    }
*/
}