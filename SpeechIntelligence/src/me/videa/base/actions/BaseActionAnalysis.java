package me.videa.base.actions;

import java.lang.reflect.Field;

import android.content.Context;
import me.videa.base.functions.Contacts;
import me.videa.base.functions.MakeCall;
import me.videa.base.functions.RecAnswerPhone;
import me.videa.base.functions.SendMessage;
import me.videa.utils.ReflectUtil;

public class BaseActionAnalysis {
	
	public Context mContext;
	
	public BaseActionAnalysis(Context context) {
		this.mContext = context;
	}
	
	public void AnalyseAction(String word){
		String[] acitons = getAction(word); 
		String actionStr = acitons[0];
		String obj = acitons[1];
		Class clazz = BaseDo.class;
		String [] args = null;
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			try {
				System.out.println(field.getName());
				Object object = ReflectUtil.getStaticProperty(clazz.getName(), field.getName());
				if(actionStr.equals((String)object)){
					execAction(actionStr, obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 执行动作
	 * @param action
	 * @param obj
	 */
	private void execAction(String action, String obj){
		
	}
	
	
	/**
	 * 获取基本动作分析
	 * @param word
	 * @return
	 */
	private String[] getAction(String word){		
		String [] args = null;
		Class clazz = BaseAction.class;
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			System.out.println(field.getName());
			try {
				String object = (String) ReflectUtil.getStaticProperty(clazz.getName(), field.getName());
				if(word.contains(object)){
					args = word.split(object);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return args;		
	}

}
