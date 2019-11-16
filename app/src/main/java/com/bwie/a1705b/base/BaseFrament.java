package com.bwie.a1705b.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFrament extends Fragment {

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayoutId(),container,false);;
        unbinder = ButterKnife.bind(this,view);
        return view;

    }

    protected abstract void initData();


    protected abstract void initView();



    protected abstract int bindLayoutId();

    /**
     * 网络判断
     * @return
     */
    public boolean isNet(){

        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
