package com.example.searchengine;

import java.sql.Timestamp;

public class SearchResult {
    private String link;
    private String linkname;
    private Timestamp time;
    SearchResult(String link, String linkname, Timestamp time){
        this.link = link;
        this.linkname = linkname;
        this.time = time;
    }
    public void setLink(String link) {this.link = link;}
    public void setLinkname(String linkname) {this.linkname = linkname;}
    public void setTime(Timestamp time) {this.time = time;}
    public String getLink() {return this.link;}
    public String getLinkname() {return this.linkname;}
    public Timestamp getTime() {return this.time;}
}
