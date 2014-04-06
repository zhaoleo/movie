package com.leo.movie.thread;

import android.os.AsyncTask;
import android.util.Log;

public class TransMovie extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Log.d("HttpExample", arg0[0]);
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
