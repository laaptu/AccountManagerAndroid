package com.laaptu.accountmanager;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AccountAuthenticatorService extends Service {

	// its binder will be called anytime Account related task is to be called
	// it in turns call the AccountAuthenticator class to do its related task

	private static AccountAuthenticator accountAuthenticator;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		IBinder binder = null;
		if (intent.getAction().equals(
				AccountManager.ACTION_AUTHENTICATOR_INTENT))
			binder = getAccountAuthenticator().getIBinder();
		return binder;
	}

	private AccountAuthenticator getAccountAuthenticator() {
		if (accountAuthenticator == null)
			accountAuthenticator = new AccountAuthenticator(this);
		return accountAuthenticator;
	}

	public class AccountAuthenticator extends AbstractAccountAuthenticator {

		private final Context context;

		public AccountAuthenticator(Context context) {
			super(context);
			this.context = context;
		}

		@Override
		public Bundle editProperties(AccountAuthenticatorResponse response,
				String accountType) {
			// TODO Auto-generated method stub
			return null;
		}

		/*
		 * This method is called when the intent is to create a new account once
		 * this is called it will in return launch an Activity to process
		 * Account Creation Task
		 */
		@Override
		public Bundle addAccount(AccountAuthenticatorResponse response,
				String accountType, String authTokenType,
				String[] requiredFeatures, Bundle options)
				throws NetworkErrorException {
			Bundle reply = new Bundle();

			// ****
			Intent intent = new Intent(context, AccountActivity.class);
			// *****
			intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE,
					response);
			intent.putExtra(AccountData.ACCOUNT_TYPE, accountType);
			intent.putExtra(AccountData.ACCOUNT_AUTH_TYPE, authTokenType);
			intent.putExtra(AccountActivity.DO_CREATE_NEW_ACCOUNT, true);

			// return our AccountAuthenticatorActivity
			// ****
			reply.putParcelable(AccountManager.KEY_INTENT, intent);

			return reply;
		}

		@Override
		public Bundle confirmCredentials(AccountAuthenticatorResponse response,
				Account account, Bundle options) throws NetworkErrorException {
			return null;
		}

		@Override
		public Bundle getAuthToken(AccountAuthenticatorResponse response,
				Account account, String authTokenType, Bundle options)
				throws NetworkErrorException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getAuthTokenLabel(String authTokenType) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle updateCredentials(AccountAuthenticatorResponse response,
				Account account, String authTokenType, Bundle options)
				throws NetworkErrorException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle hasFeatures(AccountAuthenticatorResponse response,
				Account account, String[] features)
				throws NetworkErrorException {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
