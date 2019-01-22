package com.example.olskr.cplhm2.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.olskr.cplhm2.mvp.model.Model;
import com.example.olskr.cplhm2.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    Model model;

    public MainPresenter() {
        this.model = new Model();
    }

    public void textChanged(String text) {
        getViewState().setTextViewText(text);
    }
}
