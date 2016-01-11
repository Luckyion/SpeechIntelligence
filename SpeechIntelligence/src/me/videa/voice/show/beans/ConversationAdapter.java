package me.videa.voice.show.beans;

import java.util.List;

import me.videa.voice.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConversationAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<String> mConversation;
	
	public ConversationAdapter(Context context, List<String> conversations) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		mConversation = conversations;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mConversation.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mConversation.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ConversationHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ConversationHolder();
			LayoutInflater mInflater = LayoutInflater.from(mContext);
			convertView = mInflater.inflate(R.layout.activity_conversation_content, parent, false);
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.conversation_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ConversationHolder) convertView.getTag();
		}
		viewHolder.mTextView.setText(mConversation.get(position));
		return convertView;
	}
	
	class ConversationHolder{
		TextView mTextView;
	}

}
