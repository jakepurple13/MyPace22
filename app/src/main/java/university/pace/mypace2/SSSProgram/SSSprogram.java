package university.pace.mypace2.SSSProgram;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
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

import university.pace.mypace2.Constants;
import university.pace.mypace2.GoogleAnalytics.AnalyticsApplication;
import university.pace.mypace2.MainActivity;
import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/5/2016.
 */
public class SSSprogram extends AppCompatActivity implements View.OnClickListener {
    MainActivity market;
    Button academic;
    FloatingActionButton fab2, fab4;
    ImageButton JohnContact, JoyceContact;
    private Tracker mTracker;
    private final String TAG = "SSSprogram";
    private String Screentracker = "SSS Screen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

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
                Anim(v);
                changeScreen(SSWebView.class);
                break;

            case R.id.culturalbutt:
                /**Tracks**/
                Tracks("Viewing Cultural events content", "Pressed Cultural events button");
                Log.i(TAG, "Pressed Cultural events button");
                /**Tracks**/
                Anim(v);
                //    changeScreen(CulturalEventsPdfView.class);
                break;

            case R.id.Finanbutt:
                /**Tracks**/
                Tracks("Viewing Financial content", "Pressed Financial events button");
                /**Tracks**/
                Log.i(TAG, "Pressed Financial events button");

                Anim(v);
                changeScreen(FinancialLit.class);
                break;

            case R.id.john_email:

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
                    market.TakeUserToMarket(this, "com.microsoft.exchange.mowa");
                }

                /**Tracks**/
                Tracks("Contacting John Hooker", "Pressed email John button");
                /**Tracks**/
                Log.i(TAG, "Pressed email John button");


                break;


            case R.id.joyce_email:
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
                    market.TakeUserToMarket(this, "com.microsoft.exchange.mowa");
                }
                /**Tracks**/
                Tracks("Contacting Joyce Lau", "Pressed email Joyce button");
                /**Tracks**/

                break;




        }


    }


    private void inflatewebview() {
        SupportFragment frag = new SupportFragment();
        FragmentManager manager = getFragmentManager();
        //swap the fragment
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.supportpace, frag, "Pace SSS WebPage");
        transaction.commit();
        Toast.makeText(this, getResources().getText(R.string.On_More_Academic_Pressed), Toast.LENGTH_SHORT).show();
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

    public void Tracks(String catogory, String action) {
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(catogory)
                .setAction(action)
                .build());

    }



}
