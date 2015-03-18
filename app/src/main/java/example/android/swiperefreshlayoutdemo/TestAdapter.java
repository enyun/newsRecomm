package example.android.swiperefreshlayoutdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

import utils.News;
import utils.ValleyTool;

/**
 * Created by admin on 3/15/15.
 */
public class TestAdapter  extends BaseAdapter {

    static final int TYPE_1 = 0;
    static final int TYPE_2 = 1;
    static final int TYPE_3 = 2;  // no image
    static final int VIEW_TYPE_NUM = TYPE_3+1;
    Context context;
    List<News> listData;
    LayoutInflater inflater;


    public TestAdapter(Context context, List<News> listData){
        this.context = context;
        this.listData = listData;
        inflater = LayoutInflater.from(context);

    }

    public int getViewTypeCount() {
        return VIEW_TYPE_NUM;
    }

    public int getItemViewType(int position) {

        int imgCnt = listData.get(position).getImgCnt();
        if (imgCnt == 3)
            return TYPE_2;
        if (imgCnt == 0)
            return TYPE_3;
        return TYPE_1;
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

        Log.d("getView","begining>>>>>>>>>");
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        viewHolder3 holder3 = null;

        int type = getItemViewType(position);
        Log.d("getView","viewtype="+type);

        News news = listData.get(position);
        Log.d("getView", "get news in adater view" + news);

        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.testlayout, null);
                    holder1 = new viewHolder1();
                    holder1.networkImageView = (NetworkImageView) convertView.findViewById(R.id.pic);
                    holder1.title = (TextView) convertView.findViewById(R.id.title);
                    holder1.summary = (TextView) convertView.findViewById(R.id.summary);
                    holder1.url = (TextView) convertView.findViewById(R.id.url);
                    convertView.setTag(holder1);
                    break;

                case TYPE_2:
                    convertView = inflater.inflate(R.layout.testlayout2, null);
                    holder2 = new viewHolder2();
                    holder2.title = (TextView) convertView.findViewById(R.id.title);
                    holder2.pic1 = (NetworkImageView) convertView.findViewById(R.id.pic1);
                    holder2.pic2 = (NetworkImageView) convertView.findViewById(R.id.pic2);
                    holder2.pic3 = (NetworkImageView) convertView.findViewById(R.id.pic3);
                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.testlayout1, null);
                    holder3 = new viewHolder3();
                    holder3.title = (TextView) convertView.findViewById(R.id.title);
                    holder3.summary = (TextView) convertView.findViewById(R.id.summary);
                    holder3.url = (TextView) convertView.findViewById(R.id.url);
                    convertView.setTag(holder3);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (viewHolder1) convertView.getTag();
                    Log.e("convertView !!!!!!= ", "NULL TYPE_1");
                    break;
                case TYPE_2:
                    holder2 = (viewHolder2) convertView.getTag();
                    Log.e("convertView !!!!!!= ", "NULL TYPE_2");
                    break;
                case TYPE_3:
                    holder3 = (viewHolder3) convertView.getTag();
                    Log.e("convertView !!!!!!= ", "NULL TYPE_3");
                    break;
            }
        }

        switch (type) {
            case TYPE_1:
                NetworkImageView niv = holder1.networkImageView;
                if (news.getImgList() == null || news.getImgList().size() > 0) {
                    ValleyTool.setNetWorkImageView(context, niv, news.getImgList().get(0).getUrl());
                } else {
                    ValleyTool.setNetWorkImageView(context, niv, "https://www.baidu.com/img/bdlogo.png");
                }

                // set title
                if (news.getTitle() != null) {
                    TextView textView = holder1.title;
                    System.out.println("get titleTextView = " + textView);
                    textView.setText(news.getTitle());
                }

                // set summary
                if (news.getSummary() != null) {
                    TextView summaryTextView = holder1.summary;
                    System.out.println("get summaryTextView = " + summaryTextView);
                    System.out.println("get summary = " + news.getSummary());

                    String sum = news.getSummary();
                    summaryTextView.setText(sum.substring(0, Math.min(15, sum.length() - 1)) + "...");
                }

                // set url
                if (news.getUrl() != null) {
                    TextView urlTextView = holder1.url;
                    urlTextView.setText(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").
                                    format(new java.util.Date(Integer.parseInt(news.getCtime())
                                            * 1000)));

                }

                break;
            case TYPE_2:
//                    if (true)   break;
                // set title
                if (news.getTitle() != null) {
                    holder2.title.setText(news.getTitle());
                }
                if (news.getImgCnt()!=3)
                    break;
                if (news.getImgList()!=null && news.getImgList().get(0)!=null) {
                    ValleyTool.setNetWorkImageView(context, holder2.pic1, news.getImgList().get(0).getUrl());
                }
                if (news.getImgList()!=null && news.getImgList().get(1)!=null) {
                    ValleyTool.setNetWorkImageView(context, holder2.pic2, news.getImgList().get(1).getUrl());
                }
                if (news.getImgList()!=null && news.getImgList().get(2)!=null) {
                    ValleyTool.setNetWorkImageView(context, holder2.pic3, news.getImgList().get(2).getUrl());
                }
                break;

            case TYPE_3:
                // set title
                if (news.getTitle() != null) {
                    holder3.title.setText(news.getTitle());
                }

                // set summary
                if (news.getSummary() != null) {
                    String sum = news.getSummary();
                    holder3.summary.setText(sum.substring(0, Math.min(15, sum.length() - 1)) + "...");
                }

                // set url
                if (news.getUrl() != null) {
                    holder3.url.setText(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").
                            format(new java.util.Date(Integer.parseInt(news.getCtime())
                                    * 1000)));

                }
                break;
        }

        Log.d("getView","end<<<<<<<<<");
        return convertView;
    }


    class viewHolder1{
        NetworkImageView networkImageView;
        TextView title;
        TextView summary;
        TextView url;
    }

    class viewHolder2{
        TextView title;
        NetworkImageView pic1;
        NetworkImageView pic2;
        NetworkImageView pic3;
    }

    class viewHolder3{
        TextView title;
        TextView summary;
        TextView url;
    }
}
