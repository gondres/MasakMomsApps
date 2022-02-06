package com.projectpertama.masakmoms.home;

import com.google.gson.annotations.SerializedName;

public class DataMenu {
    @SerializedName("title")
    private String title;
    @SerializedName("thumb")
    private String image;
    @SerializedName("key")
    private String key;
    @SerializedName("times")
    private String times;
    @SerializedName("portion")
    private String portion;
    @SerializedName("dificulty")
    private String difficulty;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

//    @Override
//    public String toString(){
//        return
//                "DataMenu{" +
//                        "title = '" + title + '\'' +
//                        ",thumb = '" + image + '\'' +
//                        ",key = '" + key + '\'' +
//                        ",times = '" + times + '\'' +
//                        ",portion = '" + portion + '\'' +
//                        ",difficulty = '" + difficulty + '\'' +
//                        "}";
//    }
}

