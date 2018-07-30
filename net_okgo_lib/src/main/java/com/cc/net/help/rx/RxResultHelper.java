package com.cc.net.help.rx;

import com.cc.net.BaseResponse;
import com.cc.net.MsgResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: Administrator on 2018-04-13 15:29
 * @github: https://github.com/CcSimple
 * @describe: Rx数据统一处理
 */

public class RxResultHelper {
    private static final int RESPONSE_SUCCESS_CODE = 1;
    private static final int RESPONSE_FAIL_CODE = 0;
    private static final int RESPONSE_ERROR_CODE = -1;

    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> tObservable) {
                return tObservable.flatMap(
                        new Function<BaseResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                                //可以相应更改
                                if (tBaseResponse.getCode() == RESPONSE_SUCCESS_CODE) {
                                    return Observable.just(tBaseResponse.getData());
                                } else if (tBaseResponse.getCode() == RESPONSE_FAIL_CODE) {
                                    return Observable.error(new Exception(tBaseResponse.getMsg()));
                                }  else if (tBaseResponse.getCode() == RESPONSE_ERROR_CODE) {
                                    return Observable.error(new Exception(tBaseResponse.getMsg()));
                                } else {
                                    return Observable.empty();
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        };
    }
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult(final int code) {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> tObservable) {
                return tObservable.flatMap(
                        new Function<BaseResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                                //可以相应更改
                                if (tBaseResponse.getCode() == code) {
                                    return Observable.just(tBaseResponse.getData());
                                } else if (tBaseResponse.getCode() == RESPONSE_FAIL_CODE) {
                                    return Observable.error(new Exception(tBaseResponse.getMsg()));
                                }  else if (tBaseResponse.getCode() == RESPONSE_ERROR_CODE) {
                                    return Observable.error(new Exception(tBaseResponse.getMsg()));
                                } else {
                                    return Observable.empty();
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        };
    }

    public static <T> ObservableTransformer<MsgResponse<T>, T> handleMsg() {
        return new ObservableTransformer<MsgResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<MsgResponse<T>> tObservable) {
                return tObservable.flatMap(
                        new Function<MsgResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> apply(MsgResponse<T> tMsgResponse) throws Exception {
                                //可以相应更改
                                if (tMsgResponse.getCode() == RESPONSE_SUCCESS_CODE) {
                                    return Observable.just(tMsgResponse.getMsg());
                                } else if (tMsgResponse.getCode() == RESPONSE_FAIL_CODE) {
                                    return Observable.error(new Exception(tMsgResponse.getMsg().toString()));
                                }  else if (tMsgResponse.getCode() == RESPONSE_ERROR_CODE) {
                                    return Observable.error(new Exception(tMsgResponse.getMsg().toString()));
                                } else {
                                    return Observable.empty();
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
