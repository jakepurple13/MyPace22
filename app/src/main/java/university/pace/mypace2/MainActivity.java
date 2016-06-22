package university.pace.mypace2;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;


import java.util.Date;

import university.pace.mypace2.CalendarScreen.CalendarScreen;
import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
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


                return false;
            }
        });


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

            case R.id.nextbutton2:

//                changeScreen(Buildings.class);

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

