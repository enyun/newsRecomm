package example.android.swiperefreshlayoutdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import utils.*;

/**
 * Created by admin on 3/13/15.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView url = (TextView) convertView.findViewById(R.id.url);
        TextView summary = (TextView) convertView.findViewById(R.id.summary);
//        ImageView ctime = (ImageView) convertView.findViewById(R.id.ctime);

        News news = newsList.get(position);
        title.setText(news.getTitle());
        url.setText(news.getUrl());
        summary.setText(news.getSummary());

//        String pic_url = news.getImgList().get(0);
//        HttpUtils.setPicBitmap(ivPic, pic_url);

        return convertView;
    }

}


