package be.hogent.dagboekapplicatie;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.hogent.dagboekapplicatie.persistence.Constants;
import be.hogent.dagboekapplicatie.persistence.MyDB;


public class SubmitEntryActivity extends Activity {

    private EditText titleEditText;
    private EditText contentEditText;
    private Button submitButton;
    private MyDB dba;

    /**
     * Creates this activity. Make sure that (i) you generate the db, (ii) open it, (iii) initialise
     * the references for the view and (iv) you add the code for the button listener.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_entry);
        dba = new MyDB(this);
        dba.open();
        titleEditText = (EditText)findViewById(R.id.editText_diary_title);
        contentEditText = (EditText)findViewById(R.id.editText_content);
        submitButton = (Button)findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }

    /**
     * Saves the information provided in the UI to the database. Check for null or empty strings
     * before entering into the DBA. Make toast messages in case of empty strings.
     */
    private void saveToDB(){
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        int duration = Toast.LENGTH_SHORT;
        if(title.isEmpty()){
            CharSequence text = "Don't forget your  title";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else if(content.isEmpty()){
            CharSequence text = "Don't forget you diary entry";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        }else{
            ContentValues newTaskValue = new ContentValues();
            newTaskValue.put(Constants.TITLE_NAME,title);
            newTaskValue.put(Constants.CONTENT_NAME,content);
            newTaskValue.put(Constants.DATE_NAME,System.currentTimeMillis());
            ContentResolver cr = getContentResolver();
            cr.insert(DiaryContentProvider.CONTENT_URI,newTaskValue);

            CharSequence text = "Added entry";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            Intent i = new Intent(this, DisplayDiaries.class);
            startActivity(i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.submit_entry, menu);
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
