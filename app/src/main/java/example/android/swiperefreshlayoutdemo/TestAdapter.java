package example.android.swiperefreshlayoutdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import utils.News;
import utils.ValleyTool;

/**
 * Created by admin on 3/15/15.
 */
public class TestAdapter  extends BaseAdapter {

    Context context;
    List<News> listData;

    public TestAdapter(Context context, List<News> listData){
        this.context = context;
        this.listData = listData;
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.testlayout, null);
        }

        if (listData == null)
            return convertView;

        News news = listData.get(position);
        System.out.println("get news in adapter ="+news);
        System.out.println("get news in adapter title="+news.getTitle());


        NetworkImageView niv = (NetworkImageView) convertView.findViewById(R.id.pic);
        if (news.getImgList()==null || news.getImgList().size() > 0) {
            ValleyTool.setNetWorkImageView(context, niv, news.getImgList().get(0).getUrl());
        }else{
            ValleyTool.setNetWorkImageView(context, niv, "https://www.baidu.com/img/bdlogo.png");
        }

        // set title
        if (news.getTitle()!=null) {
            TextView textView = (TextView) convertView.findViewById(R.id.title);
            System.out.println("get titleTextView = "+textView);

            textView.setText(news.getTitle());
        }

        // set summary
        if (news.getSummary()!=null) {
            TextView summaryTextView = (TextView) convertView.findViewById(R.id.summary);
            System.out.println("get summaryTextView = "+summaryTextView);
            System.out.println("get summary = "+news.getSummary());

            String sum = news.getSummary();
            summaryTextView.setText(sum.substring(0, Math.min(15,sum.length()-1))+"...");
        }

        // set url
        if (news.getUrl()!=null) {
            TextView urlTextView = (TextView) convertView.findViewById(R.id.url);
            urlTextView.setText("SinaBigEye");
        }
        return convertView;
    }
}
