package android.com.zlt;

import java.io.Serializable;

/**
 * Created by user on 2018/5/30.
 */

public class Place implements Serializable{
    private String name;//景区名称
    private String address;//景区地址
    private String detail;//景区介绍
    private String  image;//景区图片
    public Place(){
        super();
    }
    public Place(String name,String address,String detail,String image){
        super();
        this.name=name;
        this.address=address;
        this.detail=detail;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
