package com.github.angads25.roomexample.interfaces;

/**
 * <p>
 * Created by Angad Singh on 20/7/17.
 * </p>
 */

public interface DataCallbackListener<T> {
    void onDataReceived(T data);
}
