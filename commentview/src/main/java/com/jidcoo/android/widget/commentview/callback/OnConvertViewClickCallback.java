package com.jidcoo.android.widget.commentview.callback;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * 可复用点击事件回调
 *
 * @author Jidcoo
 */
public abstract class OnConvertViewClickCallback implements View.OnClickListener {

    private View convertView;
    private int[] positionIds;

    public OnConvertViewClickCallback(View convertView, int... positionIds) {
        this.convertView = convertView;
        this.positionIds = positionIds;

    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public void onClick(View v) {
        int len = positionIds.length;
        int[] positions = new int[len];
        for (int i = 0; i < len; i++) {
            positions[i] = (int) convertView.getTag(positionIds[i]);
        }
        onClickCallback(v, positions);
    }

    public abstract void onClickCallback(View view, int... positionIds);

}