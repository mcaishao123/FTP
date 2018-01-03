package com.example.cai.ftp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cai.ftp.R;

import it.sauronsoftware.ftp4j.FTPFile;

public class FtpFileAdapter extends RecyclerView.Adapter<FtpFileAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private FTPFile[] ftpFiles;

    public FtpFileAdapter(Context context, FTPFile[] ftpFiles) {
        this.layoutInflater = LayoutInflater.from(context);
        this.ftpFiles = ftpFiles;
    }

    @Override
    public FtpFileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.activity_main, parent));
    }

    @Override
    public void onBindViewHolder(FtpFileAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ftpFiles == null ? 0 : ftpFiles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        void bindView(String name) {
            this.textView.setText(name);
        }
    }
}
