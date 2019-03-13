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
    String[] colorCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        colors = getResources().getStringArray(R.array.colors);
        colorCodes = getResources().getStringArray(R.array.colorCodes);
        final ColorAdapter adapter = new ColorAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, colors, colorCodes);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findViewById(R.id.text_view_item_name).setBackgroundColor(Color.WHITE);
                findViewById(R.id.cFrag).setBackgroundColor(adapter.getColor(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class ColorAdapter extends BaseAdapter {
        private Context context;
        private String[] objects;
        private String[] colorCodes;

        public ColorAdapter(Context context, int textViewResourceId, String[] objects, String[] colorCodes) {
            this.context = context;
            this.objects = objects;
            this.colorCodes = colorCodes;
        }

        @Override
        public int getCount() {
            return this.objects.length;
        }

        @Override
        public Object getItem(int position) {
            return objects[position];
        }

        public int getColor(int position) {
            return Color.parseColor(colorCodes[position]);
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
            String colorName = (String) getItem(position);
            int color = getColor(position);

            TextView textViewItemName = convertView.findViewById(R.id.text_view_item_name);
            textViewItemName.setText(colorName);
            textViewItemName.setBackgroundColor(color);
            return convertView;
        }
    }
}
