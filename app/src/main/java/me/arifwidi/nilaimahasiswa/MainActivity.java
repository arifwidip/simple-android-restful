package me.arifwidi.nilaimahasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;
    CustomAdapter dataAdapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listMahasiswa);

        showProgressDialog();
        RestClient.get("mahasiswa", null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                hideProgressDialog();
                Log.i("Mahasiswa Data", responseString);
                Gson gson = new GsonBuilder().create();
                List<Mahasiswa> mahasiswalist = Arrays.asList(gson.fromJson(responseString, Mahasiswa[].class));
                dataAdapter = new CustomAdapter(getApplicationContext(), mahasiswalist);
                listview.setAdapter(dataAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_tambah:

                Intent intent = new Intent(getApplicationContext(), TambahData.class);
                startActivity(intent);

                return true;

            case R.id.menu_tentang:

                Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intentAbout);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Menampilkan Progress Dialog
     */
    private void showProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setMessage("Mengambil Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    private void hideProgressDialog() {
        progress.hide();
    }
}
