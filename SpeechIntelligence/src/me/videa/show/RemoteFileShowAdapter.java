package me.videa.show;

import java.util.List;
import java.util.Map;

import com.iflytek.voicedemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RemoteFileShowAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Map<String, String>> mList;
	LayoutInflater mInflater;
	
	public RemoteFileShowAdapter(Context context, List<Map<String, String>> content){
		this.mContext = context;
		this.mList = content;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return mList.get(location);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			mInflater = LayoutInflater.from(mContext);
			convertView = mInflater.inflate(R.layout.activity_remote_file_show_content, parent);
			holder.file_name = (TextView) convertView.findViewById(R.id.file_name);
			holder.file_size = (TextView) convertView.findViewById(R.id.file_size);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.file_name.setText(((Map<String, String>)mList.get(position)).get("file_name"));
		holder.file_size.setText(((Map<String, String>)mList.get(position)).get("file_size"));
		return convertView;
	}
	
	class ViewHolder{
		TextView file_name;
		TextView file_size;
	}

}
