package com.beginner.steamlabs.mediaLibrary;

public class Movie extends Media {
    private String[] actors;
    private String[] production;
    private String duration;

    public Movie() {
        super();
        this.setMediaType("movie");
        this.actors = this.getActors();
        this.production = this.getProduction();
        this.duration = this.getDuration();
    }

    public String[] getProduction() {
        return production;
    }

    public void setProduction(String[] production) {
        this.production = production;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
