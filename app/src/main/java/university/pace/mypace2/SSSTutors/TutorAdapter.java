package university.pace.mypace2.SSSTutors;

/**
 * Created by Mrgds on 8/28/2016.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import university.pace.mypace2.R;


public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.ViewHolder> {

    private ArrayList<TutorActivity.TutorInfo> mDataset;

    TutorActivity incoming;


    /**
     * Adapter constructer
     **/
    public TutorAdapter(ArrayList<TutorActivity.TutorInfo> data, TutorActivity info) {
        mDataset = data;
        this.incoming = info;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView iv;

        /**
         * constructor for View holder
         **/
        public ViewHolder(View V) {
            super(V);
            textView = (TextView) V.findViewById(R.id.textView);
            iv = (ImageView) V.findViewById(R.id.tutor_help);

        }
    }
    // Create new views (invoked by the layout manager)

    @Override
    public TutorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {   // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder


        // create a new view Inflates the view where info is going to be stored
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tutors_textview, parent, false);
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
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mDataset.get(position).email});
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "From an SSS app user");
                String emailText = "Hello " + mDataset.get(position).name + "," + "I would like help in " + mDataset.get(position).major;
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
                /**Checks to see if user has email app/ if not takes to market to download**/
                try {
                    incoming.startActivity(Intent.createChooser(emailIntent,
                            "Email Tutor via..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Log.wtf("WTF", ex);
                }
            }
        };
        //set on click for action to happen
        holder.textView.setOnClickListener(action); //sets action to contents bubble
        holder.iv.setOnClickListener(action); //sets action to picture
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}