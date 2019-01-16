package com.example.olskr.cplhm2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.edit_text1)
    EditText editText;
    @BindView(R.id.text_view1)
    TextView textView;
    @BindView(R.id.tap_event)
    Button tapEvent;
    @BindView(R.id.auto_event)
    Button AutoEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof Events.AutoEvent) {
                        textView.setText("Auto Event Received");
                    } else if (object instanceof Events.TapEvent) {
                        textView.setText("Tap Event Received");
                    }
                }));

        tapEvent.setOnClickListener(v -> ((MyApplication) getApplication())
                .bus()
                .send(new Events.TapEvent())
        );

        AutoEvent.setOnClickListener(v -> {
            ((MyApplication) getApplication())
                    .sendAutoEvent();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
