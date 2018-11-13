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
     * Author name of the news
     */
    private String mAuthorName;

    /**
     * Publication date of the news
     */
    private String mPublicationDate;

    /**
     * URL of the news
     */
    private String mUrl;

    /**
     * Image of the news
     */
    private String mImageNews;

    /**
     * @param title       is the title of the news
     * @param overview    is the overview of the news
     * @param publicationDate is the publication date of the news
     * @param authorName is the author of the news
     * @param imageNews is the image of the news
     * @param url is the url of the news
     */
    public News(String title, String authorName, String imageNews, String overview, String publicationDate, String url) {
        mTitle = title;
        mAuthorName = authorName;
        mImageNews = imageNews;
        mOverview = overview;
        mPublicationDate = publicationDate;
        mUrl = url;
    }

    /**
     * Get the title of the news
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the image of the news
     */
    public String getImageNews() {
        return mImageNews;
    }

    /**
     * Get the author of the news
     */
    public String getAuthorName() {
        return mAuthorName;
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
    public String getPublicationDate() {
        return mPublicationDate;
    }

    /**
     * Get the Url of the news
     */
    public String getUrl() {
        return mUrl;
    }

}