package com.yan.pullrefreshlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 2017/3/28.
 */
public abstract class SWRecyclerAdapter<T> extends RecyclerView.Adapter<SWViewHolder> {

    private Context context;

    protected Context getContext() {
        return context;
    }

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    private LayoutInflater inflater;


    public SWRecyclerAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = (list == null ? new ArrayList<T>() : list);
        inflater = LayoutInflater.from(context);
    }

    public void addList(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void recyleList(List<T> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public SWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final SWViewHolder holder = new SWViewHolder(
                inflater.inflate(getItemLayoutId(viewType), parent, false));
        holder.setAutoUtils(new SWViewHolder.AutoUtils() {
            @Override
            public void callBackItmeView(View view) {
                setAutoUtilsView(view);
            }
        });
        return holder;
    }

    protected abstract void setAutoUtilsView(View view);

    public void onBindViewHolder(SWViewHolder holder, int position) {
        bindData(holder, position, list.get(position));
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(SWViewHolder holder, int position, T item);

    public int getItemCount() {
        return list.size();
    }

    public void add(int positon, T item) {
        list.add(positon, item);
        notifyItemInserted(positon);
    }

    public void delete(int positon) {
        list.remove(positon);
        notifyItemRemoved(positon);
    }
}
