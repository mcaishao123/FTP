package com.example.cai.ftp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cai.ftp.R;
import com.example.cai.ftp.model.FtpInfo;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sauronsoftware.ftp4j.FTPFile;

public class FtpFileAdapter extends RecyclerView.Adapter<FtpFileAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private FTPFile[] ftpFiles;
    private FtpInfo ftpInfo;

    public FtpFileAdapter(Context context, FTPFile[] ftpFiles) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.ftpFiles = ftpFiles;
    }

    public void setFtpFiles(FTPFile[] ftpFiles) {
        this.ftpFiles = ftpFiles;
    }

    public void setFtpInfo(FtpInfo ftpInfo) {
        this.ftpInfo = ftpInfo;
        this.ftpFiles = ftpInfo.getFtpFiles();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_file, parent, false));
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
            ButterKnife.bind(this, itemView);
        }

        void bindView(FTPFile ftpFile) {
            this.ftpFile = ftpFile;
//            if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY) {
////                Picasso.with(context).load(R.drawable.ic_folder).error(R.drawable.ic_assignment).into(this.img);
//            } else {
//                Picasso.with(context).load(ftpInfo.getPath()+ftpFile.getName()).into(this.img);
//                Log.e("ViewHolder", "bindView: "+ftpInfo.getPath()+ftpFile.getName());
//            }
            if((ftpInfo.getPath()+ftpFile.getName()).equalsIgnoreCase("ftp://192.168.1.102:3333/IMG_20170714_154537.jpg")){
                                Picasso.with(context).load(ftpInfo.getPath()+ftpFile.getName()).error(R.drawable.ic_image).into(this.img);
                                this.img.setVisibility(View.VISIBLE);

            }else{
                this.img.setVisibility(View.GONE);

            }
        }
    }
}
