package com.xianwei.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class Movie implements Parcelable {

    private String title;
    private String posterUriString;
    private String backgroundUriString;
    private String releaseDate;
    private String averageVote;
    private String plotSynopsis;
    private String id;

    public Movie() {
    }

    public Movie(String title, String posterUriString, String backgroundUriString, String releaseDate, String averageVote, String plotSynopsis, String id) {
        this.title = title;
        this.posterUriString = posterUriString;
        this.backgroundUriString = backgroundUriString;
        this.releaseDate = releaseDate;
        this.averageVote = averageVote;
        this.plotSynopsis = plotSynopsis;
        this.id = id;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        posterUriString = in.readString();
        backgroundUriString = in.readString();
        releaseDate = in.readString();
        averageVote = in.readString();
        plotSynopsis = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterUriString);
        dest.writeString(backgroundUriString);
        dest.writeString(releaseDate);
        dest.writeString(averageVote);
        dest.writeString(plotSynopsis);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPosterUriString() {
        return posterUriString;
    }

    public String getBackgroundUriString() {
        return backgroundUriString;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getAverageVote() {
        return averageVote;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterUriString(String posterUriString) {
        this.posterUriString = posterUriString;
    }

    public void setBackgroundUriString(String backgroundUriString) {
        this.backgroundUriString = backgroundUriString;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAverageVote(String averageVote) {
        this.averageVote = averageVote;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public void setId(String id) {
        this.id = id;
    }
}

