package university.pace.sssfreshman.SSSProgram;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import university.pace.sssfreshman.Constants;
import university.pace.sssfreshman.GoogleAnalytics.AnalyticsApplication;
import university.pace.sssfreshman.MainActivity;
import university.pace.sssfreshman.R;

/**
 * Created by Mrgds on 8/5/2016.
 */
public class SSSprogram extends AppCompatActivity implements View.OnClickListener {
    MainActivity market;
    Button academic, cultural, financial;
    FloatingActionButton fab2, fab4;
    ImageButton JohnContact, JoyceContact;
    private Tracker mTracker;
    private final String TAG = "SSSprogram";
    private String Screentracker = "SSS Screen";
    String newString;
    SSWebView wv;
    AlertDialog ad;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);


        wv = new SSWebView();
/**Start Tracking users onCreate Screen***/
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Log.i(TAG, TAG + Screentracker);
        mTracker.setScreenName(Screentracker);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        /**Start Tracking users onCreate Screen***/

        // create shortcut if requested
        Intent.ShortcutIconResource icon =
                Intent.ShortcutIconResource.fromContext(this, R.drawable.sssbutton);

        Intent intent = new Intent();
        Intent launchIntent = new Intent(this, SSSprogram.class);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "SSS Support");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        setResult(RESULT_OK, intent);
        // create shortcut if requested end


        market = new MainActivity();
        academic = (Button) findViewById(R.id.academicbutt);
        cultural = (Button) findViewById(R.id.culturalbutt);
        financial = (Button) findViewById(R.id.Finanbutt);

        /**email john button****/
        JohnContact = (ImageButton) findViewById(R.id.john_email);
        /**email john button****/


        /**email joyce button****/
        JoyceContact = (ImageButton) findViewById(R.id.joyce_email);

        /**email joyce button****/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.academicbutt:
                /**Tracks**/
                Tracks("Viewing Academic services content", "Pressed Academic services button");
                Log.i(TAG, "Pressed Academic services button");
                /**Tracks**/
                LoadOutsidePdf(Constants.PDF_ACADEMICS);
                // PutExtra(Constants.PDF_ACADEMICS, v);
                break;

            case R.id.culturalbutt:
                /**Tracks**/
                Tracks("Viewing Cultural events content", "Pressed Cultural events button");
                Log.i(TAG, "Pressed Cultural events button");
                /**Tracks**/
                PutExtra(Constants.SSSPAGE, v);
                break;

            case R.id.Finanbutt:
                /**Tracks**/
                Tracks("Viewing Financial content", "Pressed Financial events button");
                /**Tracks**/
                Log.i(TAG, "Pressed Financial events button");
                LoadOutsidePdf(Constants.PDF_FINANCIL);
                //PutExtra(Constants.PDF_FINANCIL, v);
                break;

            case R.id.john_email:
                MethodOfContact(Constants.JOHNPHONE, Constants.JOHNEMAIL, "Hello Mr.Hooker,");
                /**Tracks**/
                Tracks("Contacting John Hooker", "Pressed email John button");
                /**Tracks**/
                Log.i(TAG, "Pressed email John button");
                break;


            case R.id.joyce_email:
                MethodOfContact(Constants.JOYCEPHONE, Constants.JOYCEEMAIL, "Hello Ms.Lau,");
                /**Tracks**/
                Tracks("Contacting Joyce Lau", "Pressed email Joyce button");
                /**Tracks**/
                break;

        }


    }

    public void PutExtra(String put, View v) {
        Intent ii = new Intent(this, SSWebView.class);
        ii.putExtra("tag", put);
        Anim(v);
        startActivity(ii);
    }


    public void LoadOutsidePdf(String pdf_url) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
        startActivity(browserIntent);
    }

    public void Anim(View button) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        button.startAnimation(shake);

    }

    public void changeScreen(Class cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
        //this.finish();
    }

    public void MethodOfContact(final String number, final String Email, final String Greeting) {

        ad = new AlertDialog.Builder(this).setIcon(R.drawable.contact).setTitle(
                R.string.methodofcontact).setMessage(
                R.string.calloremail).setCancelable(false)
                .setPositiveButton(R.string.Email,
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
                                    Toast.makeText(SSSprogram.this,
                                            "No email clients installed.",
                                            Toast.LENGTH_SHORT).show();
                                    market.TakeUserToMarket(SSSprogram.this, "com.microsoft.exchange.mowa");
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
                        }).setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ad.cancel();

                                // cancel
                            }
                        }).show();



    }


    public void Tracks(String catogory, String action) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(catogory)
                .setAction(action)
                .build());

    }



}
