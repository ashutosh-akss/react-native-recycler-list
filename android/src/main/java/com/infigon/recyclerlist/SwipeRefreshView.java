package com.infigon.recyclerlist;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class SwipeRefreshView extends SwipeRefreshLayout {
    private ReactContext reactContext;
    private RecyclerListView recyclerListView;
    private Boolean infiniteScroll;

    public SwipeRefreshView(@NonNull ReactContext context) {
        super(context);
        reactContext = context;
        recyclerListView = new RecyclerListView(context);
        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                Log.d("MYTAG", "On Refresh of SwipeView Called");
                WritableMap map = Arguments.createMap();
                map.putBoolean("refreshing", true);
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onRefresh", map);
            }
        });
        this.addView(recyclerListView,new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setData(ReadableArray adapterData) {
        recyclerListView.setData(adapterData);
    }

    public  void setVisibleThreshold(Integer value){
        recyclerListView.setVisibleThreshold(value);
    }

    public  void setPullToRefresh(Boolean value){
        this.setEnabled(value);
    }

    public  void setInfiniteScroll(Boolean value){
        this.infiniteScroll = value;
    }

    public void setRefreshing(Boolean value){
        this.setRefreshing(value);
    }

    public void sendEvent(String eventName, WritableMap map) {
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), eventName, map);
    }
}
