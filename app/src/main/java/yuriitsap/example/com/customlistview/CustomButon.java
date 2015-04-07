package yuriitsap.example.com.customlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * Created by yuriitsap on 07.04.15.
 */
public class CustomButon extends Button {

    private static final String TAG = "CustomButon";

    public CustomButon(Context context) {
        super(context);
    }

    public CustomButon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
