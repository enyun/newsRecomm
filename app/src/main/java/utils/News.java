package utils;

import java.util.List;

/**
 * Created by admin on 3/13/15.
 */
public class News {
    String summary;
    List<Img> imgList;
    String docid;
    String url;
    String categoryid;
    String title;
    String commentID;
    String ctime;

    public News(String summary, List<Img> imgList, String docid,
                String url, String categoryid,
                String title, String commentID, String ctime){
        this.summary = summary;
        this.imgList = imgList;
        this.docid = docid;
        this.url = url;
        this.categoryid = categoryid;
        this.title = title;
        this.commentID = commentID;
        this.ctime = ctime;
    }

    public int getImgCnt(){
        return imgList.size();
//        int num = 0;
//        for (int i=0; i<imgList.size(); ++i){
//            Img img = imgList.get(i);
//            if (img.getWidth() > 2 && img.getHeight() > 2)
//                num+=1;
//        }
//        return num;
    }

    public String toString(){
        String ret = "@Cake: docid="+docid+" url="+url+" title="+title;
        String imgStr = "";
        for(int i=0; i<imgList.size(); ++i){
            imgStr += imgList.get(i).toString();
        }
        ret = ret+" imgList="+imgStr;
        System.out.println(ret);
        return ret;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public List<Img> getImgList() {
        return imgList;
    }
    public void setImgList(List<Img> imgList) {
        this.imgList = imgList;
    }
    public String getDocid() {
        return docid;
    }
    public void setDocid(String docid) {
        this.docid = docid;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCommentID() {
        return commentID;
    }
    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
    public String getCtime() {
        return ctime;
    }
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
};
