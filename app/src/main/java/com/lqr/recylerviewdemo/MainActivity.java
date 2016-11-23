package com.lqr.recylerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @创建者 CSDN_LQR
 * @描述 RecyclerView练习Demo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOne;
    private Button mBtnTwo;
    private Button mBtnThree;
    private Button mBtnFour;
    private Button mBtnFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }


    private void initView() {
        setContentView(R.layout.activity_main);
        mBtnOne = (Button) findViewById(R.id.btnOne);
        mBtnTwo = (Button) findViewById(R.id.btnTwo);
        mBtnThree = (Button) findViewById(R.id.btnThree);
        mBtnFour = (Button) findViewById(R.id.btnFour);
        mBtnFive = (Button) findViewById(R.id.btnFive);
    }


    private void initListener() {
        mBtnOne.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnThree.setOnClickListener(this);
        mBtnFour.setOnClickListener(this);
        mBtnFive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                startActivity(new Intent(this, ActivityOne.class));
                break;
            case R.id.btnTwo:
                startActivity(new Intent(this, ActivityTwo.class));
                break;
            case R.id.btnThree:
                startActivity(new Intent(this, ActivityThree.class));
                break;
            case R.id.btnFour:
                startActivity(new Intent(this, ActivityFour.class));
                break;
            case R.id.btnFive:
                startActivity(new Intent(this, ActivityFive.class));
                break;
        }
    }
}
