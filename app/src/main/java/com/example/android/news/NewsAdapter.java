package com.example.android.news;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

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
        final News currentNews = getItem(position);

        // Find the TextView with the view ID for the Title
        TextView titleView = listItemView.findViewById(R.id.title_view);

        // Get the version name from the current object
        titleView.setText(currentNews.getTitle());

        // Find the TextView with the view ID for the Overview of the news
        TextView overviewView = listItemView.findViewById(R.id.overview_view);

        // Get the version name from the current object
        overviewView.setText(currentNews.getOverview());

        // Find the TextView with the view ID for the Release Date of the news
        TextView releaseDateView = listItemView.findViewById(R.id.release_date_view);

        // Get the version name from the current object
        releaseDateView.setText(currentNews.getReleaseDate());

        // Return the list item view tht is now showing the appropriate data
        return listItemView;
    }
}