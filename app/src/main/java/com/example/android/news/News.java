package com.example.android.news;

public class News {

    /**
     * Title of the news
     */
    private String mTitle;

    /**
     * Overview of the news
     */
    private String mOverview;

    /**
     * Release date of the news
     */
    private String mReleaseDate;

    /**
     * URL of the news
     */
    private String mUrl;

    /**
     * @param title       is the title of the news
     * @param overview    is the overview of the news
     * @param releaseDate is the release date of the news
     */
    public News(String title, String overview, String releaseDate) {
        mTitle = title;
        mOverview = overview;
        mReleaseDate = releaseDate;
    }

    /**
     * Get the title of the news
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the overview of the news
     */
    public String getOverview() {
        return mOverview;
    }

    /**
     * Get the Release Date of the news
     */
    public String getReleaseDate() {
        return mReleaseDate;
    }

}