package university.pace.mypace2.SSSProgram;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import university.pace.mypace2.Constants;
import university.pace.mypace2.GoogleAnalytics.AnalyticsApplication;
import university.pace.mypace2.R;

public class SSWebView extends AppCompatActivity implements View.OnClickListener {
    String newString;
    String linkstring = Constants.SSSPAGE;
    private Tracker mTracker;
    private final String TAG = "SSSWebView";
    private String Screentracker = "SSS Webpage";
    FloatingActionButton Johnfab, Joycefab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssweb_view);
        /**Start Tracking users onCreate Screen***/
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Log.i(TAG, TAG + Screentracker);
        mTracker.setScreenName(Screentracker);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        /**Start Tracking users onCreate Screen***/

        Intent i = getIntent();
        Bundle b = i.getExtras();
        WebView viewfrag = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl((String) b.get("tag"));
        Johnfab = (FloatingActionButton) findViewById(R.id.johnfab);
        Ripple(Johnfab); //ripple animation

        Joycefab = (FloatingActionButton) findViewById(R.id.joycefab);
        Ripple(Joycefab);//ripple animation


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())


        {
            case R.id.johnfab:

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constants.JOHNEMAIL});
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Mr.Hooker,");
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this,
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }

                /**Tracks**/
                Tracks("Contacting John Hooker", "Pressed email John button");
                /**Tracks**/
                Log.i(TAG, "Pressed email John button");


                break;


            case R.id.joycefab:

                Intent emailj = new Intent(android.content.Intent.ACTION_SEND);
                emailj.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constants.JOYCEEMAIL});
                emailj.setType("plain/text");
                emailj.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                emailj.putExtra(Intent.EXTRA_TEXT, "Hello Ms.Lau,");
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    startActivity(Intent.createChooser(emailj,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this,
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                }
                /**Tracks**/
                Tracks("Contacting Joyce Lau", "Pressed email Joyce button");
                /**Tracks**/
                break;
        }
    }

    public void Tracks(String catogory, String action) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(catogory)
                .setAction(action)
                .build());


    }

    public void Ripple(FloatingActionButton fab) {
        ColorStateList ripple = ContextCompat.getColorStateList(this, R.drawable.fab_ripple_color);
        fab.setBackgroundTintList(ripple);


    }

}


