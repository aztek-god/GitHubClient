package github.sdv.com.githubclient

import android.support.v7.widget.SearchView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

fun <T> Observable<T>.onBackground(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
}

fun SearchView.asObservable(): Observable<String> {
    val publisher: PublishSubject<String> = PublishSubject.create()

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return if (query != null) {
                publisher.onNext(query)
                true
            } else {
                false
            }
        }

        override fun onQueryTextChange(query: String?): Boolean {
            if (query != null) {
                publisher.onNext(query)
                return true
            }
            return false
        }
    })

    return publisher
}