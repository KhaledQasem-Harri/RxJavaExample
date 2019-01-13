package com.example.khaledalqerem.rxjavaexample

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class TeamMembersWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("TeamMembersWorker" , "just to test doWork() -----------------------")
        return Result.SUCCESS
    }
}