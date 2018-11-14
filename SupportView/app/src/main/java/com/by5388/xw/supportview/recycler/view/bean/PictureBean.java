package com.by5388.xw.supportview.recycler.view.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author by5388  on 2018/11/13.
 * generate by GsonFormat
 */
@SuppressWarnings("all")
public class PictureBean {
    /**
     * id : 5be14edb9d21223dd50660f8
     * createdAt : 2018-11-06T08:20:43.656Z
     * desc : 2018-11-06
     * publishedAt : 2018-11-06T00:00:00.0Z
     * source : web
     * type : 福利
     * url : https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg
     * used : true
     * who : lijinshanmx
     */
    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
