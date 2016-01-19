package me.videa.functions.local;

import java.util.List;

import me.videa.voice.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter{
	
	List<FileLoaderBean> mFiles;
	private Context mContext;
	
	public FileAdapter(Context context, List<FileLoaderBean> files) {
		this.mContext = context;
		mFiles = files;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFiles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mFiles.get(position);
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
			convertView = mInflater.inflate(R.layout.activity_file_list_content, parent, false);
			viewHolder.mFilePath = (TextView) convertView.findViewById(R.id.file_path);
			viewHolder.mFileSize = (TextView) convertView.findViewById(R.id.file_size);
			viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.file_img);
			viewHolder.mCursor = (ImageView) convertView.findViewById(R.id.file_cursor);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ConversationHolder) convertView.getTag();
		}
		viewHolder.mFilePath.setText(mFiles.get(position).getFileName());
		String type = mFiles.get(position).getType();
		if(type.equals("0")){//文件
			viewHolder.mImageView.setImageResource(R.drawable.file_img);
//			viewHolder.mImageView.setBackgroundResource(R.drawable.file_img);
			viewHolder.mFileSize.setText(mFiles.get(position).getFileSize());
			viewHolder.mCursor.setVisibility(View.GONE);
		}
		if(type.equals("1")){
			viewHolder.mImageView.setImageResource(R.drawable.folder_img);
			viewHolder.mCursor.setVisibility(View.VISIBLE);
		}
		if(type.equals("2")){
			Bitmap mBitmap = mFiles.get(position).getBitmap();
			if(mBitmap != null){
				viewHolder.mImageView.setImageBitmap(mBitmap);
				viewHolder.mImageView.setTag(mBitmap.hashCode());
			}else{
				viewHolder.mImageView.setImageResource(R.drawable.chat_tool_paint);
			}			
			viewHolder.mFileSize.setText(mFiles.get(position).getFileSize());
			viewHolder.mCursor.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	class ConversationHolder{
		TextView mFilePath;
		ImageView mImageView;
		TextView mFileSize;
		ImageView mCursor;
	}
}
