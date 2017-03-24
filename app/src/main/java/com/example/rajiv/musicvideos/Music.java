package com.example.rajiv.musicvideos;

public class Music {

    private String image;
    private String name;
    private String video;
    private String id;

    public Music(String image, String name, String video, String id){

        this.setId(id);
        this.image=image;
        this.name=name;
        this.video=video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
