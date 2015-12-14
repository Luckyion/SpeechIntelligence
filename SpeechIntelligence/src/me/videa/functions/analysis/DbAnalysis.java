package me.videa.functions.analysis;

import java.util.LinkedList;
import java.util.List;

import me.videa.base.db.entity.Action;

public interface DbAnalysis {

	public List<Action> query(String compSentence1, String password, boolean isFuzzy);
	

	public LinkedList<Action> soft();

	public boolean save(Action action);

}

