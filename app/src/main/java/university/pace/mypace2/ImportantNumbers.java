package university.pace.mypace2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportantNumbers extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_numbers);

        ArrayList<ImportantInfo> al = new ArrayList<>();

        InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.important_numbers));

        BufferedReader br = new BufferedReader(is);

        String line = " ";
        String number = " ";
        while(line!=null) {
            try {
                line = br.readLine();
                number = br.readLine();

                if(line==null) {
                    break;
                }

                ImportantInfo ii = new ImportantInfo(line, number);
                al.add(ii);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("asdfkjh", line + "\t" + number);

        }



        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyNumberAdapter(al);
        mRecyclerView.setAdapter(mAdapter);



    }







    public class ImportantInfo {
        String name;
        String number;

        public ImportantInfo(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return name + number;
        }

    }
}
