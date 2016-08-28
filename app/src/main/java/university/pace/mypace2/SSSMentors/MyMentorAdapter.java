package university.pace.mypace2.SSSMentors;

/**
 * Created by Jacob on 8/22/16.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;

import university.pace.mypace2.Constants;
import university.pace.mypace2.ImportantNumbersScreen.ImportantNumbers;
import university.pace.mypace2.R;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;

import university.pace.mypace2.R;

/**
 * Created by Jacob on 6/8/16.
 */
public class MyMentorAdapter extends RecyclerView.Adapter<MyMentorAdapter.ViewHolder> implements RecyclerViewFastScroller.BubbleTextGetter {
    private ArrayList<MentorActivity.MentorInfo> mDataset;

    MentorActivity in;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView iv;


        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            iv = (ImageView) v.findViewById(R.id.mentor_help);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyMentorAdapter(ArrayList<MentorActivity.MentorInfo> myDataset, MentorActivity in) {
        mDataset = myDataset;
        this.in = in;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyMentorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mentors_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).toString());

        View.OnClickListener von = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mDataset.get(position).email});
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                String emailText = "Hello " + mDataset.get(position).name + " ,";
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    in.startActivity(Intent.createChooser(emailIntent,
                            "Email Mentor using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Log.wtf("WTF", ex);
                }
            }
        };

        holder.mTextView.setOnClickListener(von);
        holder.iv.setOnClickListener(von);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= mDataset.size())
            return null;

        String name = mDataset.get(pos).name;
        if (name == null || name.length() < 1)
            return null;

        return mDataset.get(pos).name.substring(0, 1);
    }

}


