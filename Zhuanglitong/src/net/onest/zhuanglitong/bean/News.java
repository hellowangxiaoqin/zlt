package net.onest.zhuanglitong.bean;

public class News {
	private Integer newsid;
	 //����ͼƬurl
    private String newsImages;
    //���ű���
    private String title;
    //��������
    private String content;
    //���
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	
	
    
}
