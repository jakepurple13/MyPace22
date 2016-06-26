package university.pace.mypace2.PaceMaps;


import android.Manifest;
import android.content.Context;

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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
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

import university.pace.mypace2.PaceMaps.Buildings;
import university.pace.mypace2.R;

public class PaceMaps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private final int REQUEST_LOCATION = 1;
    private double latitude;
    private NycBuildings.Building Nycmap;
    private Buildings.Building pacemap;
    private Buildings pace;
    private AutoCompleteTextView location_tf;
    /**
     * PACE Weschester Longitude/Latitude
     **/
    public final double Pace_PLV_LNG = -73.808344;
    public final double Pace_PLV_LAT = 41.128393;

    public final double Pace_PLV_Miller_LNG = -73.809095;
    public final double Pace_PLV_Miller_LAT = 41.127081;

    public final double Pace_PLV_LIENHARD_LNG = -73.809692;
    public final double Pace_PLV_LIENHARD_LAT = 41.127413;


    public final double Pace_PLV_GOLDSTIEN_LNG = -73.805302;
    public final double Pace_PLV_GOLDSTIEN_LAT = 41.126664;

    public final double Pace_PLV_GOLDSTIENGYM_LNG = -73.809295;
    public final double Pace_PLV_GOLDSTIENGYM_LAT = 41.129429;

    public final double Pace_PLV_DYSON_LNG = -73.808012;
    public final double Pace_PLV_DYSON_LAT = 41.125481;


    public final double Pace_PLV_WILOCX_LNG = -73.807670;
    public final double Pace_PLV_WILCOX_LAT = 41.125986;


    public final double Pace_PLV_Library_LNG = -73.807441;
    public final double Pace_PLV_Library_LAT = 41.127590;


    public final double Pace_PLV_Choate_LNG = -73.809465;
    public final double Pace_PLV_Choate_LAT = 41.128592;

    public final double Pace_PLV_OSA_LNG = -73.808973;
    public final double Pace_PLV_OSA_LAT = 41.131604;

    public final double Pace_PLV_EnvironmentalCenter_LNG = -73.808475;
    public final double Pace_PLV_EnvironmentalCenter_LAT = 41.130207;

    public final double Pace_PLV_Kessel_LNG = -73.808743;
    public final double Pace_PLV_Kessel_LAT = 41.128397;

    /*Dorms On Campus */
    public final double Pace_PLV_Alumni_LNG = -73.808086;
    public final double Pace_PLV_Alumni_LAT = 41.129610;


    public final double Pace_PLV_Elm_LNG = -73.807263;
    public final double Pace_PLV_Elm_LAT = 41.128575;

    public final double Pace_PLV_North_LNG = -73.805270;
    public final double Pace_PLV_North_LAT = 41.128309;

    public final double Pace_PLV_Marks_LNG = -73.808189;
    public final double Pace_PLV_Marks_LAT = 41.125831;


    public final double Pace_PLV_Pond_LNG = -73.808238;
    public final double Pace_PLV_Pond_LAT = 41.127508;


    public final double Pace_PLV_Martin_LNG = -73.806595;
    public final double Pace_PLV_Martin_LAT = 41.129335;



    /** PACE Weschester Longitude/Latitude  **/


    /**
     * PACE New York Longitude/Latitude
     **/
