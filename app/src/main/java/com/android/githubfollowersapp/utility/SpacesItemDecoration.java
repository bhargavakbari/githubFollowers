package com.android.githubfollowersapp.utility;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    public static final String FOLLOWERS_TAG = "FOLLOWERS_ITEM";
    private final int separation;
    private final String flag;

    public SpacesItemDecoration(String flag, int separation) {
        this.separation = separation;
        this.flag = flag;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (flag.equals(FOLLOWERS_TAG)) {
            outRect.top = separation;
        }
    }
}
