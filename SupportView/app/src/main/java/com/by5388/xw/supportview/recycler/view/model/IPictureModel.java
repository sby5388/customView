package com.by5388.xw.supportview.recycler.view.model;

import com.by5388.xw.supportview.recycler.view.bean.RequestResult;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/11/13.
 */
public interface IPictureModel {
    /**
     * 请求图片
     * @param number 数量
     * @param page 页数
     * @return object
     */
    Observable<RequestResult> requestPictures(int number,int page);
}
