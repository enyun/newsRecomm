package utils;

import android.os.Handler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//image of one certain news, include url of img, the width and height



public class NewsFlow{

    static List<News> newsFlow= new ArrayList<News>();


	static String loadTxt(String path) {
		StringBuilder sb = new StringBuilder();
		InputStream is;
		StringBuilder result = new StringBuilder();

		try {
			is = new FileInputStream(path);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
	static Img loadImg(Object obj){
		JSONObject jobj = (JSONObject)obj;
        Long width = null;
        String url = null;
        Long height = new Long(0);
            width = (Long)jobj.get("width");
            url = (String) jobj.get("url");
            height =  (Long)jobj.get("height");

        return new Img(url, width, height);
    }
	
	static List<Img> loadImgs(Object obj){
		List<Img> listImg = new ArrayList<Img>();
		JSONArray array = (JSONArray) obj;
		for (int i=0; i<array.size(); ++i){
            Img img = null;

                img = loadImg(array.get(i));

            listImg.add(img);
		}
        return listImg;

    }


	/*
	String summary;
	List<Img> imgList;
	String docid;
	String url;
	String categoryid;
	String title;
	String commentID;
	String ctime;
	 */
	static News loadOneNews(Object ob){
		JSONObject obj = (JSONObject)ob;
        String summary = null;
        String docid = null;
        String url = null;
        List<Img> imgList = null;
        String categoryid = null;
        String title = null;
        String commentID = null;
        String ctime = null;

            summary = (String)obj.get("summary");
            docid = (String)obj.get("docid");
            url = (String)obj.get("url");
            imgList = loadImgs(obj.get("imgs"));
            categoryid = (String)obj.get("categoryid");
            title = (String)obj.get("title");
            commentID = (String)obj.get("commentID");
            ctime = (String)obj.get("ctime");

        return new News(summary, imgList, docid,  url,  categoryid,  title,  commentID,  ctime);

    }

	static List<News> loadNewsFlow(Object obj){
		JSONArray array = (JSONArray) obj;
		List<News> listNews = new ArrayList<News>();
		for (int i=0; i<array.size(); ++i){
            News news = null;
            news = loadOneNews(array.get(i));
            listNews.add(news);
		}
		return listNews;
	}
		
	static List<News> LoadNewsFlowFromUrl(String url){
		String s = loadTxt("/Users/admin/Documents/workspace/t/src/t/jin");
		System.out.println(s);

        JSONParser parser = new JSONParser();
		Object obj=null;
		try {
			obj = parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray array = (JSONArray) obj;
        newsFlow = loadNewsFlow(array);
		return newsFlow;
	}

    static List<News> LoadNewsFlowFromFile(String path){
        //String s = loadTxt("/Users/admin/Documents/workspace/t/src/t/jin");
        String s = loadTxt(path);
        System.out.println(s);

        JSONParser parser = new JSONParser();
        Object obj=null;
        try {
            obj = parser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray array = (JSONArray) obj;
        newsFlow = loadNewsFlow(array);
        return newsFlow;
    }

    public static List<News> LoadNewsFlowFromString(String s){
        System.out.println("LoadNewsFlowFromString : s = "+ s);

        JSONParser parser = new JSONParser();
        Object obj=null;
        try {
            obj = parser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray array = (JSONArray) obj;
        return loadNewsFlow(array);
    }

	public static void main(String[] args) {

        for(int t=0; t<5; ++t) {
            String s = loadTxt("/Users/admin/work/java_lib/in2.html");
            List<News> listNews = NewsFlow.LoadNewsFlowFromString(s);
            for (int i = 0; i < listNews.size(); ++i) {
                //System.out.println(listNews.get(i));
                News news = listNews.get(i);
                System.out.println("title = " + news.getTitle());
                System.out.println("url = " + news.getUrl());
                System.out.println("ctime = " + news.getCtime());
                System.out.println("getImgCnt = " + news.getImgCnt());

                for (int j = 0; j < news.getImgList().size(); ++j) {
                    System.out.println("pic = " + news.getImgList().get(j).getUrl());
                }
            }
        }
//		System.out.println("hello world");
//
//		String s = loadTxt("/Users/admin/Documents/workspace/t/src/t/jin");
//		System.out.println(s);
//		JSONParser parser = new JSONParser();
//
//		try {
//			Object obj = parser.parse(s);
//			JSONArray array = (JSONArray) obj;
//
//			List<News> listNews = loadNewsFlow(array);
//
//		} catch (ParseException pe) {
//			System.out.println("position: " + pe.getPosition());
//			System.out.println(pe);
//		}

	}
}
