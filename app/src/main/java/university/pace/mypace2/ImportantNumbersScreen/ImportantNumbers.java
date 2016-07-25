package university.pace.mypace2.ImportantNumbersScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import university.pace.mypace2.R;

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


        Collections.sort(al, new InfoCompare());


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyNumberAdapter(al, this);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerViewFastScroller fastScroller = (RecyclerViewFastScroller) findViewById(R.id.fast_scroller);


        fastScroller.setRecyclerView(mRecyclerView);

        ArrayList<AlphabetItem> mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets = new ArrayList<>();
        for (int i = 0; i < al.size(); i++) {
            String name = al.get(i).name;
            if (name == null || name.trim().isEmpty())
                continue;

            String word = name.substring(0, 1);
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));
            }
        }

        fastScroller.setUpAlphabet(mAlphabetItems);


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
            return name + "\nTelephone: " + number;
        }

    }

    public class InfoCompare implements Comparator<ImportantInfo> {
        public int compare(ImportantInfo e1, ImportantInfo e2) {
            return e1.name.compareTo(e2.name);
        }
    }
}
