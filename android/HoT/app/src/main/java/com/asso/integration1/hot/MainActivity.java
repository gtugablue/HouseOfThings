package com.asso.integration1.hot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asso.integration1.hot.utils.NetworkCallback;
import com.asso.integration1.hot.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements NetworkCallback {

    private final static String STATUS_ON_TEXT = "ON";
    private final static String STATUS_OFF_TEXT = "OFF";

    private static final int STATUS_REQUEST = 1;

    private TextView statusText;
    private Button statusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = (TextView) findViewById(R.id.statusTextView);
        statusButton = (Button) findViewById(R.id.statusButton);

        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatus();
            }
        });
    }

    private void updateStatus() {
        statusText.setText("");
        NetworkUtils.getStatus(STATUS_REQUEST, this);
    }

    @Override
    public void onResult(int requestCode, final boolean response) {
        if(requestCode == STATUS_REQUEST) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(response) {
                        statusText.setText("STATUS_ON_TEXT");
                        statusText.setTextColor(Color.GREEN);
                    } else {
                        statusText.setText("STATUS_OFF_TEXT");
                        statusText.setTextColor(Color.RED);
                    }
                }
            });
        }
    }

    @Override
    public void onResult(int requestCode, String response) {}

    @Override
    public void onResult(int requestCode, int response) {}
}
