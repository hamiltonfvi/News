package com.example.android.news;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Activity context, ArrayList<News> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        // Get the object located at this position in the list
        News currentNews = getItem(position);

        // Find the ImageView with the view ID for the Thumbnail Image
        ImageView imageView = listItemView.findViewById(R.id.image_news_view);
        // Get the version name from the current object
        Picasso.get().load(currentNews.getImageNews()).into(imageView);

        // Find the TextView with the view ID for the Title
        TextView titleView = listItemView.findViewById(R.id.title_view);
        // Get the version name from the current object
        titleView.setText(currentNews.getTitle());

        // Find the TextView with the view ID for the Category of the news
        TextView categoryView = listItemView.findViewById(R.id.section_name_view);
        // Get the version name from the current object
        categoryView.setText(currentNews.getSectionName());

        // Find the TextView with the view ID for the Author
        TextView authorView = listItemView.findViewById(R.id.author_view);
        // Get the version name from the current object
        if (currentNews.getAuthorName() != null) {
            authorView.setText(currentNews.getAuthorName());

            //Set author name view as visible
            authorView.setVisibility(View.VISIBLE);
        } else {
            //Set author name view as gone
            authorView.setVisibility(View.GONE);
        }

        // Find the TextView with the view ID for the Publication Date of the news
        // Find the TextView with view ID date
        TextView dateView = null;
        TextView timeView = null;
        if (currentNews.getPublicationDate() != null) {
            dateView = listItemView.findViewById(R.id.publication_date_view);
            // Format the date string (i.e. "Mar 3, 1984")
            String formattedDate = formatDate(currentNews.getPublicationDate()).concat(",");
            // Display the date of the current news in that TextView
            dateView.setText(formattedDate);

            // Find the TextView with view ID time
            timeView = listItemView.findViewById(R.id.publication_time_view);
            // Format the time string (i.e. "4:30PM")
            String formattedTime = formatTime(currentNews.getPublicationDate());
            // Display the time of the current news in that TextView
            timeView.setText(formattedTime);

            //Set date & time views as visible
            dateView.setVisibility(View.VISIBLE);
            timeView.setVisibility(View.VISIBLE);
        } else {
            //Set date & time views as gone
            dateView.setVisibility(View.GONE);
            timeView.setVisibility(View.GONE);
        }

        // Return the list item view tht is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}