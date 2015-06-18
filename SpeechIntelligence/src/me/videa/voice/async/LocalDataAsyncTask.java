package me.videa.voice.async;

import me.videa.base.functions.Contacts;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class LocalDataAsyncTask extends AsyncTask<String, String, String>{
	
	private Context mContext;
	
	public <T> LocalDataAsyncTask(Context context, T param){
		this.mContext = context;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		//测试读取联系人信息
//		String result = new Contacts(mContext).getContacts();
		try {
//			result = new Contacts(mContext).searchContactNameByNumber("02536598622");
//			result = new Contacts(mContext).searchContactNumberByName("测试");
			new Contacts(mContext).addContact("唐会旗", "18905166144", "123@126.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onCancelled(String result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if(result == null){
			Toast.makeText(mContext, "获取联系人失败", Toast.LENGTH_LONG).show();
		}		
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	

}
