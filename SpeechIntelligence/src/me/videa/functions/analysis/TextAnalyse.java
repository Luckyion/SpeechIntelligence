package me.videa.functions.analysis;

import java.util.List;

import android.content.Context;


public abstract class TextAnalyse extends AbstractAnalyse{
	
	private Context mContext;
	
	public TextAnalyse(Context context, Encrypt encrypt){
		super(encrypt);
		this.mContext = context;
	}

	@Override
	public List<String> analyse(String sentence, String password,
			boolean isFuzzy) {
		// TODO Auto-generated method stub
		return null;
	}

}
