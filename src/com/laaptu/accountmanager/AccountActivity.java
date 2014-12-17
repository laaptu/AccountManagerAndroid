package com.laaptu.accountmanager;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;

public class AccountActivity extends AccountAuthenticatorActivity {

	public static final String DO_CREATE_NEW_ACCOUNT = "doCreateNewAccount";

	private AccountManager accountManager;

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		accountManager = AccountManager.get(this);
		if (getIntent().hasExtra(DO_CREATE_NEW_ACCOUNT)
				&& getIntent().getBooleanExtra(DO_CREATE_NEW_ACCOUNT, false)) {
			createNewAccount(getIntent());
			return;
		}

		this.finish();
	}

	private void createNewAccount(Intent intent) {
		// these intent values are to be returned back to notify that account
		// creation is success
		// ?? Don't know it is essentail to add the necessary data on return
		// params

		final Intent returnIntent = new Intent();
		returnIntent.putExtra(AccountManager.KEY_ACCOUNT_NAME,
				AccountData.ACCOUNT_NAME);
		// the following values must be same @ xml/account_authenticator/on
		// calling
		// to create account/ and on here
		returnIntent.putExtra(AccountManager.KEY_ACCOUNT_TYPE,
				AccountData.ACCOUNT_TYPE);
		returnIntent.putExtra(AccountManager.KEY_AUTHTOKEN,
				AccountData.ACCOUNT_AUTH);
		returnIntent.putExtra(AccountManager.KEY_PASSWORD,
				AccountData.ACCOUNT_PASSWORD);

		/* #### KEY PARTS TO CREATE AN ACCOUNT */
		// AccountData.ACCOUNT_TYPE==xml/account_authenticator/on calling create
		// account
		// Creating the account on the device and setting the auth token
		// we got
		// (Not setting the auth token will cause another call to the
		// server to authenticate the user)
		final Account account = new Account(AccountData.ACCOUNT_NAME,
				AccountData.ACCOUNT_TYPE);
		accountManager.addAccountExplicitly(account,
				AccountData.ACCOUNT_PASSWORD, null);
		accountManager.setAuthToken(account, AccountData.ACCOUNT_AUTH_TYPE,
				AccountData.ACCOUNT_AUTH);

		// this part is essential to indicate that account creation is success
		setAccountAuthenticatorResult(returnIntent.getExtras());

		setResult(RESULT_OK, returnIntent);
		finish();

	}

}
