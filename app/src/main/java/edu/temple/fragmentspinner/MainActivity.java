package edu.temple.fragmentspinner;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        colors = getResources().getStringArray(R.array.colors);
        final ColorAdapter adapter = new ColorAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, colors);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findViewById(R.id.text_view_item_name).setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class ColorAdapter extends BaseAdapter {
        private Context context;
        private String[] objects;

        public ColorAdapter(Context context, int textViewResourceId, String[] objects) {
            this.context = context;
            this.objects = objects;
        }

        @Override
        public int getCount() {
            return this.objects.length;
        }

        @Override
        public Object getItem(int position) {
            return objects[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.spinnerlayout, parent, false);
            }
            String currentItem = (String) getItem(position);

            TextView textViewItemName = convertView.findViewById(R.id.text_view_item_name);
            textViewItemName.setText(currentItem);
            textViewItemName.setBackgroundColor(Color.parseColor(currentItem));
            return convertView;
        }
    }
}
