package com.bwie.a1705b.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//    }

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();


    protected abstract void initView();


    protected abstract int bindLayoutId();


//    /**
//     * 网络判断
//     *
//     * @return
//     */
//    public boolean isNet() {
//
//        if (NetworkUtils.isConnected()){
//            showToast("有网");
//            return true;
//        }else {
//            showToast("无网");
//            return false;
//        }
//
//    }

    /**
     * 无参跳转
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {

        startActivity(new Intent(this, clz));
    }

    /**
     *
     * @param txt
     * @param a
     */
    public void showToast(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }



    /**
     * 公共的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        click(v);
    }

    /**
     * 子类实现的点击事件
     * @param v
     */
    protected abstract void click(View v);
}
