package university.pace.sssfreshman.SSSProgram;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import university.pace.sssfreshman.Constants;
import university.pace.sssfreshman.GoogleAnalytics.AnalyticsApplication;
import university.pace.sssfreshman.R;

public class SSWebView extends AppCompatActivity implements View.OnClickListener {
    String newString;
    String linkstring = Constants.SSSPAGE;
    private Tracker mTracker;
    private final String TAG = "SSSWebView";
    private String Screentracker = "SSS Webpage";
    FloatingActionButton Johnfab, Joycefab;
    ToggleButton plusmore;
    AlertDialog ad;

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
        final Bundle b = i.getExtras();
        final WebView viewfrag = (WebView) findViewById(R.id.webView);
        viewfrag.getSettings().setJavaScriptEnabled(true);
        viewfrag.loadUrl((String) b.get("tag"));

        Johnfab = (FloatingActionButton) findViewById(R.id.johnfab);
        Ripple(Johnfab); //ripple animation

        Joycefab = (FloatingActionButton) findViewById(R.id.joycefab);
        Ripple(Joycefab);//ripple animation


        Johnfab.setVisibility(View.INVISIBLE);
        Joycefab.setVisibility(View.INVISIBLE);


        /**toggle more options******/
        plusmore = (ToggleButton) findViewById(R.id.plusemore);

        plusmore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Tracks("More Options", "More Options==>Email");

                if (isChecked) {
                    Johnfab.setVisibility(View.VISIBLE);
                    Joycefab.setVisibility(View.VISIBLE);
                    plusmore.setBackgroundResource(R.drawable.minussign);
                    Log.d("Minussign==>", "showing");

                    // The toggle show options

                } else {
                    Johnfab.setVisibility(View.INVISIBLE);
                    Joycefab.setVisibility(View.INVISIBLE);

                    plusmore.setBackgroundResource(R.drawable.plussign);
                    Log.d("Plussign==>", "showing ");
                    // The toggle show no options

                }
            }
        });

        /*** Toggle For more options    *******************************************************/

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())


        {
            case R.id.johnfab:
                MethodOfContact(Constants.JOHNPHONE, Constants.JOHNEMAIL, "Hello Mr.Hooker,");
                /**Tracks**/
                Tracks("Contacting John Hooker", "Pressed email John button");
                /**Tracks**/
                Log.i(TAG, "Pressed email John button");


                break;


            case R.id.joycefab:
                MethodOfContact(Constants.JOYCEPHONE, Constants.JOYCEEMAIL, "Hello Ms.Lau,");
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

    public void MethodOfContact(final String number, final String Email, final String Greeting) {

        ad = new AlertDialog.Builder(this).setIcon(R.drawable.ic_cast_dark).setTitle( //TODO:Change to contact
                R.string.methodofcontact).setMessage(
                R.string.calloremail).setCancelable(false)
                .setNegativeButton(R.string.Email,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // user emails SSS Employer

                                Intent emailj = new Intent(android.content.Intent.ACTION_SEND);
                                emailj.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Email});
                                emailj.setType("plain/text");
                                emailj.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                                emailj.putExtra(Intent.EXTRA_TEXT, Greeting);
                                /**Checks to see if user has email app/ if not takes to market to download**/
                                try {
                                    startActivity(Intent.createChooser(emailj,
                                            "Send email using..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(SSWebView.this,
                                            "No email clients installed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNeutralButton(R.string.Call,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {


                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + number));
                                startActivity(intent);

                                ;  // User Calls SSS Employer
                            }
                        }).setPositiveButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ad.cancel();

                                // cancel
                            }
                        }).show();



    }


}


