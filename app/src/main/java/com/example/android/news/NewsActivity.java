package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    /**
     * URL for the news data from theguardian.com dataset
     */
    private static final String THE_GUARDIAN_DB_URL =
            "https://content.guardianapis.com/search?&api-key=f3f378a5-d90f-4c1d-ad21-77c4f50bc08c&show-fields=thumbnail&show-tags=contributor";

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really one comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    /**
     * Image that is displayed when the list is empty or there is not internet connection
     */
    private ImageView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Create a new adapter that takes the list of the news as input.
        ListView newsListView = findViewById(R.id.listview_news);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Find the current news that was clicked on
                News currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of the network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the loaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setImageResource(R.drawable.no_internet_connection);
        }
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String Search = sharedPrefs.getString(getString(R.string.settings_search_key), getString(R.string.settings_Search_default));
        String orderBy = sharedPrefs.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));
        String section = sharedPrefs.getString(getString(R.string.settings_section_key), getString(R.string.settings_section_default));

        //parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(THE_GUARDIAN_DB_URL);

        //buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        //Append query parameter and its value
        if (!Search.equals("")) {
            uriBuilder.appendQueryParameter("q", Search);
            orderBy = getString(R.string.settings_order_by_relevance_value);
        }
                if (Search.equals("") && orderBy.equals(getString(R.string.settings_order_by_relevance_value))) {
            orderBy = getString(R.string.settings_order_by_newest_value);
        }
                if (!section.equals("")) {
            if (!section.equals(getString(R.string.settings_section_default_value))) {
                uriBuilder.appendQueryParameter("section", section);
            }
        }

        uriBuilder.appendQueryParameter("order-by", orderBy);

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No News Found."
        mEmptyStateTextView.setImageResource(R.drawable.error_image);

        // Clear the adapter of previous news data
        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
