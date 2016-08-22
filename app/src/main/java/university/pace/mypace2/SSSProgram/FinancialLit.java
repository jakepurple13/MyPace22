package university.pace.mypace2.SSSProgram;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.InputStreamReader;

import university.pace.mypace2.Constants;
import university.pace.mypace2.GoogleAnalytics.AnalyticsApplication;
import university.pace.mypace2.R;

/**
 * Created by Mrgds on 8/18/2016.
 */
public class FinancialLit extends AppCompatActivity {
    private ImageView imageView;
    private int currentPage = 0;
    private Button next, previous;
    private Tracker mTracker;
    private final String TAG = "FinancialLit";
    private String Screentracker = "Financial Literacy Screen";
    private ParcelFileDescriptor file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financial_screen);

        String linkstring = Constants.SSSPAGE + "#academicservices";


/**Start Tracking users onCreate Screen***/
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Log.i(TAG, TAG + Screentracker);
        mTracker.setScreenName(Screentracker);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        /**Start Tracking users onCreate Screen    ***/

    }

}
