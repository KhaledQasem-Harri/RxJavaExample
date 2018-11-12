//package com.example.khaledalqerem.rxjavaexample
//
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import io.reactivex.Observable
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.observers.DisposableObserver
//import io.reactivex.schedulers.Schedulers
//
//class MainActivity : AppCompatActivity() {
//
//    val compositeDisposable = CompositeDisposable()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val animalObservable = getAnimalsObservable()
//        val animalObserver = getAnimalObserver()
//        val animalCapObserver = getAnimalCapsObserver()
//
//        animalObservable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .filter { it.toLowerCase().startsWith("c") }
//            .map { it.toUpperCase() }
//            .subscribe(animalCapObserver)
//
//        animalObservable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .filter { it.toLowerCase().startsWith("b") }
//            .subscribe(animalObserver)
//
//
//
//        compositeDisposable.add(animalObserver)
//        compositeDisposable.add(animalCapObserver)
//    }
//
//    private fun getAnimalsObservable(): Observable<String> {
//        return Observable.fromArray(
//            "Ant", "Ape",
//            "Bat", "Bee", "Bear", "Butterfly",
//            "Cat", "Crab", "Cod",
//            "Dog", "Dove",
//            "Fox", "Frog"
//        )
//    }
//
//    private fun getAnimalObserver(): DisposableObserver<String> {
//        return object : DisposableObserver<String>() {
//            override fun onComplete() {
//                Log.d("MainActivity", "onComplete === All items are emitted")
//            }
//
//            override fun onNext(t: String) {
//                Log.d("MainActivity", "onNext === >> print $t")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.d("MainActivity", "onError ${e.message}")
//
//            }
//        }
//    }
//
//    private fun getAnimalCapsObserver(): DisposableObserver<String> {
//        return object : DisposableObserver<String>() {
//            override fun onComplete() {
//                Log.d("MainActivity", "onComplete  All items are emitted")
//            }
//
//            override fun onNext(t: String) {
//                Log.d("MainActivity", "onNext >> print $t")
//            }
//
//            override fun onError(e: Throwable) {
//                Log.d("MainActivity", "onError ${e.message}")
//
//            }
//        }
//    }
//
//    private fun prepareNotes(): List<Note> {
//        val notes = ArrayList<Note>()
//        notes.add(Note(1, "buy tooth paste!"))
//        notes.add(Note(2, "call brother!"))
//        notes.add(Note(3, "watch narcos tonight!"))
//        notes.add(Note(4, "pay power bill!"))
//
//        return notes
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        compositeDisposable.dispose()
//    }
//}
//
//class Note(var id: Int, var note: String)