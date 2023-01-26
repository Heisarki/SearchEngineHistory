package com.example.searchengine;

public class HistoryResult {
    private String keyword;
    private String time_stamp;

    public HistoryResult(String keyword, String time_stamp) {
        this.keyword = keyword;
        this.time_stamp = time_stamp;
    }

    public String getKeyword(){
        return this.keyword;
    }
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }
    public String getTimestamp() {
        return  this.time_stamp;
    }
    public void setTimestamp(String time_stamp){
        this.time_stamp = time_stamp;
    }
}
