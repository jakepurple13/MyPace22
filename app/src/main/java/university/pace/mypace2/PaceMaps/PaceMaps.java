package university.pace.mypace2.PaceMaps;


import android.Manifest;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import university.pace.mypace2.MainActivity;
import university.pace.mypace2.PaceMaps.Buildings;
import university.pace.mypace2.R;

//TODO:ADD MORE PLACES tutoring center for both campuses and get enter search working
public class PaceMaps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private final int REQUEST_LOCATION = 1;
    private double latitude, longitude;
    private NycBuildings.Building Nycmap;
    private Buildings.Building pacemap;
    private Buildings pace;
    private AutoCompleteTextView location_tf;
    private ToggleButton toggle;
    private ToggleButton tType;

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


    public final double Pace_PLV_WILOCX_LNG = -73.807506;
    public final double Pace_PLV_WILCOX_LAT = 41.126033;


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

    public final double Pace_PLV_A_LNG = -73.807309;
    public final double Pace_PLV_A_LAT = 41.125529;

    public final double Pace_PLV_B_LNG = -73.807561;
    public final double Pace_PLV_B_LAT = 41.125008;

    public final double Pace_PLV_C_LNG = -73.809089;
    public final double Pace_PLV_C_LAT = 41.125372;


    public final double Pace_PLV_D_LNG = -73.809075;
    public final double Pace_PLV_D_LAT = 41.126038;


    public final double Pace_PLV_E_LNG = -73.809072;
    public final double Pace_PLV_E_LAT = 41.126806;


    public final double Pace_PLV_F_LNG = -73.810249;
    public final double Pace_PLV_F_LAT = 41.126912;

    public final double Pace_PLV_H_LNG = -73.809759;
    public final double Pace_PLV_H_LAT = 41.129091;

    public final double Pace_PLV_I_LNG = -73.808624;
    public final double Pace_PLV_I_LAT = 41.129500;

    public final double Pace_PLV_ATHELTICS_LNG = -73.810408;
    public final double Pace_PLV_ATHELTICS_LAT = 41.127869;


    public final double Pace_PLV_O_LNG = -73.803973;
    public final double Pace_PLV_O_LAT = 41.127906;

    public final double Pace_PLV_P_LNG = -73.808337;
    public final double Pace_PLV_P_LAT = 41.131209;

    public final double Pace_PLV_T_LNG = -73.806636;
    public final double Pace_PLV_T_LAT = 41.127156;

    public final double Pace_PLV_U_LNG = -73.804735;
    public final double Pace_PLV_U_LAT = 41.127120;

    public final double Pace_PLV_X_LNG = -73.805277;
    public final double Pace_PLV_X_LAT = 41.127439;

    public final double Pace_PLV_Z_LNG = -73.807524;
    public final double Pace_PLV_Z_LAT = 41.126950;

    public final double Pace_PLV_FITTRAIL_LNG = -73.810249;
    public final double Pace_PLV_FITTRAIL_LAT = 41.131481;


    public final double Pace_PLV_BUSSTOP_NORTH_LNG = -73.804574;
    public final double Pace_PLV_BUSSTOP_NORTH_LAT = 41.127794;

    public final double Pace_PLV_BUSSTOP_MILLER_LNG = -73.808884;
    public final double Pace_PLV_BUSSTOP_MILLER_LAT = 41.127006;

    public final double Pace_PLV_BUSSTOP_WILCOX_LNG = -73.807798;
    public final double Pace_PLV_BUSSTOP_WILCOX_LAT = 41.125930;


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

    public final double Pace_PLV_BASEBALL_LNG = -73.811428;
    public final double Pace_PLV_BASEBALL_LAT = 41.129816;


    public final double Pace_PLV_FOOTBALL_LNG = -73.810629;
    public final double Pace_PLV_FOOTBALL_LAT = 41.128487;

    /** PACE Weschester Longitude/Latitude  **/


    /**
     * PACE New York Longitude/Latitude
     **/
