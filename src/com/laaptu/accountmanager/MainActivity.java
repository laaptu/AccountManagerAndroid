package com.laaptu.accountmanager;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txtInfo;
	AccountManager accountManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtInfo = (EditText) findViewById(R.id.txt_info);
		accountManager = AccountManager.get(this);
	}

	public void addAccount(View view) {
		accountManager.addAccount(AccountData.ACCOUNT_TYPE,
				AccountData.ACCOUNT_AUTH_TYPE, null, null, this,
				new AccountManagerCallback<Bundle>() {

					@Override
					public void run(AccountManagerFuture<Bundle> future) {
						// TODO Auto-generated method stub
						try {
							Bundle bnd = future.getResult();

							Toast.makeText(MainActivity.this,
									"Account was created", Toast.LENGTH_LONG)
									.show();
							;
							Log.d("laaptu", "AddNewAccount Bundle is " + bnd);

						} catch (Exception e) {
							e.printStackTrace();
							// showMessage(e.getMessage());
							Log.e("Error creating", "Error Creating Account");
						}

					}
				}, null);

	}

	public void getAccount(View view) {

	}

}
