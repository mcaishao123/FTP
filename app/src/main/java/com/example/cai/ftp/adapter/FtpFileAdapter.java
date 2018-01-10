package com.example.cai.ftp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cai.ftp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sauronsoftware.ftp4j.FTPFile;

public class FtpFileAdapter extends RecyclerView.Adapter<FtpFileAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private FTPFile[] ftpFiles;
    private String dir;

    public FtpFileAdapter(Context context, FTPFile[] ftpFiles) {
        this.layoutInflater = LayoutInflater.from(context);
        this.ftpFiles = ftpFiles;
    }

    public void setFtpFiles(FTPFile[] ftpFiles) {
        this.ftpFiles = ftpFiles;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_file, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(ftpFiles[position]);
    }

    @Override
    public int getItemCount() {
        return ftpFiles == null ? 0 : ftpFiles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        private FTPFile ftpFile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bindView(FTPFile ftpFile) {
            this.ftpFile = ftpFile;
            Log.e("ViewHolder", "bindView: ");
            if(ftpFile.getType() == FTPFile.TYPE_DIRECTORY){
                this.img.setImageResource(R.drawable.ic_folder_white_24dp);
                this.img.setVisibility(View.VISIBLE);

            }else{
                this.img.setVisibility(View.GONE);

            }
        }
    }
}
