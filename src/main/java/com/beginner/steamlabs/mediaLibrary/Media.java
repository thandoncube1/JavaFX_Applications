package com.beginner.steamlabs.mediaLibrary;

import java.util.Date;

public class Media {
    private String mediaType;
    private String mediaTitle;
    private Genre[] genres;
    private Date initialReleaseDate;
    private String[] images;
    private String ageRating;
    private String language;
    private String country;
    private String description;

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public Date getInitialReleaseDate() {
        return initialReleaseDate;
    }

    public void setInitialReleaseDate(Date initialReleaseDate) {
        this.initialReleaseDate = initialReleaseDate;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.mediaTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
