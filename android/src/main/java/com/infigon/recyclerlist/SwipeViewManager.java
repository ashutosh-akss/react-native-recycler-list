package com.infigon.recyclerlist;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class SwipeViewManager extends SimpleViewManager<SwipeRefreshView> {

    public static final String REACT_CLASS = "RecyclerView";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected SwipeRefreshView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SwipeRefreshView(reactContext);
    }

    @ReactProp(name = "src")
    public void setData(SwipeRefreshView view, @Nullable ReadableArray sources) {
        Log.d("MYTAG","About to print array list content from js");
        for(int i=0;i<sources.size();i++){
            Log.d("MYTAG",sources.getMap(i).getString("name"));
        }
        view.setData(sources);
    }

    @ReactProp(name = "pullToRefresh", defaultBoolean = false)
    public void pullToRefresh(SwipeRefreshView view, @Nullable Boolean param) {
        Log.d("MYTAG","PULL TO REFRESH VALUE IS : "+param);
        view.setPullToRefresh(param);
    }

    @ReactProp(name = "visibleThreshold", defaultInt = 5)
    public void pullToRefresh(SwipeRefreshView view, @Nullable Integer param) {
        Log.d("MYTAG","VISIBLE THRESHOLD VALUE IS : "+param);
        view.setVisibleThreshold(param);
    }

    @ReactProp(name = "infiniteScroll", defaultBoolean = false)
    public void infiniteScroll(SwipeRefreshView view, @Nullable Boolean param) {
        Log.d("MYTAG","INFINITE SCROLL VALUE IS : "+param);
        view.setInfiniteScroll(param);
    }

    @ReactProp(name = "refreshing", defaultBoolean = false)
    public void setRefreshing(SwipeRefreshView view, @Nullable Boolean param) {
        Log.d("MYTAG","INFINITE SCROLL VALUE IS : "+param);
        view.setRefreshing(param);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return super.getExportedCustomBubblingEventTypeConstants();
    }

    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onClick",
                MapBuilder.of("registrationName", "onClick"),
                "onLongClick",
                MapBuilder.of("registrationName", "onLongClick"),
                "onRefresh",
                MapBuilder.of("registrationName", "onRefresh"),
                "onScrollThreshold",
                MapBuilder.of("registrationName", "onScrollThreshold"),
                "onEndReach",
                MapBuilder.of("registrationName", "onEndReach")
        );

    }
}
