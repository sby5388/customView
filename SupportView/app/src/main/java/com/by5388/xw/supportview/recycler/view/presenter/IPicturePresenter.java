package com.by5388.xw.supportview.recycler.view.presenter;

/**
 * @author by5388  on 2018/11/13.
 */
public interface IPicturePresenter {
    /**
     * 查询图片
     *
     * @param page   页码
     * @param number 数量
     */
    void queryPicture(int number, int page);


    /**
     * 关闭时，取消订阅关系，防止内存泄露
     */
    void unSubscribe();
}
