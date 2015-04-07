package yuriitsap.example.com.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by yuriitsap on 07.04.15.
 */
public class TrialListView extends ListView {

    private static int measureCounter=0;
    private static int layoutCounter=0;


    public TrialListView(Context context) {
        super(context);
    }

    public TrialListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG", " measure " + ++measureCounter);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("TAG", " layout " + ++layoutCounter);
    }
}
