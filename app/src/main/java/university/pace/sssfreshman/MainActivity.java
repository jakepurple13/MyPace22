package university.pace.sssfreshman;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.Date;


import university.pace.sssfreshman.CalendarScreen.CalendarScreen;
import university.pace.sssfreshman.Courses.Courses;
import university.pace.sssfreshman.EventChecker.EventChecker;
import university.pace.sssfreshman.GoogleAnalytics.AnalyticsApplication;
import university.pace.sssfreshman.ImportantNumbersScreen.ImportantNumbers;

import university.pace.sssfreshman.PaceMaps.PaceMaps;
import university.pace.sssfreshman.SSSMentors.MentorActivity;
import university.pace.sssfreshman.SSSProgram.SSSprogram;
import university.pace.sssfreshman.SSSTutors.TutorActivity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton phoneButton, MapButton;
    AudioManager am;
    private RelativeLayout bg2;
    private Tracker mTracker;
    private final String TAG = "MainActivity";
    private String Screentracker = "StartScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TestFairy.begin(this, "bde19002b059bcf18e75d5029b2862d582033bf4");
/**Start Tracking users onCreate Screen
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Log.i(TAG, TAG + Screentracker);
        mTracker.setScreenName(Screentracker);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
 ***/
        /**Start Tracking users onCreate Screen***/


        /**Ask User for Location Premisson and Accounts**/
        AskPremission();

        MapButton = (ImageButton) findViewById(R.id.campusmap);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        /***Important Numbers****/
        phoneButton = (ImageButton) findViewById(R.id.numbers);



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


        MapButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
/**Track who finds the Easter Egg**/
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("I'm the Map Easter Egg")
                        .setAction("User Held the Maps button")
                        .build());
                Log.i(TAG, "Held Map button");
                /**Track who finds the Easter Egg**/


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
                    @Override
                    public void onCompletion(MediaPlayer mp) {
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

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.campusmap:
                /**Tracks**/
                Tracks("Pressed Maps button", "Viewing Maps");
                Log.i(TAG, "Pressed Map button");
                /**Tracks**/
                changeScreen(PaceMaps.class, v);


                break;

            case R.id.calBut:
                /**Tracks**/
                Tracks("Pressed Calendar button", "Viewing Calendar");
                Log.i(TAG, "Pressed Calendar button");
                /**Tracks**/
                changeScreen(CalendarScreen.class, v);

                break;

            case R.id.numbers:

                /**Tracks**/
                Tracks("Pressed Important numbers button", "Viewing numbers");
                /**Tracks**/
                changeScreen(ImportantNumbers.class, v);


                break;

            case R.id.tutoringbutt:
                /**Tracks**/
                Tracks("Pressed Tutor button", "Viewing Tutors");
                Log.i(TAG, "Pressed Tutors button");
                /**Tracks**/
                changeScreen(TutorActivity.class, v);
                //You Took to long Jacob...
                break;

            case R.id.pacemail:
                /**Tracks**/
                Tracks("Pressed E-mail button", "Viewing E-mail");
                Log.i(TAG, "Pressed E-mail button");
                /**Tracks**/
                Fade(v);
                NoDeceptiveads("com.microsoft.exchange.mowa", this, "com.microsoft.exchange.mowa");
                // TakeUserToMarket(this, "com.microsoft.exchange.mowa");

                break;
            case R.id.blackboard:

                /**Tracks**/
                Tracks("Pressed Blackboard button", "Viewing Blackboard content");
                Log.i(TAG, "Pressed Blackboard  button");

                Fade(v);
                NoDeceptiveads("com.blackboard.android", this, "com.blackboard.android");
                /**Tracks
                 AlertDialog ad = new AlertDialog.Builder(this).setMessage(
                 R.string.UserConsent).setTitle(
                 R.string.Tothemarketplace).setCancelable(false)
                 .setPositiveButton(android.R.string.ok,
                 new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog,
                 int whichButton) {
                 // Bring user to the market
                 TakeUserToMarket(MainActivity.this, "com.blackboard.android");


                 }
                 }).setNeutralButton(android.R.string.cancel,
                 new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog,
                 int whichButton) {
                 Toast.makeText(MainActivity.this, "This function will not be available with out th", Toast.LENGTH_LONG).show();  // User selects Cancel, discard all changes
                 }
                 }).show();
                 **/


                break;

            case R.id.sss:

                /**Tracks**/
                Tracks("Pressed SSS button", "Viewing SSS content");
                /**Tracks**/
                changeScreen(SSSprogram.class, v);


                break;

            /**reads in from .xl file  8/10/16**/
            case R.id.courses:
                /**Tracks**/
                Tracks("Pressed Courses button", "Viewing Courses content");
                Log.i(TAG, "Pressed Courses button");
                /**Tracks**/
                changeScreen(Courses.class, v);



                break;

            case R.id.mentorButton:
                /**Tracks**/
                Tracks("Pressed Mentor button", "Viewing Mentors");
                Log.i(TAG, "Pressed Mentors button");
                /**Tracks**/
                changeScreen(MentorActivity.class, v);


                break;
            case R.id.Eventbadgebutt:
                /**Tracks**/
                Tracks("Pressed Eventbadge button", "Viewing Badges");
                Log.i(TAG, "Pressed Eventbadge button");
                /**Tracks**/
                changeScreen(EventChecker.class, v);


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


    public void Tracks(String catogory, String action) {
        /**    mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(catogory)
                .setAction(action)
                .build());
         **/
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

    public void changeScreen(Class cl, View v) {
        Fade(v);
        Intent intent = new Intent(this, cl);
        startActivity(intent);
        //this.finish();
    }

    public void NoDeceptiveads(final String DownloadApp, Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            AlertDialog ad = new AlertDialog.Builder(this).setIcon(R.drawable.play_store).setMessage(
                R.string.UserConsent).setTitle(
                R.string.Tothemarketplace).setCancelable(false)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // Bring user to the market

                                TakeUserToMarket(MainActivity.this, DownloadApp);

                            }
                        }).setNeutralButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                Toast.makeText(MainActivity.this, "This function will not be available with out this app", Toast.LENGTH_LONG).show();  // User selects Cancel, discard all changes
                            }
                        }).show();
        } else
            TakeUserToMarket(MainActivity.this, DownloadApp);


    }




    public void Fade(View v) {
        Animation Anim = AnimationUtils.loadAnimation(this, R.anim.alpha_fade);
        v.startAnimation(Anim);

    }
}
