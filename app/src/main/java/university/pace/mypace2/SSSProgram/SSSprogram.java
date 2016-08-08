package university.pace.mypace2.SSSProgram;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import university.pace.mypace2.Constants;
import university.pace.mypace2.MainActivity;
import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/5/2016.
 */
public class SSSprogram extends AppCompatActivity implements View.OnClickListener {
    MainActivity market;
    Button academic;
    FloatingActionButton fab, fab2, fab3, fab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

        market = new MainActivity();
        academic = (Button) findViewById(R.id.academicbutt);
        /**email john button****/
        fab = (FloatingActionButton) findViewById(R.id.john_email);
        RippleAnim(fab);
        /**email john button****/

        /**email maria button****/
        fab2 = (FloatingActionButton) findViewById(R.id.maria_email);
        RippleAnim(fab2);
        /**email maria button****/

        /**email joyce button****/
        fab3 = (FloatingActionButton) findViewById(R.id.joyce_email);
        RippleAnim(fab3);
        /**email joyce button****/

        /**email norma button****/
        fab4 = (FloatingActionButton) findViewById(R.id.norma_email);
        RippleAnim(fab4);
        /**email norma button****/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.academicbutt:

                Animation Anim = AnimationUtils.loadAnimation(this, R.anim.alpha_fade);
                v.startAnimation(Anim);

                Intent i = new Intent(this, SSWebView.class);
                startActivity(i);

                //inflatewebview(); //invokes Fragment view

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


                break;

            case R.id.maria_email:
                Intent emailI = new Intent(android.content.Intent.ACTION_SEND);
                emailI.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constants.MARIAEMAIL});
                emailI.setType("plain/text");
                emailI.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                emailI.putExtra(Intent.EXTRA_TEXT, "Hello Mrs.Iacullo-Bird,");
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    startActivity(Intent.createChooser(emailI,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this,
                            "No email clients installed.",
                            Toast.LENGTH_SHORT).show();
                    market.TakeUserToMarket(this, "com.microsoft.exchange.mowa");
                }
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


    public void RippleAnim(FloatingActionButton button) {
        ColorStateList ripple = ContextCompat.getColorStateList(SSSprogram.this, R.drawable.ripple_colour);
        button.setBackgroundTintList(ripple);

    }

}
