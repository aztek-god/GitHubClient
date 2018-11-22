package github.sdv.com.githubclient.ui.custom

import android.content.Context
import android.support.v7.widget.SearchView
import android.util.AttributeSet
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ObservableSearchView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    SearchView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context) : this(context, null, -1)

    private val mPublisher: PublishSubject<String> = PublishSubject.create()

    val observable: Observable<String> get() = mPublisher

    init {
        setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    mPublisher.onNext(query)
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    mPublisher.onNext(query)
                    return true
                }
                return false
            }
        })
    }
}