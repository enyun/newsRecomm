package utils;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ValleyTool {
    final static LruCache<String, Bitmap> lruCache=new LruCache<String, Bitmap>(20);
    final static ImageCache imageCache= new ImageCache() {
        @Override
        public void putBitmap(String key, Bitmap value) {
            lruCache.put(key, value);
        }
        @Override
        public Bitmap getBitmap(String key) {
            return lruCache.get(key);
        }
    };

    public static void setNetWorkImageView(Context context, NetworkImageView iv, String imageUrl){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        iv.setTag("url");
        iv.setImageUrl(imageUrl, imageLoader);
    }

}
