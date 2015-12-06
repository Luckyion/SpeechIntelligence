package me.videa.voice.show;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class VoiceMessageShowAdapter extends BaseAdapter{
	
	private List<String> mHistoryWords;
	private Context mContext;
	
	public VoiceMessageShowAdapter(Context context, List<String> words){
		this.mHistoryWords = words;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mHistoryWords.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mHistoryWords.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
