package me.videa.voice.async.remote;

import java.util.List;
import java.util.Map;

public interface RemoteFileLoaderCallback {
	
	public void setResult(List<Map<String, String>> result, String path);

}
