package utils;

/**
 * Created by admin on 3/13/15.
 */
public class Img{

    String url;
    Long height;
    Long width;

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public String toString(){
        String ret = "@Img url="+url+" width="+width+" height="+height;
        System.out.println(ret);
        return ret;
    }

    public Img(String url, Long width, Long height){
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

};