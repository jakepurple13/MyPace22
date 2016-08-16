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


import university.pace.mypace2.CalendarScreen.CalendarScreen;
import university.pace.mypace2.Courses.CourseDisplay;
import university.pace.mypace2.Courses.Courses;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**Ask User for Location Premisson and Accounts**/
        AskPremission();










        MapButton = (ImageButton) findViewById(R.id.campusmap);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        /***Important Numbers****/
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

            /**reads in from .xl file  8/10/16**/
            case R.id.courses:
                Fade(v);
                changeScreen(CourseDisplay.class);
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
