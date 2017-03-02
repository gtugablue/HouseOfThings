package com.asso.integration1.hot.utils;

/**
 * Created by André on 02/03/2017.
 */

public interface NetworkCallback {

    void onResult(int requestCode, String response);
    void onResult(int requestCode, boolean response);
    void onResult(int requestCode, int response);

}