/*If User is at this point(Yonkers) they are consider in the city*/
    public final double Pace_NYC_ACCESSPOINT_LNG = -73.898747;
    public final double Pace_NYC_ACCESSPOINT_LAT = 40.931210;


    public final double Pace_NYC_OnePacePlaza_LNG = -74.005452;
    public final double Pace_NYC_OnePacePlaza_LAT = 40.711590;

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

    public final double Pace_NYC_William140_LNG = -74.006296;
    public final double Pace_NYC_William140_LAT = 40.709313;


    public final double Pace_NYC_OnePacePlaza_CCAR_LNG = -74.005156;
    public final double Pace_NYC_OnePacePlaza_CCAR_LAT = 40.711335;

    public final double Pace_NYC_OnePacePlaza_Honors_LNG = -74.005457;
    public final double Pace_NYC_OnePacePlaza_Honors_LAT = 40.711489;


    public final double Pace_NYC_William163_IP_LNG = -74.006369;
    public final double Pace_NYC_William163_IP_LAT = 40.710164;

    public final double Pace_NYC_Maria_LNG = -74.004416;
    public final double Pace_NYC_Maria_LAT = 40.710814;

    public final double Pace_NYC__Court_Yard_LNG = -74.004845;
    public final double Pace_NYC_Court_Yard_LAT = 40.711099;

    public final double Pace_NYC_OnePace_TUTORING_LNG = -74.006542;
    public final double Pace_NYC_OnePace_TUTORING_LAT = 40.711491;

    public final double Pace_NYC_OnePace_IT_LNG = -74.005286;
    public final double Pace_NYC_OnePace_IT_LAT = 40.711224;

    public final double Pace_NYC_OnePace_OSA_LNG = -74.004995;
    public final double Pace_NYC_OnePace_OSA_LAT = 40.711473;

    public final double Pace_NYC_ParksRow_LNG = -74.006211;
    public final double Pace_NYC_ParksRow_LAT = 40.711674;

    public final double Pace_NYC_HEALTH_LNG = -74.006690;
    public final double Pace_NYC_HEALTH_LAT = 40.711563;

    public final double Pace_NYC_ParksRow_Bookstore_LNG = -74.006244;
    public final double Pace_NYC_ParksRow_Bookstore_LAT = 40.711616;

    public final double Pace_NYC_Library_LNG = -74.004317;
    public final double Pace_NYC_Library_LAT = 40.710556;


    public final double Pace_NYC_Lubin_LNG = -74.005084;
    public final double Pace_NYC_Lubin_LAT = 40.711196;

    public final double Pace_NYC_Cafe101_LNG = -74.004437;
    public final double Pace_NYC_Cafe101_LAT = 40.710538;

    public final double Pace_NYC_BEEKMAN_LNG = -74.005800;
    public final double Pace_NYC_BEEKMAN_LAT = 40.710577;

    public final double Pace_NYC_Confucius_LNG = -74.006387;
    public final double Pace_NYC_Confucius_LAT = 40.711704;

    public final double Pace_NYC_SSS_LNG = -74.006422;
    public final double Pace_NYC_SSS_LAT = 40.711416;

    public final double Pace_NYC_Schimmel_LNG = -74.004623;
    public final double Pace_NYC_Schimmel_LAT = 40.710846;


    public final double Pace_NYC_CareerS_LNG = -74.006465;
    public final double Pace_NYC_CareerS_LAT = 40.711359;

    /**
     * around campus
     **/
    public final double Dennys_LNG = -74.005978;
    public final double Dennys_LAT = 40.711534;

    public final double BeekmanPub_LNG = -74.006360;
    public final double BeekmanPub_LAT = 40.710862;

    public final double DunknDonuts_LNG = -74.006360;
    public final double DunknDonuts_LAT = 40.710862;

    /**
     * around campus
     **/


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
    public LatLng PaceUniPLV_Baseball = new LatLng(Pace_PLV_BASEBALL_LAT, Pace_PLV_BASEBALL_LNG);
    public LatLng PaceUniPLV_Football = new LatLng(Pace_PLV_FOOTBALL_LAT, Pace_PLV_FOOTBALL_LNG);

    /**
     * public LatLng PaceUniPLV_PARKING_A = new LatLng(Pace_PLV_A_LAT, Pace_PLV_A_LNG);
     * public LatLng PaceUniPLV_PARKING_B = new LatLng(Pace_PLV_B_LAT, Pace_PLV_B_LNG);
     * public LatLng PaceUniPLV_PARKING_C = new LatLng(Pace_PLV_C_LAT, Pace_PLV_C_LNG);
     * public LatLng PaceUniPLV_PARKING_D = new LatLng(Pace_PLV_D_LAT, Pace_PLV_D_LNG);
     * public LatLng PaceUniPLV_PARKING_E = new LatLng(Pace_PLV_E_LAT, Pace_PLV_E_LNG);
     * public LatLng PaceUniPLV_PARKING_F = new LatLng(Pace_PLV_F_LAT, Pace_PLV_F_LNG);
     * public LatLng PaceUniPLV_PARKING_H = new LatLng(Pace_PLV_H_LAT, Pace_PLV_H_LNG);
     * public LatLng PaceUniPLV_PARKING_I = new LatLng(Pace_PLV_I_LAT, Pace_PLV_I_LNG);
     * public LatLng PaceUniPLV_PARKING_O = new LatLng(Pace_PLV_O_LAT, Pace_PLV_O_LNG);
     * public LatLng PaceUniPLV_PARKING_P = new LatLng(Pace_PLV_P_LAT, Pace_PLV_P_LNG);
     * public LatLng PaceUniPLV_PARKING_T = new LatLng(Pace_PLV_T_LAT, Pace_PLV_T_LNG);
     * public LatLng PaceUniPLV_PARKING_U = new LatLng(Pace_PLV_U_LAT, Pace_PLV_U_LNG);
     * public LatLng PaceUniPLV_PARKING_X = new LatLng(Pace_PLV_X_LAT, Pace_PLV_X_LNG);
     * public LatLng PaceUniPLV_PARKING_Z = new LatLng(Pace_PLV_Z_LAT, Pace_PLV_Z_LNG);
     **/

    public LatLng PaceUniPLV_BUS_NORTH = new LatLng(Pace_PLV_BUSSTOP_NORTH_LAT, Pace_PLV_BUSSTOP_NORTH_LNG);
    public LatLng PaceUniPLV_BUS_MILLER = new LatLng(Pace_PLV_BUSSTOP_MILLER_LAT, Pace_PLV_BUSSTOP_MILLER_LNG);
    public LatLng PaceUniPLV_BUS_WILCOX = new LatLng(Pace_PLV_BUSSTOP_WILCOX_LAT, Pace_PLV_WILOCX_LNG);
    public LatLng PaceUniPLV_FITNESSTRAIL = new LatLng(Pace_PLV_FITTRAIL_LAT, Pace_PLV_FITTRAIL_LNG);

    // Add a marker in NYC and move the camera
    public LatLng PaceUniNYC = new LatLng(Pace_NYC_OnePacePlaza_LAT, Pace_NYC_OnePacePlaza_LNG);
    public LatLng PaceUniNYC_OnePacePlaza = new LatLng(Pace_NYC_OnePacePlaza_LAT, Pace_NYC_OnePacePlaza_LNG);
    public LatLng PaceUniNYC_Broadway = new LatLng(Pace_NYC_Broadway_LAT, Pace_NYC_Broadway_LNG);
    public LatLng PaceUniNYC_William156 = new LatLng(Pace_NYC_William_LAT, Pace_NYC_William_LNG);
    public LatLng PaceUniNYC_William163 = new LatLng(Pace_NYC_WilliamII_LAT, Pace_NYC_WilliamII_LNG);
    public LatLng PaceUniNYC_William163_IP = new LatLng(Pace_NYC_William163_IP_LAT, Pace_NYC_William163_IP_LNG);
    public LatLng PaceUniNYC_William140 = new LatLng(Pace_NYC_William140_LAT, Pace_NYC_William140_LNG);
    public LatLng PaceUniNYC_Fulton = new LatLng(Pace_NYC_fulton_LAT, Pace_NYC_fulton_LNG);
    public LatLng PaceUniNYC_JohnStreet = new LatLng(Pace_NYC_JohnStreet_LAT, Pace_NYC_JohnStreet_LNG);
    public LatLng PaceUniNYC_OnePace_Courtyard = new LatLng(Pace_NYC_Court_Yard_LAT, Pace_NYC__Court_Yard_LNG);
    public LatLng PaceUniNYC_Bookstore = new LatLng(Pace_NYC_ParksRow_Bookstore_LAT, Pace_NYC_ParksRow_Bookstore_LNG);
    public LatLng PaceUniNYC_Cafe101 = new LatLng(Pace_NYC_Cafe101_LAT, Pace_NYC_Cafe101_LNG);
    public LatLng PaceUniNYC_Lubin = new LatLng(Pace_NYC_Lubin_LAT, Pace_NYC_Lubin_LNG);
    public LatLng PaceUniNYC_Library = new LatLng(Pace_NYC_Library_LAT, Pace_NYC_Library_LNG);
    public LatLng PaceUniNYC_Confucius = new LatLng(Pace_NYC_Confucius_LAT, Pace_NYC_Confucius_LNG);
    public LatLng PaceUniNYC_ParksRow = new LatLng(Pace_NYC_ParksRow_LAT, Pace_NYC_ParksRow_LNG);
    public LatLng PaceUniNYC_Maria = new LatLng(Pace_NYC_Maria_LAT, Pace_NYC_Maria_LNG);
    public LatLng PaceUniNYC_Schimmel = new LatLng(Pace_NYC_Schimmel_LAT, Pace_NYC_Schimmel_LNG);
    public LatLng PaceUniNYC_BEEKMAN = new LatLng(Pace_NYC_BEEKMAN_LAT, Pace_NYC_BEEKMAN_LNG);
    public LatLng PaceUniNYC_Tutoring = new LatLng(Pace_NYC_OnePace_TUTORING_LAT, Pace_NYC_OnePace_TUTORING_LNG);
    public LatLng PaceUniNYC_IT = new LatLng(Pace_NYC_OnePace_IT_LAT, Pace_NYC_OnePace_IT_LNG);
    public LatLng PaceUniNYC_Health = new LatLng(Pace_NYC_HEALTH_LAT, Pace_NYC_HEALTH_LNG);
    public LatLng PaceUniNYC_SSS = new LatLng(Pace_NYC_SSS_LAT, Pace_NYC_SSS_LNG);
    public LatLng PaceUniNYC_OSA = new LatLng(Pace_NYC_OnePace_OSA_LAT, Pace_NYC_OnePace_OSA_LNG);
    public LatLng PaceUniNYC_Honors = new LatLng(Pace_NYC_OnePacePlaza_Honors_LAT, Pace_NYC_OnePacePlaza_Honors_LNG);
    public LatLng PaceUniNYC_CCAR = new LatLng(Pace_NYC_OnePacePlaza_CCAR_LAT, Pace_NYC_OnePacePlaza_CCAR_LNG);
    public LatLng PaceUniNYC_CareerSer = new LatLng(Pace_NYC_CareerS_LAT, Pace_NYC_CareerS_LNG);
    /*Default Map view*/
    private LatLng Position = PaceUniPLV;
    //:TODO Get working on higer API Phones
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_pace_maps);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /*** Toggle For map PLV /NYC      *******************************************************/
        toggle = (ToggleButton) findViewById(R.id.Toggle);



        /**toogle functions************/



        /**toggle map type******/
        tType = (ToggleButton) findViewById(R.id.satellite);

        tType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeType(buttonView);
                    tType.setBackgroundResource(R.drawable.map_pace_light);
                    Log.d("change map type", "toggle pressed map");

                    // The toggle satellite

                } else {
                    changeType(buttonView);
                    findViewById(R.id.satellite).setBackgroundResource(R.drawable.google_earth);
                    Log.d("change map type", "toggle pressed satellite");
                    // The toggle on Map

                }
            }
        });

        /*** Toggle For map PLV /NYC      *******************************************************/

