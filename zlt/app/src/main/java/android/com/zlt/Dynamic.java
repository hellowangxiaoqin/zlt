package android.com.zlt;

/**
 * Created by 王玉波 on 2018/5/24.
 */

public class Dynamic {
    private Integer id;
    private String name;
    private String image;
    private String dynamic;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDynamic() {
        return dynamic;
    }
    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }
    @Override
    public String toString() {
        return "Dynamic [id=" + id + ", name=" + name + ", image=" + image + ", dynamic=" + dynamic + "]";
    }


}
