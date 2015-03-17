package example.android.swiperefreshlayoutdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import utils.HttpUtils;
import utils.Img;
import utils.News;
import utils.NewsFlow;


public class TestActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    public static String DEMOURL = "http://10.210.228.69:8010/content_recomm?duid=102232001&apprid=4&nr=2";

    public static List<News> listData = new ArrayList<News>();

    private ListView listView;
    private TestAdapter testAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Handler getNewsHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);

            //parser news from given json string
            List<News> listNews = NewsFlow.LoadNewsFlowFromString(jsonData);

            //add new news gotten from url
            listData.addAll(listNews);

            //notify it's changed
            testAdapter.notifyDataSetChanged();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_test);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        listView = (ListView) findViewById(R.id.lv);
        testAdapter = new TestAdapter(this, listData);

        listView.setAdapter(testAdapter);
        listView.setOnItemClickListener(this);

        // get data from url and update the listview
        HttpUtils.getNewsJSON(DEMOURL, getNewsHandler);
    }

    @Override
    public void onRefresh() {
        System.out.println("onRefresh is called  start");

        HttpUtils.getNewsJSON(DEMOURL, getNewsHandler);
        swipeRefreshLayout.setRefreshing(false);

        System.out.println("onRefresh is called  end");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = listData.get(position);
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra("content_url", news.getUrl());
        startActivity(intent);
    }
}