/**refresh button****/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ColorStateList rippleColor = ContextCompat.getColorStateList(this, R.drawable.fab_ripple_color);
        fab.setBackgroundTintList(rippleColor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshMap(v);

            }
        });
/**refresh button****/





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

            // other 'case' lines to check_campus for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        pace = new Buildings(this);


        try {


            /** Premission request to Find current location**/
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);  //this does not work without permission check_campus
 /*   Gets user Location */
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        /*ECHO SHOW USER's LAT */
            System.out.println("user's latitude" + latitude + "user's longitude" + longitude);




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
/** Auto correct initialize onMap ready for user**/


                /**Search with enter******/
                location_tf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            final String location_search = location_tf.getText().toString();
                            performSearch(location_search);
                            Log.d("Entered Search", "pressed Enter");
                            return true;

                        }
                        return false;
                    }
                });

                /**Search with enter******/
                mMap.getUiSettings().setZoomControlsEnabled(true);

            }


        } catch (Exception e) {

/*If latitude not there or something- default */

            Log.d("error", e.toString());
        }
                                                                                            /*On info Window Clicked */
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                double DirectionsOnClickLat = marker.getPosition().latitude;
                double DirectionsOnClickLong = marker.getPosition().longitude;//gets clicked info window position
                OnLocationGo(DirectionsOnClickLat, DirectionsOnClickLong, latitude, longitude);//passes to google maps
                //   Log.d("Pace marker clicked",DirectionsOnClickLong);

            }
        });

                                                                                            /*On info Window Clicked End*/
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
     * Show different map manual Pace/NYC also controls toggle button graphic
     **/
    public void changeMap(View view) {
        if (mMap != null) {

            if (Position == PaceUniPLV) {
                mMap.clear();  //clears anything on the map that should not be there 'gs' jr' 'jl'
                NYVCampusOnMapView();
                Position = PaceUniNYC;
                Log.d("User toggled", "Showing NYC");
                Log.d(" Grey", "Map");

            } else {
                mMap.clear(); //clears anything on the map that should not be there 'gs' jr' 'jl'
                PleasantvilleCampusOnMapView();
                Position = PaceUniPLV;
                Log.d("User toggled", "Showing PLV");
                Log.d(" Blue", "Map");

            }


        }
    }

    public void refreshMap(View view) {
        if (mMap != null) {

            if (Position == PaceUniPLV) {
                mMap.clear();  //clears anything on the map
                PleasantvilleCampusOnMapView();
                Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();
                Log.d("User toggled", "refresh");


            } else {
                mMap.clear(); //clears anything on the map
                NYVCampusOnMapView();
                Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();
                Log.d("User toggled", "refresh");


            }


        }
    }


    private void PleasantvilleCampusOnMapView() {


        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_OSA)
                .title("Office of Student Assistance").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_osa_icon)).snippet(
                        "Services of Financial Aid, Student Accounts"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Environmental
        ).title("Environmental Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.environmentmkr)).snippet("Charming place open to" +
                " all and is especially a favorite destination of families with young children. "));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Miller
        ).title("Miller Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)).snippet("College Academic Building, " +
                "College Classroom "));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Lienhard)
                .title("Lienhard Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)).snippet("College Academic Building" +
                        " and College Nursing Building"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Goldstien)
                .title("Goldstien Academic Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)).snippet(
                        "Home of Lubin and Computer Science students"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_GoldstienGym)
                .title("Goldstien Fitness Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_gym_dogpound)).snippet(
                        "Gym,Pool & Basketball courts & Health Center"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Marks)
                .title("Marks Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_dorms)).snippet("Welcome Center & Mathematics department"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Dyson)
                .title("Dyson Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)).snippet("Home to all Science students & Science Professors"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Wilcox)
                .title("Wilcox Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)).snippet("Academic Building & " +
                        "Technical Support- first floor"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Kessel)
                .title("Kessel Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_kessel)).snippet("Campus Food & Dinning Hall," +
                        "Lounges,Bookstore"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Library)
                .title("Mortola Library").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library)).snippet("Open 24 Hours for" +
                        "studying and group work"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Pond)
                .title("Choate Pond").icon(BitmapDescriptorFactory.fromResource(R.drawable.pondmkr)).snippet("Pace's iconic land mark"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Choate
        ).title("Choate House").icon(BitmapDescriptorFactory.fromResource(R.drawable.choatemkr)).snippet("English Department and Art show"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_North
        ).title("North Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)).snippet("Student dormitory located near Entrance 3"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Elm)
                .title("Elm Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)).snippet("NEW Student dormitory part of the master plan"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Alumni)
                .title("Alumni Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)).snippet("NEW Student dormitory part of the master plan & Security Office"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Martin)
                .title("Martin Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)).snippet("Student dormitory located near Entrance 3"));


        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Baseball)
                .title("Baseball Field").icon(BitmapDescriptorFactory.fromResource(R.drawable.baseballmker)).snippet("NEW Baseball field near Goldstien Gym"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_Football)
                .title("Football Field").icon(BitmapDescriptorFactory.fromResource(R.drawable.football_mker)).snippet("Rebuilt Football field behind Choate house"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_FITNESSTRAIL)
                .title("Fit trail").icon(BitmapDescriptorFactory.fromResource(R.drawable.fit)).snippet("On campus trail for walking"));

        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_BUS_NORTH)
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)).snippet("Campus shuttle stop-North Entrance 3"));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_BUS_MILLER)
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)).snippet("Campus shuttle stop- Entrance 2"));
        mMap.addMarker(new MarkerOptions().position(PaceUniPLV_BUS_WILCOX)
                .title("Bus Stop").icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)).snippet("Campus shuttle stop- Entrance 1"));


