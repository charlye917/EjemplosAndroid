package com.charlye934.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class CompressingWorker(context: Context, params: WorkerParameters): Worker(context, params) {

    override fun doWork(): ListenableWorker.Result {
        try {
            for(i in 0 until 3000){
                Log.d("__TAG","Compressing $i")
            }
            return ListenableWorker.Result.success()
        }catch (e: Exception){
            return ListenableWorker.Result.failure()
        }

    }
}