package university.pace.mypace2;


import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;


import java.util.ArrayList;
import java.util.Date;

import university.pace.mypace2.CalendarScreen.CalendarScreen;
import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
import university.pace.mypace2.PaceEmail.PMail;
import university.pace.mypace2.PaceMaps.Buildings;
import university.pace.mypace2.PaceMaps.PaceMaps;
import university.pace.mypace2.TestingPackage.CardTest;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button phoneButton;
    AudioManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] perms = {"android.permission.READ_CALENDAR", "android.permission.Write_CALENDAR",
                "android.permission.GET_ACCOUNTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

        int permsRequestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, permsRequestCode);
        }


        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        phoneButton = (Button) findViewById(R.id.numbers);

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScreen(ImportantNumbers.class);
            }
        });

        phoneButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Date dec = new Date();
                dec.setMonth(11);

                if (new Date().getMonth() == dec.getMonth()) {

                }

                final int normalSound = am.getStreamVolume(AudioManager.STREAM_MUSIC);

                am.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                        15);

                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.rejectionhotline);

                mediaPlayer.start();
                int duration = mediaPlayer.getDuration();
                int current_position = mediaPlayer.getCurrentPosition();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        am.setStreamVolume(
                                AudioManager.STREAM_MUSIC,
                                am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                                normalSound);
                    }
                });

                Toast.makeText(MainActivity.this, "Achievement Unlocked: Get Rejected", Toast.LENGTH_LONG).show();

                return false;
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    break;

                } else
                    Toast.makeText(this, "For full app functions these premission are needed", Toast.LENGTH_LONG).show();
        }

    }
    private boolean canMakeSmores() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);

    }




    /*private boolean shouldWeAsk(String permission){

        return (sharedPreferences.getBoolean(permission, true));

    }



    private void markAsAsked(String permission){

        sharedPreferences.edit().putBoolean(permission, false).apply;

    }

    private ArrayList findUnAskedPermissions(ArrayList wanted){

        ArrayList result = new ArrayList<~>();

        for(String perm : wanted){

            if(!hasPermission(perm) && shouldWeAsk(perm)){

                result.add(perm);

            }

        }

        return result;

    }*/


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



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.campusmap:

                changeScreen(PaceMaps.class);

                break;

            case R.id.calBut:

                changeScreen(CalendarScreen.class);

                break;

            case R.id.next:

                changeScreen(CardTest.class);

                break;

            case R.id.pacemail:

                changeScreen(PMail.class);
//can not logon    TODO:here
                break;

            default:
                break;
        }


    }


    public void changeScreen(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }

}

