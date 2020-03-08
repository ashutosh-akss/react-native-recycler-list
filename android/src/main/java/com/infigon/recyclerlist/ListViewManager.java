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

public class ListViewManager extends SimpleViewManager<RecyclerListView> {

    public static final String REACT_CLASS = "RecyclerList";

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected RecyclerListView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new RecyclerListView(reactContext );
    }

    @ReactProp(name = "src")
    public void setData(RecyclerListView view, @Nullable ReadableArray sources) {
        Log.d("MYTAG","About to print array list content from js");
        for(int i=0;i<sources.size();i++){
            Log.d("MYTAG",sources.getMap(i).getString("name"));
        }
        view.setData(sources);
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
                "onEndReach",
                MapBuilder.of("registrationName", "onEndReach")
                );

    }
}
