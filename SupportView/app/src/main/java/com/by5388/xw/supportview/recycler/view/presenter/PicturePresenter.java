package com.by5388.xw.supportview.recycler.view.presenter;

import com.by5388.xw.supportview.recycler.view.bean.RequestResult;
import com.by5388.xw.supportview.recycler.view.model.IPictureModel;
import com.by5388.xw.supportview.recycler.view.model.PictureModel;
import com.by5388.xw.supportview.recycler.view.view.IPictureView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/11/14.
 */
public class PicturePresenter implements IPicturePresenter {
    private final IPictureView view;
    private final IPictureModel model;
    private final CompositeDisposable compositeDisposable;

    public PicturePresenter(IPictureView view) {
        this.view = view;
        this.model = new PictureModel();
        compositeDisposable = new CompositeDisposable();
    }

    @Override

    public void queryPicture(int number, int page) {
        view.startQuery();
        compositeDisposable.add(
                model.requestPictures(number, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RequestResult>() {
                            @Override
                            public void accept(RequestResult requestResult) {
                                if (null == requestResult || requestResult.isError()) {
                                    return;
                                }
                                view.updatePicture(requestResult.getPictureBeans());
                                view.completeQuery();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                view.showTip(throwable.getLocalizedMessage());
                                view.completeQuery();
                            }
                        })
        );

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
