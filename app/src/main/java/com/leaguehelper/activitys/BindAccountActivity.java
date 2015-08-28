package com.leaguehelper.activitys;

import android.os.Bundle;
import android.widget.EditText;

import com.leaguehelper.BaseActivity;
import com.leaguehelper.R;
import com.leaguehelper.widget.CircularImageView;

public class BindAccountActivity extends BaseActivity{
    private EditText mServerArea;
    private EditText mName;
    private CircularImageView mHeaderIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_account_layout);
        init();
    }

    void init(){
        mServerArea = (EditText) findViewById(R.id.et_server_area);
        mName = (EditText) findViewById(R.id.et_name);
        mHeaderIcon = (CircularImageView) findViewById(R.id.img_header_icon);
    }
}
