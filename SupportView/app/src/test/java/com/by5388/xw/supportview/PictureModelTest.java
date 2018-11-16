package com.by5388.xw.supportview;

import com.by5388.xw.supportview.download.DownLoadTask;
import com.by5388.xw.supportview.recycler.view.bean.PictureBean;
import com.by5388.xw.supportview.recycler.view.bean.RequestResult;
import com.by5388.xw.supportview.recycler.view.model.IPictureModel;
import com.by5388.xw.supportview.recycler.view.model.PictureModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author by5388  on 2018/11/14.
 */
public class PictureModelTest {
    @Test
    public void testGetPicture() {
        final DownLoadTask task = DownLoadTask.getInstance();
        IPictureModel model = new PictureModel();

        model.requestPictures(1000, 1)
                .map(new Function<RequestResult, List<String>>() {
                    @Override
                    public List<String> apply(RequestResult requestResult) throws Exception {
                        List<String> urls = new ArrayList<>();
                        if (null == requestResult) {
                            return urls;
                        }
                        if (requestResult.isError()) {
                            return urls;
                        }
                        List<PictureBean> pictureBeans = requestResult.getPictureBeans();
                        if (null == pictureBeans || pictureBeans.isEmpty()) {
                            return urls;
                        }
                        for (PictureBean bean : pictureBeans) {
                            urls.add(bean.getUrl());
                        }
                        return urls;
                    }
                })
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        for (String str : strings) {
                            task.addTask(str);
                        }
                        task.startTask(0);
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
