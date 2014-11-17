package be.hogent.dagboekapplicatie;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import be.hogent.dagboekapplicatie.persistence.Constants;
import be.hogent.dagboekapplicatie.persistence.MyDB;

/**
 * Created by jbuy519 on 26/10/2014.
 */
public class DisplayDiaries extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * The database we are storing the database entries
     */
    private MyDB dba;
    /**
     * The DiaryAdapter to show the diary entries
     */
    private DiaryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dba = new MyDB(this);
        dba.open();
        setContentView(R.layout.diaries);

        super.onCreate(savedInstanceState);
        adapter = new DiaryAdapter(dba,this);
        this.setListAdapter(adapter);
        getLoaderManager().initLoader(0, null, this);
        ListView view= getListView();
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DiaryEntry entry = (DiaryEntry)adapter.getItem(position);
                    Intent i = new Intent(getApplicationContext(),DisplayEntry.class);
                    i.putExtra("TITLE",entry.getTitle());
                    i.putExtra("DATE",entry.getRecordedDate());
                    i.putExtra("CONTENT",entry.getContent());
                    startActivity(i);
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this,DiaryContentProvider.CONTENT_URI,
                null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.setDataToNull();
    }
}
