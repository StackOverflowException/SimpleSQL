package com.stackoverflowexception.simplesql;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    KeyValueStore _database;
    EditText _editKey, _editValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _database = new KeyValueStore(this);
        _editKey = (EditText) findViewById(R.id.editTextKey);
        _editValue = (EditText) findViewById(R.id.editTextValue);
        }

        public  void onSaveClicked(View view){
            String key   = _editKey.getText().toString();
            String value = _editValue.getText().toString();
            _database.addEntry(new Entry(key, value));
        }

        public void onLookupClicked(View view) {
            String key   = _editKey.getText().toString();
            _editValue.setText(_database.getValue(key));
        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }