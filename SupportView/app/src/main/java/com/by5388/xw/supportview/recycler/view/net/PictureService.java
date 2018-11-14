package com.by5388.xw.supportview.recycler.view.net;

import com.by5388.xw.supportview.recycler.view.bean.RequestResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author by5388  on 2018/11/13.
 */
public interface PictureService {
    /**
     * 每页显示数量
     */
    String NUMBER = "number";
    /**
     * 页码数，第几页
     */
    String PAGE = "page";

    /**
     * 获取图片资源
     * api 来自:扔物线的RxJavaSamples-2.x demo
     *
     * @param number 每页显示数量
     * @param page   页码数，第几页
     * @return 结果
     */
    @GET("data/福利/{number}/{page}")
    Observable<RequestResult> getPictures(@Path(NUMBER) int number, @Path(PAGE) int page);

}
