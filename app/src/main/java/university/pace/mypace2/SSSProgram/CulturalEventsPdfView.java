package university.pace.mypace2.SSSProgram;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/16/2016.
 */
public class CulturalEventsPdfView extends Activity {

    SSWebView view = new SSWebView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view.webviewUser("http://appsrv.pace.edu/dyson/media/pdf/sss/Student_Support_Services_Application_revised3_18_2016.pdf");
    }


}