/**
 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_A)
 .title("Parking lot A").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_a)).snippet("Campus Parking- Front of Dyson"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_B)
 .title("Parking lot B").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_b)).snippet("Campus Parking- Right of Dyson"));
 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_C)
 .title("Parking lot C").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_c)).snippet("Campus Parking-"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_D)
 .title("Parking lot D").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_d)).snippet("Campus Parking- Behind Marks hall"));
 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_E)
 .title("Parking lot E").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_e)).snippet("Campus Parking- Side of Miller"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_F)
 .title("Parking lot F").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_f)).snippet("Campus Parking- Behind  Miller"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_H)
 .title("Parking lot H").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_h)).snippet("Campus Parking- By the Gym"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_I)
 .title("Parking lot I").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_i)).snippet("Campus Parking- Between Miller and Alumni"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_O)
 .title("Parking lot O").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_o)).snippet("Campus Parking- North hall"));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_P)
 .title("Parking lot P").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_p)).snippet("Campus Parking- Near OSA "));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_T)
 .title("Parking lot T").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_t)).snippet("Campus Parking- Above Library parking lot Z "));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_U)
 .title("Parking lot U").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_u)).snippet("Campus Parking- Near Goldstien Academic Center "));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_X)
 .title("Parking lot X").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_x)).snippet("Campus Parking- Lower Goldstien Academic Center "));

 mMap.addMarker(new MarkerOptions().position(PaceUniPLV_PARKING_Z)
 .title("Parking lot Z").icon(BitmapDescriptorFactory.fromResource(R.drawable.park_z)).snippet("Campus Parking- Library"));
**/

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniPLV, 16));


    }

    private void NYVCampusOnMapView() {
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_OnePacePlaza)

                .title("One Pace Plaza").snippet("•Lubin Administration and Advisement-4th FL").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_one_plaza)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Schimmel)
                .title("Schimmel Center").snippet("•Performance to the arts" +
                        "– B level ").icon(BitmapDescriptorFactory.fromResource(R.drawable.schimmelmkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Honors)
                .title("Office for Student Success/Honors College")
                .snippet("•Office for any need on campus Rm-Y21–2nd FL/Rm W209A").icon(BitmapDescriptorFactory.fromResource(R.drawable.studentsuccessmkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Lubin)
                .title("Lubin School of Business").snippet("•The business school of Pace University").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Maria)
                .title("Maria's Tower").snippet("•TV lounge and a study lounge on every floor").icon(BitmapDescriptorFactory.fromResource(R.drawable.maria_tower)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Cafe101)
                .title("Cafe 101").snippet("•Starbucks/ Food Court-1st Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_kessel))); //TODO: we have food
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Library)
                .title("Henry Birnbaum Library").snippet("•Study lounges/private group study rooms available-1st Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_OnePace_Courtyard)
                .title("Court Yard")
                .snippet("•Relax in this outdoor area- 1st Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.courtyardmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_IT)
                .title("Information Technology Center").snippet("•Tech support available- 2nd Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.itmkr)));

        //   mMap.addMarker(new MarkerOptions().position(PaceUniNYC)
        //    .title("Pace University -City Campus").snippet("1 Pace Plaza, New York, NY 10038").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_marks)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_ParksRow)
                .title("41 Parks Row").snippet("•Classrooms and offices/SDCA-8th").icon(BitmapDescriptorFactory.fromResource(R.drawable.classmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Bookstore)
                .title("Barns & Noble BookStore").snippet("•Purchase books,apparel and more").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_library_kid)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Tutoring)
                .title("Tutoring Center/First Year Experience/Advising Center for Exploring Majors")
                .snippet("•Student/Professional tutors and more available- 2nd Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.tutormkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Health)
                .title("Health Center").snippet("•Nurse available-Rm 313 3rd Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.healthmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_SSS)
                .title("Student Support Services")
                .snippet("•First year student support/CURE- 4th Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.sssmkr)));




        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William163)
                .title("163 William Street").snippet("•Seidenberg School of Computer Science-2nd Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.seidenbergmkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William163_IP)
                .title("International Programs and Services").snippet("•Study Abroad/International Student AD-16th Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_JohnStreet)
                .title("55 John Street").snippet("•Residence Halls").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Broadway)
                .title("182 Broadway").snippet("•Residence Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William156)
                .title("156 William Street").snippet("•Counseling Center–8th FL/Human Resources-5th Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.hrmkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_William140)
                .title("140 William Street").snippet("•Performing Arts").icon(BitmapDescriptorFactory.fromResource(R.drawable.schimmelmkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_BEEKMAN)
                .title("33 Beekman st").snippet("•Student housing").icon(BitmapDescriptorFactory.fromResource(R.drawable.dormmkr)));


        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_OSA)
                .title("Office of Student Assistance").snippet("•Holds/Financial questions/Degree verification -1st Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.pace_osa_icon)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_Confucius)
                .title("Office of Multicultural Affairs & Dean for Students")
                .snippet("•Social Justice Center/Student Government- 9th Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.socialjusticemkr)));
        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_CCAR)
                .title("Center for Undergraduate Research Experiences ")
                .snippet("•CCAR Rm-Y31–3rd Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.undergrademkr)));

        mMap.addMarker(new MarkerOptions().position(PaceUniNYC_CareerSer)
                .title("Career Services/Dyson Administration and Advisement")
                .snippet("•Future for you begins here-14th FL/Dyson-16th Fl").icon(BitmapDescriptorFactory.fromResource(R.drawable.csmkr)));
//TODO: Add new locations to search engine

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PaceUniNYC, 17));

    }

    private void ShowCampusNearMe(double lat) {

        if (lat > Pace_NYC_ACCESSPOINT_LAT)//TODO:CHANGE THE LAT
        {
 /*If latitude is greater than user latitude user is north */
            PleasantvilleCampusOnMapView();

            Position = PaceUniPLV;
            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        if (Position == PaceUniPLV) {
//TODO: FIX NYC TOGGLE BUTTON ON START

                            changeMap(buttonView);
                            toggle.setBackgroundResource(R.drawable.togglenyc);
                            Log.d("toggle pressed Blue", "NYC Map");
                            Toast.makeText(PaceMaps.this, "Now viewing the NYC Campus", Toast.LENGTH_LONG).show();
                            // The toggle while on  PLV
                        }

                    } else {

                        if (Position == PaceUniNYC) {
                            changeMap(buttonView);
                            findViewById(R.id.Toggle).setBackgroundResource(R.drawable.toggleplv);
                            Toast.makeText(PaceMaps.this, "Now viewing the Westchester Campus", Toast.LENGTH_LONG).show();
                            Log.d("toggle pressed Yellow", "PLV Map");
                            // The toggle while on NYC
                        }


                    }


                }
            });
            Log.d("User is North", "Showing PLV");

        }
           /*If latitude is less than user latitude user is south  */
        if (lat <= Pace_NYC_ACCESSPOINT_LAT)  //TODO:CHANGE THE LAT
        {
            NYVCampusOnMapView();
            Position = PaceUniNYC;
            /**Starts on NYC Based on user's latitude and changes the toggle start graphic**/

            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        if (Position == PaceUniNYC) {
                            //TODO: FIX NYC TOGGLE BUTTON ON START

                            changeMap(buttonView);
                            toggle.setText(R.string.togglePLV);
                            toggle.setBackgroundResource(R.drawable.plv_toggle);
                            Log.d("toggle pressed Blue", "NYC Map");

                            // The toggle on PLV
                        }

                    } else {

                        if (Position == PaceUniPLV) {
                            changeMap(buttonView);
                            toggle.setText(R.string.toggleNYC);
                            findViewById(R.id.Toggle).setBackgroundResource(R.drawable.nyc_toggle);
                            Log.d("toggle pressed Yellow", "PLV Map");

                            // The toggle on NYC
                        }


                    }


                }
             });


            Log.d("User is South", "Showing NYC");
        }
    }

    /**
     * Search any Building
     **/
    public void onSearch(final View view) {

// Get a reference to the AutoCompleteTextView in the layout
        final String location_search = location_tf.getText().toString();
        /**search with enter button**/
        Log.d("debug", location_tf.toString());

        performSearch(location_search); //search button on screen

    }

    private void performSearch(String location_search) {

        if (location_search.equalsIgnoreCase("George Samuels") && Position == PaceUniPLV)
            GS();



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

                }                                                                                                    /*check_campus list
                                                                                                                            for matches*/ else {



                        /*returns LatLng Position*/
                    BuildingLatLng = pacemap.PaceLocation(location_search);

                          /*returns LatLng Positions for keyword class*/
                    if (location_search.equalsIgnoreCase("class") || location_search.equalsIgnoreCase("classes")) {
                        //TODO:won't show miller?
                        SetClassMarker(PaceUniPLV_Dyson, PaceUniPLV_Goldstien, PaceUniPLV_Lienhard, BuildingLatLng, location_search,
                                "Here is at Dyson Hall ", "Here is at Goldstien ", "found at Lienhard Hall " + "", "found at " + "Miller Hall");

                    } else if (location_search.equalsIgnoreCase("food") && Position == PaceUniPLV) {
                        SetMultiMarker(PaceUniPLV_Kessel, BuildingLatLng, PaceUniPLV_Miller, location_search,
                                "Here is ", "found at the " + "Pace Perk", "found at " + "Miller Hall");
                    } else if (location_search.equalsIgnoreCase("goldstien") && Position == PaceUniPLV) {
                        SetTwoMarker(PaceUniPLV_Goldstien, BuildingLatLng, location_search,
                                "Here is ", "found at the " + "Gym");
                    }  /** got lazy   **/

                    else {
                        SetMarker(BuildingLatLng, location_search, "Here is ");
                        Log.d("spot found ", "show on map");
                    }
                }


            }

            if (Position == PaceUniNYC) {
          /*reads from text list*/
                LatLng BuildingLatLng;
                Nycmap = new NycBuildings(this).new Building(location_search);

                   /*if nothing is on the list show output*/
                if (Nycmap.NYClocation(location_search) == null) {
                    Toast.makeText(this, "No matches found on the NYC Campus", Toast.LENGTH_LONG).show();
                    Log.d("not on list ", "show toast");
                }                                                                                                    /*check_campus list
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

    public LatLng CheckLocation(double lat) {

        if (lat > Pace_NYC_ACCESSPOINT_LAT) {

            Position = PaceUniPLV;
            return Position;
        } else if (lat <= Pace_NYC_ACCESSPOINT_LAT) {
            Position = PaceUniNYC;
            return Position;
        } else
            Position = PaceUniPLV;


        return Position;


    }


    private void OnLocationGo(final double goLat, final double goLong, double userLat, double userLong) {

        final String UserLat = String.valueOf(userLat);
        final String UserLong = String.valueOf(userLong); //gets user latitude & longitude


        final String MarkerLong = String.valueOf(goLong); //gets user longitude & latitude
        final String MarkerLat = String.valueOf(goLat);

        Log.d("UserLong", UserLong);
        Log.d("UserLat", UserLat);

        AlertDialog ad = new AlertDialog.Builder(this).setMessage(
                R.string.OnPressMessage).setTitle(
                R.string.UndertitleLocation).setCancelable(false)
                .setNeutralButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Toast.makeText(PaceMaps.this, "Maybe next time then...", Toast.LENGTH_LONG).show();  // User selects Cancel, discard all changes
                            }
                        }).setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                        Uri.parse("http://maps.google.com/maps?saddr=" + UserLat
                                                + "," + UserLong + "&" + "daddr=" + MarkerLat + "," + MarkerLong));
                                startActivity(intent);
                                Log.d("OkDirections", "user is getting directions");

                            }
                        }).show();

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