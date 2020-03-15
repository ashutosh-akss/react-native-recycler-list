package com.infigon.recyclerlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerListView extends RecyclerView {
    private ReactContext reactContext;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public RecyclerListView(@NonNull ReactContext context) {
        super(context);
        reactContext = context;
        this.setLayoutManager(new LinearLayoutManager(context));

        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = recyclerView.getLayoutManager().getItemCount();
                firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached
                    Log.d("MYTAG", "end called");
                    loading = true;
                }
            }
        });
    }

    public void setData(ReadableArray adapterData) {
        this.setAdapter(new MyAdapter(adapterData));
    }

    public void setVisibleThreshold(Integer value){
        this.visibleThreshold = value;
    }

    public void sendEvent(View view, String eventName, WritableMap map) {
        SwipeRefreshView swipeRefreshView = (SwipeRefreshView) this.getParent();
        swipeRefreshView.sendEvent(view,eventName,map);
    }
}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    static ArrayList mDataset;
    static RecyclerListView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = (RecyclerListView) recyclerView;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        // each data item is just a string in this case
        private TextView txtname;
        private TextView txtDesc;

        public MyViewHolder(CardView v) {
            super(v);
            txtname = v.findViewById(R.id.txtName);
            txtDesc = v.findViewById(R.id.txtDesc);
            // Set Event Listeners
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d("MYTAG", "Click received " + getAdapterPosition() + " view id : " + view);
            WritableMap map = Arguments.createMap();
            map.putInt("position", getAdapterPosition());
            mRecyclerView.sendEvent(view,"onClick",map);
        }

        @Override
        public boolean onLongClick(View view) {
            Log.d("MYTAG", "Long Click received " + getAdapterPosition() + " view id : " + view.getId());
            WritableMap map = Arguments.createMap();
            map.putInt("position", getAdapterPosition());
            mRecyclerView.sendEvent(view,"onLongClick",map);
            return false;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ReadableArray myDataset) {
        mDataset = myDataset.toArrayList();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        CardView layout = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup,
                false);
        MyViewHolder vh = new MyViewHolder(layout);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HashMap<String, String> rowData = (HashMap<String, String>) mDataset.get(position);
        holder.txtname.setText(rowData.get("name"));
        holder.txtDesc.setText(rowData.get("email"));
    }

    public void refresh(ReadableArray dataset) {
        mDataset.clear();
        mDataset = dataset.toArrayList();
        notifyDataSetChanged();
    }

    public void addMore(ReadableArray dataset) {
        mDataset.addAll(dataset.toArrayList());
        notifyDataSetChanged();
    }

    public void clear() {
        mDataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}