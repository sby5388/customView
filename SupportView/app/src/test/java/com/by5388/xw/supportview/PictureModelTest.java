package com.by5388.xw.supportview;

import com.by5388.xw.supportview.recycler.view.bean.PictureBean;
import com.by5388.xw.supportview.recycler.view.bean.RequestResult;
import com.by5388.xw.supportview.recycler.view.model.IPictureModel;
import com.by5388.xw.supportview.recycler.view.model.PictureModel;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author by5388  on 2018/11/14.
 */
public class PictureModelTest {
    @Test
    public void testGetPicture() {
        IPictureModel model = new PictureModel();
        model.requestPictures(10, 2)
                .subscribe(new Observer<RequestResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestResult requestResult) {
                        if (null == requestResult) {
                            return;
                        }
                        if (requestResult.isError()) {
                            return;
                        }
                        List<PictureBean> pictureBeans = requestResult.getPictureBeans();
                        if (null == pictureBeans || pictureBeans.isEmpty()) {
                            return;
                        }
                        for (PictureBean bean : pictureBeans) {
                            System.out.println(bean.getUrl());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
