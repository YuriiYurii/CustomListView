package yuriitsap.example.com.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mVisibleViews = new LinkedList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = getMeasuredWidth();
        int parentHeight = getMeasuredHeight();
        int borderHeight = 0;
        int childCount = mBaseAdapter.getCount();
        Log.e(TAG, "Childs = " + childCount);
        if (childCount != 0) {
            for (int i = 0; i < childCount; i++) {
                View child = mBaseAdapter.getView(i, null, this);
                child.measure(
                        MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.AT_MOST));
                borderHeight += child.getMeasuredHeight();
                mVisibleViews.add(child);
                Log.e(TAG, "elements measured = " + i);
                if (borderHeight >= parentHeight) {
                    break;
                }

            }

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentLeft = getPaddingLeft();
        int currentTop = getPaddingTop();
        int childCount = mVisibleViews.size();
        for (int i = 0; i < childCount; i++) {
            View view = mVisibleViews.get(i);
            addViewInLayout(view, i, view.getLayoutParams());
            view.layout(currentLeft, currentTop, r - l, currentTop + view.getMeasuredHeight());
            currentTop += view.getMeasuredHeight();
        }

    }

    public BaseAdapter getBaseAdapter() {
        return mBaseAdapter;
    }

    public void setBaseAdapter(BaseAdapter baseAdapter) {
        mBaseAdapter = baseAdapter;
    }
}
