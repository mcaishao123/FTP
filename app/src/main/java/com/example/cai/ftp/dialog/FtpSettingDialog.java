package com.example.cai.ftp.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.cai.ftp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FtpSettingDialog extends AlertDialog {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.server_edt)
    TextInputEditText serverEdt;
    @BindView(R.id.port_edt)
    TextInputEditText portEdt;
    @BindView(R.id.username_edt)
    TextInputEditText usernameEdt;
    @BindView(R.id.password_edt)
    TextInputEditText passwordEdt;
    @BindView(R.id.cancel_btn)
    Button cancelBtn;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;

    public FtpSettingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getWindow() != null) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        }
        setContentView(R.layout.dialog_ftp_setting);
        ButterKnife.bind(this);
    }

    @Override
    public void dismiss() {
//        KeyboardHelper.closeKeyboardOnDialog(titleTv);
        super.dismiss();
    }

    @OnClick({R.id.cancel_btn, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.confirm_btn:
                dismiss();
                break;
        }
    }
}
