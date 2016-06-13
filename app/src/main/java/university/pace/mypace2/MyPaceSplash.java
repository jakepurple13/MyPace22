package university.pace.mypace2;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyPaceSplash extends AppCompatActivity {


        /** Duration of wait **/
        private final int SPLASH_DISPLAY_LENGTH = 1000;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle Mypace2Begin) {
            super.onCreate( Mypace2Begin);

            setContentView(R.layout.my_pace_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(MyPaceSplash.this, MainActivity.class); //Takes to Main Need2Feed Screen
                    MyPaceSplash.this.startActivity(mainIntent);
                    MyPaceSplash.this.finish();
                  overridePendingTransition(R.anim.mainfadein,R.anim.mainfadeout);
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

}
