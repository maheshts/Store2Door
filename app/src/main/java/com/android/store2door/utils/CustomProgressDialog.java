package com.android.store2door.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.android.store2door.R;


public class CustomProgressDialog extends ProgressDialog {
	public static final String DEBUG_TAG = "CustomProgressDialog";
	private String mMessage;

	public CustomProgressDialog(Context context, String message) {
		super(context, R.style.CustomAlertDialogStyle);
		this.mMessage = message;
		// TODO Auto-generated constructor stub
	}

	public CustomProgressDialog(Context context) {
		super(context, R.style.CustomAlertDialogStyle);
		this.mMessage = "Loading...";
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_loading);

		setCancelable(false);

		TextView progressMessage = findViewById(R.id.progressMessage);
		progressMessage.setText(mMessage);
	}
}
