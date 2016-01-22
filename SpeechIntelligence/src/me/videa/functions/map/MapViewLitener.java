package me.videa.functions.map;

import android.view.Menu;
import android.view.MenuItem;

public interface MapViewLitener {
	
	public void onCreateOptionsMenu(Menu menu);
	
	public void onOptionsItemSelected(MenuItem item);
	
	public void onResume();
    
	public void onPause();  

	public void onDestroy();

}