/*If User is at this point(Yonkers) they are consider in the city*/
    public final double Pace_NYC_ACCESSPOINT_LNG = -73.898747;
    public final double Pace_NYC_ACCESSPOINT_LAT = 40.931210;


    public final double Pace_NYC_LNG = -74.005452;
    public final double Pace_NYC_LAT = 40.711590;

    public final double Pace_NYC_Broadway_LNG = -74.009344;
    public final double Pace_NYC_Broadway_LAT = 40.710031;

    public final double Pace_NYC_JohnStreet_LNG = -74.007566;
    public final double Pace_NYC_JohnStreet_LAT = 40.709136;

    public final double Pace_NYC_fulton_LNG = -74.007007;
    public final double Pace_NYC_fulton_LAT = 40.709703;

    public final double Pace_NYC_William_LNG = -74.005368;
    public final double Pace_NYC_William_LAT = 40.709811;

    public final double Pace_NYC_WilliamII_LNG = -74.006147;
    public final double Pace_NYC_WilliamII_LAT = 40.710184;

    public final double Pace_NYC_Maria_LNG = -74.004736;
    public final double Pace_NYC_Maria_LAT = 40.710840;

    public final double Pace_NYC_OnePace_LNG = -74.004740;
    public final double Pace_NYC_OnePace_LAT = 40.710968;

    public final double Pace_NYC_ParksRow_LNG = -74.006211;
    public final double Pace_NYC_ParksRow_LAT = 40.711674;

    public final double Pace_NYC_ParksRow_Bookstore_LNG = -74.006244;
    public final double Pace_NYC_ParksRow_Bookstore_LAT = 40.711616;

    public final double Pace_NYC_Library_LNG = -74.004317;
    public final double Pace_NYC_Library_LAT = 40.710556;


    public final double Pace_NYC_Lubin_LNG = -74.005084;
    public final double Pace_NYC_Lubin_LAT = 40.711196;

    public final double Pace_NYC_TasteOfSeaPort_LNG = -74.005324;
    public final double Pace_NYC_TasteOfSeaPort_LAT = 40.711033;


    public final double Pace_NYC_Confucius_LNG = -74.006387;
    public final double Pace_NYC_Confucius_LAT = 40.711704;

    public final double Pace_NYC_Schimmel_LNG = -74.004687;
    public final double Pace_NYC_Schimmel_LAT = 40.710839;


    // Add a marker in Pleasantville and move the camera
    public LatLng PaceUniPLV = new LatLng(Pace_PLV_LAT, Pace_PLV_LNG);
    public LatLng PaceUniPLV_OSA = new LatLng(Pace_PLV_OSA_LAT, Pace_PLV_OSA_LNG);
    public LatLng PaceUniPLV_Environmental = new LatLng(Pace_PLV_EnvironmentalCenter_LAT, Pace_PLV_EnvironmentalCenter_LNG);
    public LatLng PaceUniPLV_Miller = new LatLng(Pace_PLV_Miller_LAT, Pace_PLV_Miller_LNG);
    public LatLng PaceUniPLV_Lienhard = new LatLng(Pace_PLV_LIENHARD_LAT, Pace_PLV_LIENHARD_LNG);
    public LatLng PaceUniPLV_Goldstien = new LatLng(Pace_PLV_GOLDSTIEN_LAT, Pace_PLV_GOLDSTIEN_LNG);
    public LatLng PaceUniPLV_GoldstienGym = new LatLng(Pace_PLV_GOLDSTIENGYM_LAT, Pace_PLV_GOLDSTIENGYM_LNG);
    public LatLng PaceUniPLV_Dyson = new LatLng(Pace_PLV_DYSON_LAT, Pace_PLV_DYSON_LNG);
    public LatLng PaceUniPLV_Marks = new LatLng(Pace_PLV_Marks_LAT, Pace_PLV_Marks_LNG);
    public LatLng PaceUniPLV_Wilcox = new LatLng(Pace_PLV_WILCOX_LAT, Pace_PLV_WILOCX_LNG);
    public LatLng PaceUniPLV_Library = new LatLng(Pace_PLV_Library_LAT, Pace_PLV_Library_LNG);
    public LatLng PaceUniPLV_Kessel = new LatLng(Pace_PLV_Kessel_LAT, Pace_PLV_Kessel_LNG);
    public LatLng PaceUniPLV_Pond = new LatLng(Pace_PLV_Pond_LAT, Pace_PLV_Pond_LNG);
    public LatLng PaceUniPLV_Choate = new LatLng(Pace_PLV_Choate_LAT, Pace_PLV_Choate_LNG);
    public LatLng PaceUniPLV_Alumni = new LatLng(Pace_PLV_Alumni_LAT, Pace_PLV_Alumni_LNG);
    public LatLng PaceUniPLV_Elm = new LatLng(Pace_PLV_Elm_LAT, Pace_PLV_Elm_LNG);
    public LatLng PaceUniPLV_North = new LatLng(Pace_PLV_North_LAT, Pace_PLV_North_LNG);
    public LatLng PaceUniPLV_Martin = new LatLng(Pace_PLV_Martin_LAT, Pace_PLV_Martin_LNG);
    // Add a marker in NYC and move the camera
    public LatLng PaceUniNYC = new LatLng(Pace_NYC_LAT, Pace_NYC_LNG);
    public LatLng PaceUniNYC_Broadway = new LatLng(Pace_NYC_Broadway_LAT, Pace_NYC_Broadway_LNG);
    public LatLng PaceUniNYC_William156 = new LatLng(Pace_NYC_William_LAT, Pace_NYC_William_LNG);
    public LatLng PaceUniNYC_William163 = new LatLng(Pace_NYC_WilliamII_LAT, Pace_NYC_WilliamII_LNG);
    public LatLng PaceUniNYC_Fulton = new LatLng(Pace_NYC_fulton_LAT, Pace_NYC_fulton_LNG);
    public LatLng PaceUniNYC_JohnStreet = new LatLng(Pace_NYC_JohnStreet_LAT, Pace_NYC_JohnStreet_LNG);
    public LatLng PaceUniNYC_OnePacePlaza = new LatLng(Pace_NYC_OnePace_LAT, Pace_NYC_OnePace_LNG);
    public LatLng PaceUniNYC_Bookstore = new LatLng(Pace_NYC_ParksRow_Bookstore_LAT, Pace_NYC_ParksRow_Bookstore_LNG);
    public LatLng PaceUniNYC_TasteOfSeaPort = new LatLng(Pace_NYC_TasteOfSeaPort_LAT, Pace_NYC_TasteOfSeaPort_LNG);
    public LatLng PaceUniNYC_Lubin = new LatLng(Pace_NYC_Lubin_LAT, Pace_NYC_Lubin_LNG);
    public LatLng PaceUniNYC_Library = new LatLng(Pace_NYC_Library_LAT, Pace_NYC_Library_LNG);
    public LatLng PaceUniNYC_Confucius = new LatLng(Pace_NYC_Confucius_LAT, Pace_NYC_Confucius_LNG);
    public LatLng PaceUniNYC_ParksRow = new LatLng(Pace_NYC_ParksRow_LAT, Pace_NYC_ParksRow_LNG);
    public LatLng PaceUniNYC_Maria = new LatLng(Pace_NYC_Maria_LAT, Pace_NYC_Maria_LNG);
    public LatLng PaceUniNYC_Schimmel = new LatLng(Pace_NYC_Schimmel_LAT, Pace_NYC_Schimmel_LNG);


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
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
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



    /**
     * Premission request Result
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // SHOW CAMPUS NEAR USER.
                    ShowCampusNearMe(latitude);
                } else {
                    Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
                    PleasantvilleCampusOnMapView();

                    Log.d("lat not granted", "PLV Default");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        pace = new Buildings(this);


        try {
            /** Premission request to Find current location**/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        Toast.makeText(this, "Location permission is need to show the campus closet to you", Toast.LENGTH_SHORT).show();
                    } else {

                        // No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION
                        );

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }


                    return;
                }
            }

            /** Find current location**/
            mMap.setMyLocationEnabled(true);
 /*   Gets user Location */
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            latitude = location.getLatitude();

        /*ECHO SHOW USER's LAT */
            System.out.println("user's latitude" + latitude);
            double longitude = location.getLongitude();

