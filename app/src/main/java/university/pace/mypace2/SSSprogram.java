package university.pace.mypace2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Mrgds on 8/5/2016.
 */
public class SSSprogram extends AppCompatActivity implements View.OnClickListener {

    Button academic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.support);

        academic = (Button) findViewById(R.id.academicbutt);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.academicbutt:

                inflatewebview();
                Animation Anim = AnimationUtils.loadAnimation(this, R.anim.alpha_fade);
                v.startAnimation(Anim);
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
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.On_More_Academic_Pressed), Toast.LENGTH_SHORT).show();
    }


}
