package com.example.cai.ftp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cai.ftp.R;
import com.example.cai.ftp.adapter.FtpFileAdapter;
import com.example.cai.ftp.dialog.FtpSettingDialog;
import com.example.cai.ftp.listener.ConnectHandler;
import com.example.cai.ftp.repository.FtpRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sauronsoftware.ftp4j.FTPFile;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.path_recycler)
    RecyclerView pathRecyclerView;
    @BindView(R.id.file_recycler)
    RecyclerView fileRecyclerView;
    private FtpFileAdapter ftpFileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupContent();
        loadData();
    }

    private void setupContent() {
        ftpFileAdapter = new FtpFileAdapter(this, null);
        fileRecyclerView.setAdapter(ftpFileAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            new FtpSettingDialog(this, new FtpSettingDialog.ClickListener() {
                @Override
                public void onClickConfirm() {
                    loadData();
                }
            }).show();
            return true;
        } else if (id == R.id.action_refresh) {
            loadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, null, getString(R.string.please_wait), false, false);
         FtpRepository.getInstance(new ConnectHandler() {
            @Override
            public void onSuccessful(FTPFile[] ftpFiles) {
                if (ftpFileAdapter != null) {
                    ftpFileAdapter.setFtpFiles(ftpFiles);
                    ftpFileAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
