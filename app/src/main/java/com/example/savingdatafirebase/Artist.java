package com.example.savingdatafirebase;
public class Artist {
    String heroID;
    String heroName;
    String heroComic;
    public Artist(){

    }
    public Artist(String artistID, String heroName, String heroComic){
        this.heroID = heroID;
        this.heroName = heroName;
        this.heroComic = heroComic;
    }

    public String getheroID() {
        return heroID;
    }

    public String getheroName() {
        return heroName;
    }

    public String getheroComic() {
        return heroComic;
    }
}
