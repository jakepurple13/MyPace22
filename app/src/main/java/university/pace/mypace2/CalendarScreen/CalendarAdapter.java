package university.pace.mypace2.CalendarScreen;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import university.pace.mypace2.R;


/**
 * Created by Jacob on 6/8/16.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    //private ArrayList<CalendarScreen.CalendarInfo> mDataset;
    private List<com.github.sundeepk.compactcalendarview.domain.Event> mDataset;

    CalendarScreen in;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        //public Button addToCalendar;
        public CardView cv;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.calendarTexted);
            //addToCalendar = (Button) v.findViewById(R.id.addCal);
            cv = (CardView) v.findViewById(R.id.card_view1);

        }
    }

    /*// Provide a suitable constructor (depends on the kind of dataset)
    public CalendarAdapter(ArrayList<CalendarScreen.CalendarInfo> myDataset, CalendarScreen in) {
        mDataset = myDataset;
        this.in = in;
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalendarAdapter(List<com.github.sundeepk.compactcalendarview.domain.Event> myDataset, CalendarScreen in) {
        mDataset = myDataset;
        this.in = in;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendartext, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String info = "";
        final Event event = ((CalendarScreen.CalendarInfo) mDataset.get(position).getData()).getEvent();//.getEvent();

        info += event.getSummary() + "\n" +
                "Description: " + event.getDescription() + "\n" +
                "Date: " + event.getStart().getDate() + "\n" + event.getEnd().getDate();

        //TODO: Work here on getting this looking nicer

        //holder.mTextView.setText(mDataset.get(position).toString());
        holder.mTextView.setText(info);

        //holder.addToCalendar.setOnClickListener(new View.OnClickListener() {
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ContentValues events = new ContentValues();
                events.put(CalendarContract.Events.CALENDAR_ID, 1);

                events.put(CalendarContract.Events.TITLE, event.getSummary());
                events.put(CalendarContract.Events.DESCRIPTION, event.getDescription());
                event.setStart(event.getStart());
                events.put(CalendarContract.Events.DTSTART, event.getStart().getDate().getValue() + (1000 * 60 * 60 * 24));
                events.put(CalendarContract.Events.DTEND, event.getEnd().getDate().getValue());
                String timeZone = TimeZone.getDefault().getID();
                events.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);

                Uri baseUri;
                if (Build.VERSION.SDK_INT >= 8) {
                    baseUri = Uri.parse("content://com.android.calendar/events");
                } else {
                    baseUri = Uri.parse("content://calendar/events");
                }

                in.getContentResolver().insert(baseUri, events);

                Toast.makeText(in, "Event Added", Toast.LENGTH_SHORT).show();

                Log.e("Line 128", holder.cv.getDrawingCacheBackgroundColor() + "");
                //Log.e("Line 129", darken(holder.cv.getSolidColor(), 0.5) + "");
                //holder.cv.setBackgroundColor(darken(holder.cv.getSolidColor(), 0.5));

                holder.cv.setBackgroundColor(darker(Color.WHITE, 5));

            }
        });

    }


    public static int darker(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}


