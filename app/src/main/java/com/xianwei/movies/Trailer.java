package com.xianwei.movies;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class Trailer {
    private String videoUrl;
    private String imageUrl;

    public Trailer(String videoUrl, String imageUrl) {
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
