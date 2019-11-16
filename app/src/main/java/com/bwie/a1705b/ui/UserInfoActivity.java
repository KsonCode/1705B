package com.bwie.a1705b.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bwie.a1705b.R;
import com.bwie.a1705b.api.UserService;
import com.bwie.a1705b.base.BaseActivity;
import com.bwie.a1705b.base.bean.BaseBean;
import com.bwie.a1705b.utils.RetrofitUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoActivity extends BaseActivity {

   @BindView(R.id.upload)
    TextView uploadTv;

    List<LocalMedia> localMediaList;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {



    }

    /**
     * 上传头像
     * @param view
     */
    @OnClick(R.id.upload)
    public void  upload(View view){


        //第一步：弹出窗口


        //第二步：相机拍摄或者相册
        //相册
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//打开相册
                .compress(true)
                .maxSelectNum(1)//最多一张
                .forResult(PictureConfig.CHOOSE_REQUEST);

        //相册
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())//拍摄
                .compress(true)//压缩
                .maxSelectNum(1)//最多一张
                .forResult(PictureConfig.CHOOSE_REQUEST);


        //第三步拿到选择的图片数据对象，拼装参数，请求上传头像接口，进行上传
        //上传头像
        if (localMediaList!=null&&localMediaList.size()>0){
            File file = new File(localMediaList.get(0).getCompressPath());
            //把文件对象包装成请求体对象
//            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part  和后端约定好Key，这里的partName是用file
            //最终转换成需要的多表单对象
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//上传头像结束
            //发布帖子接口
            RetrofitUtils.getInstance().createService(UserService.class)
                    .uploadPic(SPUtils.getInstance().getString("userId"),SPUtils.getInstance().getString("seessionId"),filePart)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BaseBean>() {
                        @Override
                        public void accept(BaseBean baseBean) throws Exception {

                            showToast(baseBean.message);

//                            Glide.with(UserInfoActivity.this).load(Uri.fromFile(new File(localMediaList.get(0).getCompressPath()))).into();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });


        }



    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void click(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调,框架的回调
                   localMediaList = PictureSelector.obtainMultipleResult(data);

                    break;
            }
        }
    }
}
