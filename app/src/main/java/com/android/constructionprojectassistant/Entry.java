package com.android.constructionprojectassistant;

/**
 * Created by troyr on 5/6/2018.
 */

public class Entry {
    private String mEntryContent;

    private String mTemp;

    private String mLocation;

    private String mSkyDescription;

    private String mSkyIconText;



    public Entry(String skyDescription, String skyIconText, String temp) {

        mSkyDescription = skyDescription;

        mSkyIconText = skyIconText;

        mTemp = temp;

        mEntryContent = "";

    }

    public void setEntryContent(String entryContent) {

        mEntryContent = entryContent;

    }

    public String getTemp() {

        return mTemp;

    }

    public void setTemp(String temp) {

        mTemp = temp;

    }

    public String getLocation() {

        return mLocation;

    }

    public void setLocation(String location) {

        mLocation = location;

    }

    public String getSkyDescription() {

        return mSkyDescription;

    }

    public void setSkyDescription(String skyDescription) {

        mSkyDescription = skyDescription;

    }

    public String getSkyIconText() {

        return mSkyIconText;

    }

    public void setSkyIconText(String skyIconText) {

        mSkyIconText = skyIconText;

    }

}


