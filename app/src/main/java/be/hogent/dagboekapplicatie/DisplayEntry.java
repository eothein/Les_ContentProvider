package be.hogent.dagboekapplicatie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayEntry extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_entry);
        Intent intent = getIntent();
        if(intent != null){
            String title = intent.getStringExtra("TITLE");
            String date = intent.getStringExtra("DATE");
            String content = intent.getStringExtra("CONTENT");
            final TextView vTitle = (TextView)findViewById(R.id.diaryShowTitle);
            final TextView vDate = (TextView)findViewById(R.id.diaryShowDate);
            final TextView vContent = (TextView)findViewById(R.id.diaryShowContent);
            vTitle.setText(title);
            vDate.setText(date);
            vContent.setText(content);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
