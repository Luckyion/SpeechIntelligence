package me.videa.functions.map;

import java.util.ArrayList;
import java.util.List;

import me.videa.voice.R;

import org.json.JSONObject;

import com.baidu.mapapi.search.core.PoiInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ResultAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	List<PoiInfo> mPoiInfos;
	private int checkId = 0;

	public ResultAdapter(Context context, List<PoiInfo> params) {
		// TODO Auto-generated constructor stub
		this.inflater = LayoutInflater.from(context);
		this.mPoiInfos = params;
	}
	public void setCheckId(int checkId) {
		// TODO Auto-generated method stub
		this.checkId = checkId;
	}

	public int getCheckId() {
		return checkId;
	}

	@Override
	public int getCount() {
		return mPoiInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return mPoiInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.lst_item, null,
					false);
			viewHolder.id = (TextView) convertView.findViewById(R.id.id);
			viewHolder.id1 = (TextView) convertView.findViewById(R.id.id1);
			viewHolder.id2 = (TextView) convertView.findViewById(R.id.id2);
//			viewHolder.id3 = (TextView) convertView.findViewById(R.id.id3);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.id.setText(mPoiInfos.get(position).name);
		viewHolder.id1.setText(mPoiInfos.get(position).city);
		viewHolder.id2.setText(mPoiInfos.get(position).address);
		return convertView;
	}

	class ViewHolder {
		TextView id, id1, id2;
	}
}
