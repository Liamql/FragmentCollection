package com.example.administrator.izhihucollection.MVP.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.izhihucollection.MVP.ui.fragment.HomeFragment;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.LActivity;
import com.example.administrator.izhihucollection.di.component.AppComponent;

public class MainActivity extends LActivity {

    private int position;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_home,null,false);
    }

    @Override
    protected void initData() {
        selectItem(0,"首页");
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    public void selectItem(int position, String title) {
        Fragment fragment = null;
        this.position = position;
        switch (position) {
            case 0:
                //首页
                fragment = new HomeFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment).commit();
            setTitle(title);
        } else {
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }
}
