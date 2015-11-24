package me.videa.functions.analysis;

import java.util.LinkedList;
import java.util.List;

import me.videa.base.db.entity.Action;
import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

public class AnalyseDbOp implements DbAnalysis{
	
	private Context mContext;
	
	public AnalyseDbOp() {
		// TODO Auto-generated constructor stub
	}
	
	public AnalyseDbOp(Context context){
		this.mContext = context;
	}

	@Override
	public List<Action> query(String compSentence, String password, boolean isFuzzy) {
		// TODO Auto-generated method stub
		if(isFuzzy){
			return queryFuzzy(compSentence, password);
		}
		return query(compSentence, password);
	}

	
	/**
	 * 严格方式查询数据
	 * @param compSentence
	 * @return 
	 */
	private List<Action> query(String compSentence, String password){
		DbUtils dbUtils = DbUtils.create(mContext);
		List<Action> actions = null;
		try {
			actions = dbUtils.findAll(Selector.from(Action.class)
					.where(WhereBuilder.b("SWMANTIC", "=", compSentence)
							.and("PASSWORD", "=", password)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actions;
	}
	/**
	 * 模糊方式查询数据
	 * @param compSentence
	 * @return
	 */
	private List<Action> queryFuzzy(String compSentence, String password){
		DbUtils dbUtils = DbUtils.create(mContext);
		List<Action> actions = null;
		try {
			actions = dbUtils.findAll(Selector.from(Action.class)
					.where(WhereBuilder.b("SWMANTIC", "like", "%" + compSentence + "%")
							.and("PASSWORD", "=", password)));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actions;
	}

	@Override
	public boolean save(Action action){
		boolean flag = true;
		DbUtils dbUtils = DbUtils.create(mContext);
		try {
			dbUtils.save(action);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			flag = false;
		}
		return flag;
	}

	@Override
	public LinkedList<Action> soft() {
		// TODO Auto-generated method stub
		return null;
	}

}
