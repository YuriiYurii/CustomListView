package yuriitsap.example.com.customlistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    private CustomListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mListView = (CustomListView) findViewById(R.id.list_view);
        mListView.setBaseAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("TAG", "convertView " + (convertView == null ? "null" : convertView.toString()));
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }
            ((Button) convertView).setText(((Button) convertView).getText() + " " + position);

            return convertView;
        }
    }
}
