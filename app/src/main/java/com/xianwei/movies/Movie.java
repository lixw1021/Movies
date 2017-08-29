package com.xianwei.movies;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class Movie {
    private String title;
    private String imageUriString;
    private String releaseDate;
    private String poster;
    private String averageVote;
    private String plotSynopsis;

    public Movie(String title, String imageUriString, String releaseDate, String poster, String averageVote, String plotSynopsis) {
        this.title = title;
        this.imageUriString = imageUriString;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.averageVote = averageVote;
        this.plotSynopsis = plotSynopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUriString() {
        return imageUriString;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public String getAverageVote() {
        return averageVote;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUriString(String imageUriString) {
        this.imageUriString = imageUriString;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setAverageVote(String averageVote) {
        this.averageVote = averageVote;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }
}
