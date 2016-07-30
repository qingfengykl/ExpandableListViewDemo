package com.kelin.day01_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created with Android Studio
 * User : kelin
 * Date : 2016/7/29
 * TIME : 19:25
 * PROJECT : day01_test
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> mGroupList;
    private List<List<String>> mChildList;
    private Context mContext;

    public MyExpandableListAdapter(Context context, List<String> groupList, List<List<String>> childList) {
        mContext = context;
        mGroupList = groupList;
        mChildList = childList;
    }

    @Override
    public int getGroupCount() {

        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChildList.get(i).size();
    }

    @Override
    public String getGroup(int i) {
        return mGroupList.get(i);
    }

    @Override
    public String getChild(int i, int i1) {
        return mChildList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        textView.setText("-.-"+getGroup(i));
        return textView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        textView.setText("       "+getChild(i,i1));
        return textView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
