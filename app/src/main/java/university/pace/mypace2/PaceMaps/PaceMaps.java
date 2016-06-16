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
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import university.pace.mypace2.*;
import university.pace.mypace2.R;

public class PaceMaps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    /**
     * PACE Weschester Longitude/Latitude
     **/
    private final double Pace_PLV_LNG = -73.808344;
    private final double Pace_PLV_LAT = 41.128393;

    private final double Pace_PLV_Miller_LNG = -73.809095;
    private final double Pace_PLV_Miller_LAT = 41.127081;

    private final double Pace_PLV_LIENHARD_LNG = -73.809692;
    private final double Pace_PLV_LIENHARD_LAT = 41.127413;


    private final double Pace_PLV_GOLDSTIEN_LNG = -73.805302;
    private final double Pace_PLV_GOLDSTIEN_LAT = 41.126664;

    private final double Pace_PLV_GOLDSTIENGYM_LNG = -73.809295;
    private final double Pace_PLV_GOLDSTIENGYM_LAT = 41.129429;

    private final double Pace_PLV_DYSON_LNG = -73.808012;
    private final double Pace_PLV_DYSON_LAT = 41.125481;


    private final double Pace_PLV_WILOCX_LNG = -73.807670;
    private final double Pace_PLV_WILCOX_LAT = 41.125986;


    private final double Pace_PLV_Library_LNG = -73.807441;
    private final double Pace_PLV_Library_LAT = 41.127590;


    private final double Pace_PLV_Choate_LNG = -73.809465;
    private final double Pace_PLV_Choate_LAT = 41.128592;

    private final double Pace_PLV_OSA_LNG = -73.808973;
    private final double Pace_PLV_OSA_LAT = 41.131604;

    private final double Pace_PLV_EnvironmentalCenter_LNG = -73.808475;
    private final double Pace_PLV_EnvironmentalCenter_LAT = 41.130207;

    private final double Pace_PLV_Kessel_LNG = -73.808743;
    private final double Pace_PLV_Kessel_LAT = 41.128397;

    /*Dorms On Campus */
    private final double Pace_PLV_Alumni_LNG = -73.808086;
    private final double Pace_PLV_Alumni_LAT = 41.129610;


    private final double Pace_PLV_Elm_LNG = -73.807263;
    private final double Pace_PLV_Elm_LAT = 41.128575;

    private final double Pace_PLV_North_LNG = -73.805270;
    private final double Pace_PLV_North_LAT = 41.128309;

    private final double Pace_PLV_Marks_LNG = -73.808189;
    private final double Pace_PLV_Marks_LAT = 41.125831;


    private final double Pace_PLV_Pond_LNG = -73.808238;
    private final double Pace_PLV_Pond_LAT = 41.127508;


    private final double Pace_PLV_Martin_LNG = -73.806595;
    private final double Pace_PLV_Martin_LAT = 41.129335;


    /** PACE Weschester Longitude/Latitude  **/


    /**
     * PACE New York Longitude/Latitude
     **/
    private final double Pace_NYC_LNG = -74.005043;
    private final double Pace_NYC_LAT = 40.711353;


    /**
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     * <p>
     * private final double Pace_PLV_Martin_LNG = -73.806595;
     * private final double Pace_PLV_Martin_LAT = 41.129335;
     **/












    /*Gets user Location */


    // Add a marker in Pleasantville and move the camera
    private LatLng PaceUniPLV = new LatLng(Pace_PLV_LAT, Pace_PLV_LNG);
    private LatLng PaceUniPLV_OSA = new LatLng(Pace_PLV_OSA_LAT, Pace_PLV_OSA_LNG);
    private LatLng PaceUniPLV_Environmental = new LatLng(Pace_PLV_EnvironmentalCenter_LAT, Pace_PLV_EnvironmentalCenter_LNG);
    private LatLng PaceUniPLV_Miller = new LatLng(Pace_PLV_Miller_LAT, Pace_PLV_Miller_LNG);
    private LatLng PaceUniPLV_Lienhard = new LatLng(Pace_PLV_LIENHARD_LAT, Pace_PLV_LIENHARD_LNG);
    private LatLng PaceUniPLV_Goldstien = new LatLng(Pace_PLV_GOLDSTIEN_LAT, Pace_PLV_GOLDSTIEN_LNG);
    private LatLng PaceUniPLV_GoldstienGym = new LatLng(Pace_PLV_GOLDSTIENGYM_LAT, Pace_PLV_GOLDSTIENGYM_LNG);
    private LatLng PaceUniPLV_Dyson = new LatLng(Pace_PLV_DYSON_LAT, Pace_PLV_DYSON_LNG);
    private LatLng PaceUniPLV_Marks = new LatLng(Pace_PLV_Marks_LAT, Pace_PLV_Marks_LNG);
    private LatLng PaceUniPLV_Wilcox = new LatLng(Pace_PLV_WILCOX_LAT, Pace_PLV_WILOCX_LNG);
    private LatLng PaceUniPLV_Library = new LatLng(Pace_PLV_Library_LAT, Pace_PLV_Library_LNG);
    private LatLng PaceUniPLV_Kessel = new LatLng(Pace_PLV_Kessel_LAT, Pace_PLV_Kessel_LNG);
    private LatLng PaceUniPLV_Pond = new LatLng(Pace_PLV_Pond_LAT, Pace_PLV_Pond_LNG);
    private LatLng PaceUniPLV_Choate = new LatLng(Pace_PLV_Choate_LAT, Pace_PLV_Choate_LNG);
    private LatLng PaceUniPLV_Alumni = new LatLng(Pace_PLV_Alumni_LAT, Pace_PLV_Alumni_LNG);
    private LatLng PaceUniPLV_Elm = new LatLng(Pace_PLV_Elm_LAT, Pace_PLV_Elm_LNG);
    private LatLng PaceUniPLV_North = new LatLng(Pace_PLV_North_LAT, Pace_PLV_North_LNG);
    private LatLng PaceUniPLV_Martin = new LatLng(Pace_PLV_Martin_LAT, Pace_PLV_Martin_LNG);
    // Add a marker in NYC and move the camera
    private LatLng PaceUniNYC = new LatLng(Pace_NYC_LAT, Pace_NYC_LNG);

    /*Default Map view*/
    private LatLng Position = PaceUniPLV;

    //:TODO Get working on higer API Phones
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
            //     changeMap();

            return true;
        }
        if (id == R.id.MapTypeChange) {
            /**   changeType();    Change to satellite  **/
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

        try {

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            double latitude = location.getLatitude();

        /*ECHO SHOW USER's LAT    */
            System.out.println("user's latitude" + latitude);
            double longitude = location.getLongitude();

/**checks if Location is on if not then show default map**/
            if (mMap != null && isLocationEnabled(getApplicationContext())) {


                if (latitude >= Pace_PLV_LAT)

                {
 /*If latitude is greater than user latitude user is north */
                    PleasantvilleCampusOnMapView();
                    Position = PaceUniPLV;
                    Log.d("User is North", "Showing PLV");

                }
           /*If latitude is less than user latitude user is south  */
                if (latitude <= Pace_NYC_LAT)  //TODO:CHANGE THE LAT

                {
                    NYVCampusOnMapView();
                    Position = PaceUniNYC;
                    Log.d("User is South", "Showing NYC");
                }

            } else {
/*If latitude not there or something- default */
                PleasantvilleCampusOnMapView();
                Log.d("default", "Showing PLV");

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
        } catch (Exception e) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
            dialog.setMessage(getApplicationContext().getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(getApplicationContext().getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getApplicationContext().startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(getApplicationContext().getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();

            Log.d("error", "Location");
        }
    }


    /**
     * Normal to satellite view
     **/
    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * Show different map manual
     **/
    public void changeMap(View view) {
        if (mMap != null) {

            if (Position == PaceUniPLV) {
             NYVCampusOnMapView();
                Position = PaceUniNYC;
                Log.d("User toggled", "Showing NYC");

            } else {
              PleasantvilleCampusOnMapView();
                Position = PaceUniPLV;
                Log.d("User toggled", "Showing PLV");

            }


        }
    }


    /**
     * Search any loaction
     **/
    public void onSearch(View view) {

        EditText location_tf = (EditText) findViewById(R.id.address);
        String location = location_tf.getText().toString();

        List<Address> addressList = null;
        if (location == null) {
            if (location != null) {
                Geocoder geocoder = new Geocoder(this);

                try {
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = addressList.get(0);

                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

/*User can Search any location*/
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }

    private void InfoWindow() {

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.infowindow_maps, null);
                TextView tvLocality = (TextView) findViewById(R.id.tv_locality);
                TextView tvDescription = (TextView) findViewById(R.id.tv_des);

                //       LatLng latLng=marker.getPosition();
                tvLocality.setText(marker.getTitle());
                tvDescription.setText(marker.getSnippet());
                return view;
            }
        });


    }

    private void PleasantvilleCampusOnMapView() {
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("Pace University - Pleasantville Campus").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_OSA).title("Office of Student Assistance").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_osa_icon)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Environmental).title("Environmental Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_environ)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Miller).title("Miller Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Lienhard).title("Lienhard Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Goldstien).title("Goldstien Academic Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_GoldstienGym).title("Goldstien Fitness Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Marks).title("Marks Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Dyson).title("Dyson Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Wilcox).title("Wilcox Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Kessel).title("Kessel Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_kessel)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Library).title("Mortola Library").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library_kid)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Pond).title("Choate Pond").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_pond)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Choate).title("Choate House").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_North).title("North Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Elm).title("Elm Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Alumni).title("Alumni Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Martin).title("Martin Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 16));
    }

    private void NYVCampusOnMapView() {
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC).title("1 Pace Plaza, New York, NY 10038").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        /*
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Miller).title("Miller Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Lienhard).title("Lienhard Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Goldstien).title("Goldstien Academic Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_GoldstienGym).title("Goldstien Fitness Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Dyson).title("Dyson Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Wilcox).title("Wilcox Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Kessel).title("Kessel Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_small)));
*/
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 16));
    }


    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;


        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }



    }


}