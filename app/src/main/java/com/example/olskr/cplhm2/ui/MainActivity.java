package com.example.olskr.cplhm2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.olskr.cplhm2.R;
import com.example.olskr.cplhm2.mvp.presenter.MainPresenter;
import com.example.olskr.cplhm2.mvp.view.MainView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

//решение с использование MVP
public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.edit_text1)
    EditText editText;
    @BindView(R.id.text_view1)
    TextView textView;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RxTextView.textChanges(editText)
                .subscribe(charSequence -> presenter.textChanged(charSequence.toString()));
    }


    @Override
    public void setTextViewText(String text) {
        textView.setText(text);
    }
}
