package example.android.swiperefreshlayoutdemo;

/**
 * Created by admin on 3/15/15.
 */
public class TestData {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestData(){}
    public TestData(String s){
        name = s;
    }
    public TestData(int i){
        name=""+i;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String name;
    public int age;
    public String picUrl;
}
