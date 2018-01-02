package com.example.majid_fit5.al_rajhitakaful.createorder;

import android.support.annotation.NonNull;

import com.example.majid_fit5.al_rajhitakaful.data.DataRepository;
import com.example.majid_fit5.al_rajhitakaful.data.DataSource;
import com.example.majid_fit5.al_rajhitakaful.data.models.AlRajhiTakafulError;
import com.example.majid_fit5.al_rajhitakaful.data.models.order.Order;
import com.example.majid_fit5.al_rajhitakaful.data.models.request.OrderRequest;
import com.example.majid_fit5.al_rajhitakaful.login.mobilephoneinsertion.MobilePhoneInsertionContract;

import java.lang.ref.WeakReference;

/**
 * Created by Eng. Abdulmajid Alyafey on 1/2/2018.
 */

public class CreateOrderPresenter implements CreateOrderContract.Presenter {
    private DataRepository mDataRepository;
    private WeakReference<CreateOrderContract.View> mView;

    public CreateOrderPresenter(DataRepository mDataRepository){
        this.mDataRepository=mDataRepository;
    }

    @Override
    public void onBind(@NonNull CreateOrderContract.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy() {
        if(mView.get()!=null)
            mView.clear();
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        if(mView.get()!=null){
            mDataRepository.createOrder(orderRequest, new DataSource.CreateOrderCallback() {
                @Override
                public void onCreateOrderResponse(Order currentOrder) {
                    mView.get().onCreateOrderSuccess(currentOrder);
                }
                @Override
                public void onFailure(AlRajhiTakafulError error) {
                    mView.get().onCreateOrderFailure(error);
                }
            });
        }
    }
}
