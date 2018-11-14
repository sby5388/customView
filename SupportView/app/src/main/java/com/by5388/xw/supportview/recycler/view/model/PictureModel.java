package com.by5388.xw.supportview.recycler.view.model;

import com.by5388.xw.supportview.recycler.view.bean.RequestResult;
import com.by5388.xw.supportview.recycler.view.net.NetTools;
import com.by5388.xw.supportview.recycler.view.net.PictureService;

import io.reactivex.Observable;

/**
 * @author by5388  on 2018/11/13.
 */
public class PictureModel implements IPictureModel {
    private PictureService service;

    public PictureModel() {
        service = new NetTools().getRetrofit().create(PictureService.class);
    }

    @Override
    public Observable<RequestResult> requestPictures(int number, int page) {
        return service.getPictures(number, page);
    }
}
