package com.charlye934.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            setOneTimeWorkRequest()
            //setPeridicWorkRequest()
        }
    }

    private fun setOneTimeWorkRequest(){
        val workManager = WorkManager.getInstance(applicationContext)

        val data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125)
            .build()

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimerRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val filteringRequest = OneTimeWorkRequest.Builder(FilteringWorker::class.java)
            .build()

        val compressingRequest = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        val downloadingWorker = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val paralleWorks = mutableListOf<OneTimeWorkRequest>()
        paralleWorks.add(downloadingWorker)
        paralleWorks.add(filteringRequest)

        workManager
            .beginWith(paralleWorks)
            .then(compressingRequest)
            .then(oneTimerRequest)
            .enqueue()
        workManager.getWorkInfoByIdLiveData( oneTimerRequest.id)
                .observe(this, Observer {
                    txtResutl.text = it.state.name
                    if(it.state.isFinished){
                        val data = it.outputData
                        val message = data.getString(UploadWorker.KEY_WORKER)
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT ).show()
                    }
                })
    }

    private fun setPeridicWorkRequest(){
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java, 16, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}