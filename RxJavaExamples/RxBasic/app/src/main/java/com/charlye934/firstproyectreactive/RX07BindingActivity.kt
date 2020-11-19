package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_rx07_binding.*
import java.util.concurrent.TimeUnit


class RX07BindingActivity : AppCompatActivity() {

   /* private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx07_binding)

        disposable = RxView.clicks(btRXBinding)
            .subscribe(Consumer<Any?> { //Manejamos el Evento onClick
                Log.d("TAG1", "onClick utilizando RX")
            })


        RxTextView.textChanges(etRXBinding)
            .subscribe(Consumer<CharSequence?> { Log.d("TAG1", "onTextChange") })

        RxView.clicks(btRXBinding1)
            .debounce(1, TimeUnit.SECONDS)
            .subscribe { e -> Log.d("TAG1", "onClic con un segundo") }

        RxTextView.textChanges(etRXBinding1)
            .debounce(1, TimeUnit.SECONDS)
            .map { e -> etRXBinding1.getText().toString() }
            .subscribe { e -> Log.d("TAG1", e) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable!!.dispose()
    }*/
}
