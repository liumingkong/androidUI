package com.mico.model.po;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table STICKER_DATA.
 */
public class StickerData {

    private String stickerId;
    /** Not-null value. */
    private String content;
    private long ctime;
    private String ext;

    public StickerData() {
    }

    public StickerData(String stickerId) {
        this.stickerId = stickerId;
    }

    public StickerData(String stickerId, String content, long ctime, String ext) {
        this.stickerId = stickerId;
        this.content = content;
        this.ctime = ctime;
        this.ext = ext;
    }

    public String getStickerId() {
        return stickerId;
    }

    public void setStickerId(String stickerId) {
        this.stickerId = stickerId;
    }

    /** Not-null value. */
    public String getContent() {
        return content;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setContent(String content) {
        this.content = content;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}