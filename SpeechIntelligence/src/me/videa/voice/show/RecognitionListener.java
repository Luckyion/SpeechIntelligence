package me.videa.voice.show;


public interface RecognitionListener {
	
	public void onInitialized();
	
	public void onError(String code);
	
	public void onResult(String results);
	
	public void onBeginOfSpeech();
	
	public void onVolumeChanged(int volume);
	
	public void onEndOfSpeech();

}
