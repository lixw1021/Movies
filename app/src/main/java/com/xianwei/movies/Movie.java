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

    public Movie(String title, String posterUriString, String backgroundUriString, String releaseDate, String averageVote, String plotSynopsis, String id) {
        this.title = title;
        this.posterUriString = posterUriString;
        this.backgroundUriString = backgroundUriString;
        this.releaseDate = releaseDate;
        this.averageVote = averageVote;
        this.plotSynopsis = plotSynopsis;
        this.id = id;
    }

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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.posterUriString);
        dest.writeString(this.backgroundUriString);
        dest.writeString(this.releaseDate);
        dest.writeString(this.averageVote);
        dest.writeString(this.plotSynopsis);
        dest.writeString(this.id);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.posterUriString = in.readString();
        this.backgroundUriString = in.readString();
        this.releaseDate = in.readString();
        this.averageVote = in.readString();
        this.plotSynopsis = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
