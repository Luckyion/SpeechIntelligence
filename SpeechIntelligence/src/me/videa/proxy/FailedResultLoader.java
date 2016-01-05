package me.videa.proxy;

import android.content.Context;
import android.os.AsyncTask;

public class FailedResultLoader extends AsyncTask<Void, Integer, Void>{
	Context mContext;
	
	public FailedResultLoader(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		new ProxyDataLoader(mContext).add();
		return null;
	}

}
