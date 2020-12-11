package com.actors;

public class ChannelResponse {
    String title;
    String description;
    String country;
    String viewCount;
    String subscriberCount;

    public ChannelResponse(String title, String description, String country, String viewCount, String subscriberCount){
        this.title = title;
        this.description = description;
        this.country = country;
        this.viewCount = viewCount;
        this.subscriberCount = subscriberCount;
    }

    public String getTitle() {
        return title;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getDescription() {
        return description;
    }

    public String getSubscriberCount() {
        return subscriberCount;
    }

    public String getCountry() {
        return country;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubscriberCount(String subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
}
