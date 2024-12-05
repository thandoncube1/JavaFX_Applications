package com.beginner.steamlabs.mediaLibrary;

public class Game extends Media {
    private String features;
    private String developer;
    private String publisher;
    private String platform;

    public Game() {
        super();
        this.setMediaType("game");
        this.features = this.getFeatures();
        this.developer = this.getDeveloper();
        this.platform = this.getPlatform();
        this.publisher = this.getPublisher();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
