package com.example.alextagocsfair;

public class Plant {
    private String name;
    private String link;
    private String image_url;
    private String common_names;


    public Plant(String name, String link, String image_url, String common_names) {
        this.name = name;
        this.link = link;
        this.image_url = image_url;
        this.common_names = common_names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCommon_names() {
        return common_names;
    }

    public void setCommon_names(String common_names) {
        this.common_names = common_names;
    }
}
