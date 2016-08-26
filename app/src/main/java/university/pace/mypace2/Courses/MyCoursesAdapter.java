package university.pace.mypace2.Courses;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import university.pace.mypace2.Constants;
import university.pace.mypace2.R;


public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.ViewHolder> {

    private ArrayList<Courses.Course> mDataset;

    Courses incoming;


    /**
     * Adapter constructer
     **/
    public MyCoursesAdapter(ArrayList<Courses.Course> data, Courses info) {
        mDataset = data;
        this.incoming = info;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        /**
         * constructor for View holder
         **/
        public ViewHolder(View V) {
            super(V);
            textView = (TextView) V.findViewById(R.id.textView);


        }
    }
    // Create new views (invoked by the layout manager)

    @Override
    public MyCoursesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {   // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder


        // create a new view Inflates the view where info is going to be stored
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_textview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        //create a new viewholder
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        /**Action can be set to the bubble that the user's press**/
        holder.textView.setText(mDataset.get(position).toString());

//Now what happens in the buddle view
        //set an Action

        View.OnClickListener action = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constants.JOHNEMAIL}); //TODO:Default to john
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                String emailText = "I would like to take " + mDataset.get(position).title + "." + "\n" + "CRN: " + mDataset.get(position).course_number;
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    incoming.startActivity(Intent.createChooser(emailIntent,
                            "Send Course to Jonathon Hooker..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Log.wtf("WTF", ex);
                }
            }
        };
        //set on click for action to happen
        holder.textView.setOnClickListener(action);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}