package com.showcase.photoshell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Photos {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAlbumId : " + getAlbumId() + "\n");
        sb.append("\tID : " + getId() + "\n");
        sb.append("\tTitle : " + getTitle());

        return sb.toString();
    }
}
