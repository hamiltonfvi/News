package com.example.android.news;

import java.util.Date;

public class News {

    /**
     * Title of the news
     */
    private String mTitle;

    /**
     * Category of the news
     */
    private String mSectionName;

    /**
     * Author name of the news
     */
    private String mAuthorName;

    /**
     * Publication date of the news
     */
    private Date mPublicationDate;

    /**
     * URL of the news
     */
    private String mUrl;

    /**
     * Image of the news
     */
    private String mImage;

    /**
     * @param title           is the title of the news
     * @param sectionName        is the category of the news
     * @param publicationDate is the publication date of the news
     * @param authorName      is the author of the news
     * @param imageNews       is the image of the news
     * @param url             is the url of the news
     */
    public News(String title, String authorName, String imageNews, String sectionName, Date publicationDate, String url) {
        mTitle = title;
        mAuthorName = authorName;
        mImage = imageNews;
        mSectionName = sectionName;
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
        return mImage;
    }

    /**
     * Get the author of the news
     */
    public String getAuthorName() {
        return mAuthorName;
    }

    /**
     * Get the Category of the news
     */
    public String getSectionName() {
        return mSectionName;
    }

    /**
     * Get the Publication Date of the news
     */
    public Date getPublicationDate() {
        return mPublicationDate;
    }

    /**
     * Get the Url of the news
     */
    public String getUrl() {
        return mUrl;
    }
}