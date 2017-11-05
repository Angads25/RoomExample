package com.github.angads25.roomexample.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <p>
 * Created by Angad Singh on 13/7/17.
 * </p>
 */

public interface OnItemClickListener {
    void onClick(RecyclerView parent, View v, int position);
}
