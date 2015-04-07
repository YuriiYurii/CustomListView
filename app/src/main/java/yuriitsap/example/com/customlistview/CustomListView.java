package yuriitsap.example.com.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

/**
 * Created by yuriitsap on 07.04.15.
 */
public class CustomListView extends ViewGroup {

    private final static String TAG = "CustomListView";
    private BaseAdapter mBaseAdapter;
    private LinkedList<View> mVisibleViews;
    private GestureDetector mGestureDetector;
    private int mScrollX;

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mVisibleViews = new LinkedList<>();
        mGestureDetector = new GestureDetector(getContext(), new ListViewGestureListener());
        mScrollX = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = getMeasuredWidth();
        int parentHeight = getMeasuredHeight();
        int borderHeight = 0;
        int childCount = mBaseAdapter.getCount();
        if (childCount != 0) {
            for (int i = 0; i < childCount; i++) {
                View child = mBaseAdapter.getView(i, null, this);
                child.measure(
                        MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.AT_MOST));
                borderHeight += child.getMeasuredHeight();
                mVisibleViews.add(child);
                addViewInLayout(child, i, child.getLayoutParams());
                if (borderHeight >= parentHeight) {
                    break;
                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentTop = getPaddingTop();
        for (View view : mVisibleViews) {
            view.layout(getPaddingLeft(), currentTop, r - l, currentTop + view.getMeasuredHeight());
            currentTop += view.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    public BaseAdapter getBaseAdapter() {
        return mBaseAdapter;
    }

    public void setBaseAdapter(BaseAdapter baseAdapter) {
        mBaseAdapter = baseAdapter;
    }

    private void scrollVertically(int offset) {
        mScrollX -= offset;
        int temporaryOffset=getHeight()-mScrollX;
        for (View view : mVisibleViews) {
            view.offsetTopAndBottom(offset);
        }
    }

    private class ListViewGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "distance " + distanceY);
            scrollVertically((int) -distanceY);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
