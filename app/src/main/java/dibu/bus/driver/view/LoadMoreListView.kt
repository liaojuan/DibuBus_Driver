package dibu.bus.driver.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.AbsListView
import android.widget.ListView

import java.util.ArrayList

class LoadMoreListView : ListView, AbsListView.OnScrollListener {

    /**
     * 是否是用户操作
     */
    private var isUserTounch: Boolean = false
    private var onLoadMoreListener: OnLoadMoreListener? = null
    var isLoadMore: Boolean = false
    private val scrollListeners = ArrayList<AbsListView.OnScrollListener>()

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        super.setOnScrollListener(this)
    }

    override fun setOnScrollListener(l: AbsListView.OnScrollListener) {
        scrollListeners.add(l)
    }

    override fun onScrollStateChanged(absListView: AbsListView, i: Int) {
        for (scrollListener in scrollListeners) {
            scrollListener?.onScrollStateChanged(absListView, i)
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        isUserTounch = true
        return super.onTouchEvent(ev)
    }

    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int,
                          visibleItemCount: Int, totalItemCount: Int) {
        for (scrollListener in scrollListeners) {
            scrollListener?.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount)
        }

        if (isUserTounch && adapter != null && adapter.count - headerViewsCount - footerViewsCount > 0 && firstVisibleItem + visibleItemCount >= totalItemCount && !isLoadMore) {
            if (onLoadMoreListener != null) {
                isLoadMore = true
                onLoadMoreListener!!.onLoadMore()
            }
        }

    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }
}

