package me.arifwidi.nilaimahasiswa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by arifwidipratomo on 2/5/17.
 */

public class TambahData extends AppCompatActivity {

    EditText nama;
    EditText nim;
    EditText nilai;
    Button save;
    private ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        nama = (EditText) findViewById(R.id.editTextNama);
        nim = (EditText) findViewById(R.id.editTextNIM);
        nilai = (EditText) findViewById(R.id.editTextNilai);
        save = (Button) findViewById(R.id.buttonSimpan);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( validasi() ) {
                    showProgressDialog();
                    saveData();
                }
            }
        });
    }

    /**
     * Validasi data
     */
    private boolean validasi() {
        Boolean validated = true;

        if (nama.getText().toString().equals("")) {
            Toast.makeText(getApplication(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            validated = false;
        } else if (nim.getText().toString().equals("")) {
            Toast.makeText(getApplication(), "NIM tidak boleh kosong", Toast.LENGTH_SHORT).show();
            validated = false;
        } else if (nilai.getText().toString().equals("")) {
            Toast.makeText(getApplication(), "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            validated = false;
        }

        return validated;
    }

    /**
     * Menampilkan Progress Dialog
     */
    private void showProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setMessage("Menyimpan Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }

    private void hideProgressDialog() {
        progress.hide();
    }

    /**
     * Simpan data ke Rest API
     */
    public void saveData() {
        RequestParams params = new RequestParams();
        params.put("nim", nim.getText());
        params.put("nama", nama.getText());
        params.put("nilai", nilai.getText());

        RestClient.post("mahasiswa", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                hideProgressDialog();
                Toast.makeText(getApplication(), "Data gagal disimpan, silakan coba beberapa saat lagi", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                hideProgressDialog();
                resetForm();
                Toast.makeText(getApplication(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Reset Form
     */
    private void resetForm() {
        nama.setText("");
        nim.setText("");
        nilai.setText("");
    }
}
