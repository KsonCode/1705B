package com.bwie.a1705b.api;

import com.bwie.a1705b.base.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {

    /**
     * 上传头像
     * @param uid 用户id
     * @param sid token，sessionid
     * @param file 文件part类型
     * @return
     */
    @POST("techApi/user/verify/v1/modifyHeadPic")
    @Multipart
    Observable<BaseBean> uploadPic(@Header("userId") String uid,
                                   @Header("sessionId") String sid, @Part MultipartBody.Part file);

}
