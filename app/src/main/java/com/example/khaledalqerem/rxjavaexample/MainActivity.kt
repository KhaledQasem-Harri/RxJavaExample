package com.example.khaledalqerem.rxjavaexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.*
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val TEAM_UPDATE_TASK_ID = "team_members_update"

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enqueueTeamMembersWorker()
//        val disposableObserver = getObserver()
//        val notes = prepareNotes()
//        val observable =
//            Observable.create<Note> { emitter -> notes.forEach { emitter.onNext(it) } }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(disposableObserver)
//        val observable =
//            Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//            Observable.range(1, 20)
////                .repeat(4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter { it % 2 == 0 }
//                .map { "$it is even number" }
//                .subscribeWith(disposableObserver)


//        compositeDisposable.add(observable)
    }


    private fun getObserver(): DisposableObserver<Note> {
        return object : DisposableObserver<Note>() {
            override fun onNext(t: Note) {
                Log.d("MainActivity", "note = ${t.note}")
            }

            override fun onComplete() {
                Log.d("MainActivity", "onComplete === All items are emitted")
            }


            override fun onError(e: Throwable) {
                Log.d("MainActivity", "onError ${e.message}")

            }
        }
    }

    private fun getAnimalCapsObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.d("MainActivity", "onComplete  All items are emitted")
            }

            override fun onNext(t: String) {
                Log.d("MainActivity", "onNext >> print $t")
            }

            override fun onError(e: Throwable) {
                Log.d("MainActivity", "onError ${e.message}")

            }
        }
    }

    private fun prepareNotes(): List<Note> {
        val notes = ArrayList<Note>()
        notes.add(Note(1, "buy tooth paste!"))
        notes.add(Note(2, "call brother!"))
        notes.add(Note(3, "watch narcos tonight!"))
        notes.add(Note(4, "pay power bill!"))

        return notes
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun enqueueTeamMembersWorker() {
//        val constraints: Constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
        val teamMembersWork =
//            OneTimeWorkRequest.Builder(TeamMembersWorker::class.java).build()
            PeriodicWorkRequest.Builder(TeamMembersWorker::class.java, 16, TimeUnit.MINUTES).build()
//                .setConstraints(constraints).addTag("teamWorker").build()
        WorkManager.getInstance().enqueue(teamMembersWork)
//            .enqueueUniquePeriodicWork(
//                TEAM_UPDATE_TASK_ID,
//                ExistingPeriodicWorkPolicy.KEEP,
//                teamMembersWork
//            )
    }


}

class Note(var id: Int, var note: String)