package com.charlye934.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            setOneTimeWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest(){
        val workManager = WorkManager.getInstance(applicationContext)
        val oneTimerRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .build()

        workManager.enqueue(oneTimerRequest)
        workManager.getWorkInfoByIdLiveData(oneTimerRequest.id)
                .observe(this, Observer {
                    txtResutl.text = it.state.name
                })
    }
}