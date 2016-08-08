package university.pace.mypace2;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


import java.util.Date;


import university.pace.mypace2.CalendarScreen.CalendarScreen;
import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
;
import university.pace.mypace2.PaceMaps.PaceMaps;
import university.pace.mypace2.SSSProgram.SSSprogram;
import university.pace.mypace2.TestingPackage.CardTest;
//import university.pace.mypace2.TestingPackage.ChatRoomActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton phoneButton, MapButton;
    AudioManager am;
    private RelativeLayout bg2;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**Ask User for Location Premisson and Accounts**/
        AskPremission();

        // [START shared_app_measurement]
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // [END shared_app_measurement]

        MapButton = (ImageButton) findViewById(R.id.campusmap);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        /***Important Numbers****/////////////
        phoneButton = (ImageButton) findViewById(R.id.numbers);

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
        /***Important Numbers****/////////////


        //TODO: button grey out on press testing

/**
 MapButton.setOnTouchListener(new View.OnTouchListener() {

@Override public boolean onTouch(View view, MotionEvent event) {
if (event.getAction() == MotionEvent.ACTION_UP) {
MapButton.setBackgroundResource(R.drawable.map_onpress);
changeScreen(PaceMaps.class);

Log.d("on touch", "darken");
} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
MapButton.setBackgroundResource(R.drawable.map_onpress);
}
return false;
}

});

 MapButton.setOnLongClickListener(new View.OnLongClickListener() {
@Override public boolean onLongClick(View v) {

Date dec = new Date();
dec.setMonth(11);

if (new Date().getMonth() == dec.getMonth()) {

}

final int normalSound = am.getStreamVolume(AudioManager.STREAM_MUSIC);

am.setStreamVolume(
AudioManager.STREAM_MUSIC,
am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
15);

MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.imthemap);

mediaPlayer.start();
int duration = mediaPlayer.getDuration();
int current_position = mediaPlayer.getCurrentPosition();

mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
@Override public void onCompletion(MediaPlayer mp) {
am.setStreamVolume(
AudioManager.STREAM_MUSIC,
am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
normalSound);
}
});

Toast.makeText(MainActivity.this, "Achievement Unlocked: If there's a place you gotta go", Toast.LENGTH_LONG).show();

return false;
}
});
 **/


    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else
                    Toast.makeText(this, "For full app functions these premission are needed", Toast.LENGTH_LONG).show();
                break;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            /*switch (id) {
                case R.id.bg1:
                    Log.d("Bg select", "Bloodcity");
                    RelativeLayout bg = (RelativeLayout) findViewById(R.id.mainpage);
                    bg.setBackground(getResources().getDrawable(R.drawable.blood_city_low, getResources().newTheme()));

                    break;

                case R.id.bg2:
                    Log.d("Bg select", "city that never sleeps");
                    bg2 = (RelativeLayout) findViewById(R.id.mainpage);
                    bg2.setBackground(getResources().getDrawable(R.drawable.city_that_never_sleep2, getResources().newTheme()));

                    SaveSuggestion(R.drawable.city_that_never_sleep2);

                    break;
                case R.id.bg3:
                    Log.d("Bg select", "Big Apple");
                    RelativeLayout bg3 = (RelativeLayout) findViewById(R.id.mainpage);
                    bg3.setBackground(getResources().getDrawable(R.drawable.thebigapple_low, getResources().newTheme()));

                    break;
                case R.id.bg4:
                    Log.d("Bg select", "dusk");
                    RelativeLayout bg4 = (RelativeLayout) findViewById(R.id.mainpage);
                    bg4.setBackground(getResources().getDrawable(R.drawable.dusk, getResources().newTheme()));

                    break;
                case R.id.defaultbg:
                    Log.d("Bg select", "default");
                    RelativeLayout defaultbg = (RelativeLayout) findViewById(R.id.mainpage);
                    defaultbg.setBackground(getResources().getDrawable(R.drawable.mainbg3, getResources().newTheme()));

                    break;


            }
            return true;*/

        } else
            Toast.makeText(MainActivity.this, "Sorry your Phone can't handle this feature", Toast.LENGTH_LONG).show();


       /* if (id == R.id.MapTypeChange) {
            *//**   changeType();    Change to satellite  **//*
            return true;
        }*/


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.campusmap:
                Fade(v);
                changeScreen(PaceMaps.class);

                break;

            case R.id.calBut:
                Fade(v);
                changeScreen(CalendarScreen.class);

                break;

            case R.id.tutoringbutt:
                Fade(v);
                changeScreen(CardTest.class);

                break;

            case R.id.pacemail:
                Fade(v);
                TakeUserToMarket(this, "com.microsoft.exchange.mowa");

                break;
            case R.id.blackboard:
                Fade(v);
                TakeUserToMarket(this, "com.blackboard.android");


                break;


            case R.id.sss:
                Fade(v);
                changeScreen(SSSprogram.class);


                break;

            case R.id.test:
                Fade(v);
                askaboutapp();
                break;

            default:
                break;
        }


    }

    public void AskPremission() {

        String[] perms = {"android.permission.READ_CALENDAR", "android.permission.Write_CALENDAR",
                "android.permission.GET_ACCOUNTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

        int permsRequestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.GET_ACCOUNTS
            ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_CALENDAR
            ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_CALENDAR
            ) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(this, "Location permission is need to show the campus closet to you", Toast.LENGTH_SHORT).show();
                } else {
/**If the app does have their Permission  dont ask again**/
                    requestPermissions(perms, permsRequestCode);
                }
            }

        }
    }


    public void TakeUserToMarket(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            //download the app
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void changeScreen(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
        //this.finish();
    }

    private void SaveSuggestion(final int bg) {

        AlertDialog ad = new AlertDialog.Builder(this).setMessage(
                R.string.rating_exit_message).setTitle(
                R.string.rating_exit_title).setCancelable(false)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                SharedPreferences sharepref = getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharepref.edit();
                                editor.putInt("save background", bg);
                                editor.apply(); // User selects OK, save changes to db

                            }
                        }).setNeutralButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Toast.makeText(MainActivity.this, "Background not saved", Toast.LENGTH_LONG).show();  // User selects Cancel, discard all changes
                            }
                        }).show();

    }

    private void askaboutapp() {

        AlertDialog ad = new AlertDialog.Builder(this).setMessage(
                R.string.UserExperience).setTitle(
                R.string.more).setCancelable(false)
                .setPositiveButton(R.string.useful,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                // [START custom_event]
                                Bundle params = new Bundle();
                                params.putString("image_name", getResources().getString(R.string.useful));
                                mFirebaseAnalytics.logEvent("Good experience", params);
                                // [END custom_event]


                            }
                        }).setNeutralButton(R.string.bad,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Toast.makeText(MainActivity.this, "We are sorry to hear that", Toast.LENGTH_LONG).show();  // User selects Cancel, discard all changes
                            }
                        }).show();


    }


    public void Fade(View v) {
        Animation Anim = AnimationUtils.loadAnimation(this, R.anim.alpha_fade);
        v.startAnimation(Anim);

    }
}
