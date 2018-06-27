package android.com.zlt;

import java.io.Serializable;

/**
 * Created by user on 2018/6/6.
 */

public class TouTiao implements Serializable{
    private Integer newsid;
    //新闻图片url
    private String newsImages;
    //新闻标题
    private String title;
    //新闻内容
    private String content;
    //标记
    private String tag;

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public String getNewsImages() {
        return newsImages;
    }

    public void setNewsImages(String newsImages) {
        this.newsImages = newsImages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
