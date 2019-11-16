package com.bwie.a1705b.base.mvp;

import java.lang.ref.WeakReference;

/**
 * basepresenter
 * @param <M>
 * @param <V>
 */
public  abstract class IBasePresenter<M,V> {

    private V view;
    private WeakReference<V> weakReference;

    /**
     * 绑定
     */
    public void attach(V v){
        this.view = v;
//        this.model = m;
        weakReference = new WeakReference<>(v);
    }

    /**
     * 解绑
     */
    public void detach(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference = null;
            this.view = null;

        }
    }

//    abstract M setModel();


}