/**checks if Location is on if not then show default map**/


            if (mMap != null) {

                ShowCampusNearMe(latitude);
                pace = new Buildings(PaceMaps.this);
                Log.d("List", "here");
                /** Max zoom on School**/
                mMap.getMaxZoomLevel();
                /** Auto correct initialize onMap ready for user**/
                location_tf = (AutoCompleteTextView) findViewById(R.id.address);
                // Get the string array
                String[] Buildings = getResources().getStringArray(R.array.Buildings);
// Create the adapter and set it to the AutoCompleteTextView
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Buildings);
                location_tf.setAdapter(adapter);

            }                                                          /** Auto correct initialize onMap ready for user**/


        } catch (Exception e) {

/*If latitude not there or something- default */

            Log.d("error", e.toString());
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTitle().equals("Pace University - Pleasantville Campus")) {
                    // if marker source is clicked
                    // display toast
                    Toast.makeText(PaceMaps.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                    //Create view
                    //TODO:  Create layout view
                    //   setContentView(R.layout.pace_marker_info);

                    Log.v("Pace marker clicked", "k");

                } else
                    Log.v("Pace marker not clicked", "no");


            }
        });


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
     * Show different map manual Pace/NYC
     **/
    public void changeMap(View view) {
        if (mMap != null) {

            if (Position == PaceUniPLV) {
                mMap.clear();  //clears anything on the map that should not be there 'gs' jr' 'jl'
                NYVCampusOnMapView();
                Position = PaceUniNYC;
                Log.d("User toggled", "Showing NYC");

            } else {
                mMap.clear(); //clears anything on the map that should not be there 'gs' jr' 'jl'
                PleasantvilleCampusOnMapView();
                Position = PaceUniPLV;
                Log.d("User toggled", "Showing PLV");

            }


        }
    }

    private void PleasantvilleCampusOnMapView() {

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV).title("Pace University - Pleasantville Campus").snippet("861 Bedford Rd, Pleasantville, NY 10570")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_marks))
        );

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_OSA)
                .title("Office of Student Assistance").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_osa_icon)).snippet(
                        "Services of Financial Aid, Student Accounts"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Environmental
        ).title("Environmental Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_environ)).snippet("Charming place open to" +
                " all and is especially a favorite destination of families with young children. "));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Miller
        ).title("Miller Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)).snippet("College Academic Building, " +
                "College Classroom "));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Lienhard)
                .title("Lienhard Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)).snippet("College Academic Building" +
                        " and College Nursing Building"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Goldstien)
                .title("Goldstien Academic Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)).snippet(
                        "Home of Lubin and Computer Science students"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_GoldstienGym)
                .title("Goldstien Fitness Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_gym)).snippet(
                        "Gym,Pool & Basketball courts available"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Marks)
                .title("Marks Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)).snippet("Welcome Center & Mathematics department"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Dyson)
                .title("Dyson Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)).snippet("Home to all Science students & Science Professors"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Wilcox)
                .title("Wilcox Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)).snippet("Academic Building & " +
                        "home of Performing Art students"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Kessel)
                .title("Kessel Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_kessel)).snippet("Campus Food & Dinning Hall," +
                        " PaceOne card accepted"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Library)
                .title("Mortola Library").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library)).snippet("Open 24 Hours for" +
                        " studying and group work"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Pond)
                .title("Choate Pond").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_pond)).snippet("Pace's iconic land mark"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Choate
        ).title("Choate House").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)).snippet(""));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_North
        ).title("North Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)).snippet("Student dormitory located near Entrance 3"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Elm)
                .title("Elm Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)).snippet("NEW Student dormitory part of the master plan"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Alumni)
                .title("Alumni Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)).snippet("NEW Student dormitory part of the master plan"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Martin)
                .title("Martin Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)).snippet("Student dormitory located near Entrance 3"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 16));

        Toast.makeText(this, "Now viewing the Westchester Campus", Toast.LENGTH_LONG).show();

    }

    private void NYVCampusOnMapView() {


        mMap.addMarker(new MarkerOptions().position(PaceUniNYC).title("Pace University -City Campus").snippet("1 Pace Plaza, New York, NY 10038").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_marks)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Schimmel).title("Schimmel Center").snippet("provides performance to the" +
                " university and the general public").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_schimmel)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Confucius).title("The Confucius Institute").snippet("Center for language instruction & cultural immersion").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Bookstore).title("Barns & Noble BookStore").snippet("Purchase books,apparel and more").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library_kid)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_ParksRow).title("41 Parks Row").snippet("Classrooms and offices located in this building").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Lubin).title("Lubin School of Business").snippet("The business school of Pace University").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_class)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_OnePacePlaza).title("One Pace Plaza").snippet("Main building for the University").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Maria).title("Maria's Tower").snippet("TV lounge and a study lounge on every floor").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_TasteOfSeaPort).title("Taste Of The Seaport").snippet("we have food").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_kessel))); //TODO: we have foodk
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Library).title("Henry Birnbaum Library").snippet("Study lounges & private group study rooms available to all students").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William163).title("163 William Street").snippet("Residence Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Fulton).title("106 Fulton Street").snippet("Residence Halls").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_JohnStreet).title("55 John Street").snippet("Residence Halls").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Broadway).title("182 Broadway").snippet("Residence Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William156).title("156 William Street").snippet("Classrooms and Administration located in this building").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_maker_50dp)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 16));
        Toast.makeText(this, "Now viewing the NYC Campus", Toast.LENGTH_LONG).show();
    }

    private void ShowCampusNearMe(double lat) {

        if (lat > Pace_NYC_ACCESSPOINT_LAT)

        {
 /*If latitude is greater than user latitude user is north */
            PleasantvilleCampusOnMapView();

            Position = PaceUniPLV;
            Log.d("User is North", "Showing PLV");

        }
           /*If latitude is less than user latitude user is south  */
        if (lat <= Pace_NYC_ACCESSPOINT_LAT)  //TODO:CHANGE THE LAT

        {
            NYVCampusOnMapView();
            Position = PaceUniNYC;
            Log.d("User is South", "Showing NYC");
        }
    }

    /**
     * Search any Building
     **/
    public void onSearch(View view) {

// Get a reference to the AutoCompleteTextView in the layout

        final String location_search = location_tf.getText().toString();

        /**search with enter button**/
        Log.d("debug", location_tf.toString());

        location_tf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(location_search);
                    Log.d("Entered Search", "pressed Enter");
                    return true;

                }
                return false;
            }
        });
        performSearch(location_search); //search button on screen

    }

    private void performSearch(String location_search) {

        if (location_search.equalsIgnoreCase("George Samuels") && Position == PaceUniPLV)
            GS();
        if (location_search.equalsIgnoreCase("food") && Position == PaceUniPLV)
            SetMultiMarker(PaceUniPLV_Kessel, PaceUniPLV_Martin, PaceUniPLV_Miller, location_search,
                    "Here is ", "found at the " + "Pace Perk", "found at " + "Miller Hall");
        if (location_search.equalsIgnoreCase("class") || location_search.equalsIgnoreCase("classes")) {
            SetMultiMarker(PaceUniPLV_Miller, PaceUniPLV_Goldstien, PaceUniPLV_Lienhard, location_search,
                    "Here is ", "found at the " + "", "found at " + "Miller Hall");


        }
        if (location_search.equalsIgnoreCase("class") || location_search.equalsIgnoreCase("classes")) {
            SetClassMarker(PaceUniPLV_Dyson, PaceUniPLV_Goldstien, PaceUniPLV_Lienhard, PaceUniPLV_Miller, location_search,
                    "Here is ", "Here is ", "found at the " + "", "found at " + "Miller Hall");

        }
        if (location_search.equalsIgnoreCase("goldstien") && Position == PaceUniPLV) {
            SetTwoMarker(PaceUniPLV_Goldstien, PaceUniPLV_GoldstienGym, location_search,
                    "Here is ", "found at the " + "Gym");
        }  /** got lazy   **/

  /*User can Search any location on pace grounds */
        if (!location_search.equals("")) {

        /*The class 'Building'needs an enclosing instance to be instantiated*/

            if (Position == PaceUniPLV) {
                LatLng BuildingLatLng;


                            /*reads from text list*/
                pacemap = new Buildings(this).new Building(location_search);
                   /*if nothing is on the list show output*/
                if (pacemap.PaceLocation(location_search) == null) {


                    Toast.makeText(this, "No matches found on the Westchester Campus", Toast.LENGTH_LONG).show();
                    Log.d("not on list ", "show toast");

                }                                                                                                    /*check list
                                                                                                                            for matches*/ else {



                        /*returns LatLng Position*/
                    BuildingLatLng = pacemap.PaceLocation(location_search);
                    SetMarker(BuildingLatLng, location_search, "Here is ");
                    Log.d("spot found ", "show on map");
                }


            }

            if (Position == PaceUniNYC) {
          /*reads from text list*/
                Nycmap = new NycBuildings(this).new Building(location_search);
                LatLng BuildingLatLng;
                   /*if nothing is on the list show output*/
                if (Nycmap.NYClocation(location_search) == null) {
                    Toast.makeText(this, "No matches found on the NYC Campus", Toast.LENGTH_LONG).show();
                    Log.d("not on list ", "show toast");
                }                                                                                                    /*check list
                                                                                                                            for matches*/ else {

                        /*returns LatLng Position*/
                    BuildingLatLng = Nycmap.NYClocation(location_search);
                    SetMarker(BuildingLatLng, location_search, "Here is ");
                    Log.d("spot found ", "show on map");
                }


            }


        } else
            Toast.makeText(this, "You must type something to Search", Toast.LENGTH_LONG).show();


    }


    private void SetMarker(LatLng setposition, String title, String des) {
        MarkerOptions marker = new MarkerOptions()
                .title(title)
                .snippet(des)
                .position(setposition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        mMap.clear();
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(setposition));

    }

    private void SetMultiMarker(LatLng setposition, LatLng secondPosition, LatLng ThirdPosition, String title,
                                String des, String desII, String desIII) {
        MarkerOptions marker = new MarkerOptions()
                .title(title)
                .snippet(des)
                .position(setposition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        MarkerOptions markertwo = new MarkerOptions()
                .title(title)
                .snippet(desII)
                .position(secondPosition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        MarkerOptions markerthree = new MarkerOptions()
                .title(title)
                .snippet(desIII)
                .position(ThirdPosition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));


        mMap.clear();
        mMap.addMarker(marker);
        mMap.addMarker(markertwo);
        mMap.addMarker(markerthree);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(setposition));

    }

    private void SetClassMarker(LatLng setposition, LatLng secondPosition, LatLng ThirdPosition, LatLng FourPosition, String title,
                                String des, String desII, String desIII, String desIV) {
        MarkerOptions marker = new MarkerOptions()
                .title(title)
                .snippet(des)
                .position(setposition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        MarkerOptions markertwo = new MarkerOptions()
                .title(title)
                .snippet(desII)
                .position(secondPosition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        MarkerOptions markerthree = new MarkerOptions()
                .title(title)
                .snippet(desIII)
                .position(ThirdPosition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));


        mMap.clear();
        mMap.addMarker(marker);
        mMap.addMarker(markertwo);
        mMap.addMarker(markerthree);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(setposition));

    }

    private void SetTwoMarker(LatLng setposition, LatLng secondPosition, String title,
                              String des, String desII) {
        MarkerOptions marker = new MarkerOptions()
                .title(title)
                .snippet(des)
                .position(setposition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));

        MarkerOptions markertwo = new MarkerOptions()
                .title(title)
                .snippet(desII)
                .position(secondPosition)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp));


        mMap.clear();
        mMap.addMarker(marker);
        mMap.addMarker(markertwo);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(setposition));

    }

    private void GS() {
/**Gs    **/
        final double Pace_PLV_GTOP_LNG = -73.808604;
        final double Pace_PLV_GTOP_LAT = 41.129585;

        final double Pace_PLV_GLEFTONE_LNG = -73.809494;
        final double Pace_PLV_GLEFTONE_LAT = 41.129116;

        final double Pace_PLV_GLEFTONEHALF_LNG = -73.809189;
        final double Pace_PLV_GLEFTONEHALF_LAT = 41.129358;

        final double Pace_PLV_GLEFTTWO_LNG = -73.809473;
        final double Pace_PLV_GLEFTTWO_LAT = 41.128655;

        final double Pace_PLV_GLEFTTHREE_LNG = -73.809237;
        final double Pace_PLV_GLEFTTHREE_LAT = 41.128429;

        final double Pace_PLV_GLEFTFOUR_LNG = -73.808540;
        final double Pace_PLV_GLEFTFOUR_LAT = 41.128082;

        final double Pace_PLV_GLEFTFIVE_LNG = -73.807890;
        final double Pace_PLV_GLEFTFIVE_LAT = 41.128114;

        final double Pace_PLV_GLEFTSIX_LNG = -73.807590;
        final double Pace_PLV_GLEFTSIX_LAT = 41.128251;

        final double Pace_PLV_GLEFTSEVEN_LNG = -73.807461;
        final double Pace_PLV_GLEFTSEVEN_LAT = 41.128647;

        final double Pace_PLV_GLEFTEIGHT_LNG = -73.807687;
        final double Pace_PLV_GLEFTEIGHT_LAT = 41.128624;


        final double Pace_PLV_GLEFTNINE_LNG = -73.808132;
        final double Pace_PLV_GLEFTNINE_LAT = 41.128608;

        /**Gs    **/


        mMap.clear();
    /*
     *
      *
░░░░█░░░░▒▒▒▒▒▒▒▒▒▒▒▒░░▀▀▄
░░░█░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░░█
░░█░░░░░░▄██▀▄▄░░░░░▄▄▄░░░█
░▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░░█
█▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒▒█
█▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀▄▒█
░█▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░░█
░░█░░▀▄▀█▄▄░█▀▀▀▄▄▄▄▀▀█▀██░█
░░░█░░██░░▀█▄▄▄█▄▄█▄████░█
░░░░█░░░▀▀▄░█░░░█░███████░█
░░░░░▀▄░░░▀▀▄▄▄█▄█▄█▄█▄▀



*/
        LatLng Cool = new LatLng(Pace_PLV_GTOP_LAT, Pace_PLV_GTOP_LNG);
        mMap.addMarker(new MarkerOptions().title("YOU").snippet("YES YOU")
                .position(Cool).icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolg = new LatLng(Pace_PLV_GLEFTONE_LAT, Pace_PLV_GLEFTONE_LNG);
        mMap.addMarker(new MarkerOptions().title("YOU").snippet("REALLY").position(Coolg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng CoolgG = new LatLng(Pace_PLV_GLEFTONEHALF_LAT, Pace_PLV_GLEFTONEHALF_LNG);
        mMap.addMarker(new MarkerOptions().title("I MEAN").snippet("REALLY").position(CoolgG)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolggg = new LatLng(Pace_PLV_GLEFTTWO_LAT, Pace_PLV_GLEFTTWO_LNG);
        mMap.addMarker(new MarkerOptions().title("REALLY").snippet("REALLY").position(Coolggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolgggg = new LatLng(Pace_PLV_GLEFTTHREE_LAT, Pace_PLV_GLEFTTHREE_LNG);
        mMap.addMarker(new MarkerOptions().title("TYPED").snippet("IN").position(Coolgggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolggggg = new LatLng(Pace_PLV_GLEFTFOUR_LAT, Pace_PLV_GLEFTFOUR_LNG);
        mMap.addMarker(new MarkerOptions().title("MY").snippet("NAME?!").position(Coolggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolgggggg = new LatLng(Pace_PLV_GLEFTFIVE_LAT, Pace_PLV_GLEFTFIVE_LNG);
        mMap.addMarker(new MarkerOptions().title("MY").snippet("NAME?!").position(Coolgggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolggggggg = new LatLng(Pace_PLV_GLEFTSIX_LAT, Pace_PLV_GLEFTSIX_LNG);
        mMap.addMarker(new MarkerOptions().title("REALLY").snippet(" MY NAME?!").position(Coolggggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolgggggggg = new LatLng(Pace_PLV_GLEFTSEVEN_LAT, Pace_PLV_GLEFTSEVEN_LNG);
        mMap.addMarker(new MarkerOptions().title("WELL HERE").snippet("IS YOUR").position(Coolgggggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolggggggggg = new LatLng(Pace_PLV_GLEFTEIGHT_LAT, Pace_PLV_GLEFTEIGHT_LNG);
        mMap.addMarker(new MarkerOptions().title("REWARD").snippet("REWARD").position(Coolggggggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        LatLng Coolgggggggggg = new LatLng(Pace_PLV_GLEFTNINE_LAT, Pace_PLV_GLEFTNINE_LNG);
        mMap.addMarker(new MarkerOptions().title("REWARD").snippet("REWARD").position(Coolgggggggggg)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.p_marker_50_65dp)));
        Toast.makeText(this, "Achievement Unlocked: Meet the Developers", Toast.LENGTH_LONG).show();
        Log.d("Easter Egg", "Found");

    }


}