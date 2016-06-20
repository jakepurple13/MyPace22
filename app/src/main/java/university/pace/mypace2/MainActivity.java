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
import university.pace.mypace2.PaceMaps.PaceMaps;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button phoneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneButton = (Button) findViewById(R.id.numbers);
        //212-479-7990

        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeScreen(ImportantNumbers.class);
            }
        });

        phoneButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }

                String number = "3107350099";

                Date dec = new Date();
                dec.setMonth(5);

                if (new Date().getMonth() == dec.getMonth()) {
                    number = "7814522080";
                }


                try {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);
                } catch (SecurityException e) {
                    AudioManager am =
                            (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                    am.setStreamVolume(
                            AudioManager.STREAM_MUSIC,
                            am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                            15);

                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.rejectionhotline);

                    mediaPlayer.start();
                    int duration = mediaPlayer.getDuration();
                    int current_position = mediaPlayer.getCurrentPosition();

                }

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

            default:
                break;
        }


    }


    public void changeScreen(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }

}

