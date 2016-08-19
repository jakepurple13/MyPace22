package university.pace.mypace2.SSSProgram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

import university.pace.mypace2.Constants;
import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/16/2016.
 */
public class CulturalEventsPdfView extends Activity implements View.OnClickListener {
    FloatingActionButton fab1, fab2, fab3;
    SSWebView view = new SSWebView();
    SSSprogram market = new SSSprogram();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.culturalevents_webview);

        WebView viewfrag = (WebView) findViewById(R.id.SSSwebPagecul);
        WebSettings webSettings = viewfrag.getSettings();
        webSettings.setJavaScriptEnabled(true);
        viewfrag.loadUrl(Constants.SSSPAGE + "#eli"); //links to sss page cultural events #cul

        /**email john button****/
        fab1 = (FloatingActionButton) findViewById(R.id.johnfab);
        RippleAnimate(fab1);
        /**email john button****/


        /**email joyce button****/
        fab2 = (FloatingActionButton) findViewById(R.id.joycefab);
        RippleAnimate(fab2);

        /**email joyce button****/

        fab3 = (FloatingActionButton) findViewById(R.id.pdf_press);
        RippleAnimate(fab3);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

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
                break;

            case R.id.pdf_press:
                File file = new File(String.valueOf(getResources().openRawResource(R.raw.academics))); //TODO: Change for cultural events

                if (file.exists()) {
                    Uri path = Uri.fromFile(file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this,
                                "No Application Available to View PDF",
                                Toast.LENGTH_SHORT).show();
                    }
                    Log.d("Pressed Pdf", "Viewing pdf?");
                    break;


                }
            default:


                break;

        }

    }


    public void RippleAnimate(FloatingActionButton butt) {
        ColorStateList ripple = ContextCompat.getColorStateList(this, R.drawable.fab_ripple_color);
        butt.setBackgroundTintList(ripple);


    }

}








