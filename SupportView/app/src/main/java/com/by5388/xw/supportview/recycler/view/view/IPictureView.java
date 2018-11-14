package com.by5388.xw.supportview.recycler.view.view;

import com.by5388.xw.supportview.recycler.view.bean.PictureBean;

import java.util.List;

/**
 * @author by5388  on 2018/11/13.
 */
public interface IPictureView {
    /**
     * 回显图片
     *
     * @param beans 图片相关链接
     */
    void updatePicture(List<PictureBean> beans);

    /**
     * 开始查询
     */
    void startQuery();

    /**
     * 结束查询
     */
    void completeQuery();

    /**
     * 内容提示
     *
     * @param message 内容
     */
    void showTip(String message);
}
