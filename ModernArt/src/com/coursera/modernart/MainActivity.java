package com.coursera.modernart;

import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Get the non white/non grey rectangles
		TextView rec1 = (TextView) findViewById(R.id.idRec1);
		TextView rec2 = (TextView) findViewById(R.id.idRec2);
		TextView rec3 = (TextView) findViewById(R.id.idRec3);
		TextView rec4 = (TextView) findViewById(R.id.idRec4);
		TextView rec5 = (TextView) findViewById(R.id.idRec5);
		TextView rec6 = (TextView) findViewById(R.id.idRec6);
		
		// Get the rectangles initial colors
		ColorDrawable color1 = (ColorDrawable) rec1.getBackground();
		ColorDrawable color2 = (ColorDrawable) rec2.getBackground();
		ColorDrawable color3 = (ColorDrawable) rec3.getBackground();
		ColorDrawable color4 = (ColorDrawable) rec4.getBackground();
		ColorDrawable color5 = (ColorDrawable) rec5.getBackground();
		ColorDrawable color6 = (ColorDrawable) rec6.getBackground();
		
		final int baseColorInt1 = color1.getColor();
		final int baseColorInt2 = color2.getColor();
		final int baseColorInt3 = color3.getColor();
		final int baseColorInt4 = color4.getColor();
		final int baseColorInt5 = color5.getColor();
		final int baseColorInt6 = color6.getColor();
		
		final TextView[] widgets = {rec1, rec2, rec3, rec4, rec5, rec6};
		final int[] baseColors = {baseColorInt1, baseColorInt2, baseColorInt3, baseColorInt4, baseColorInt5, baseColorInt6};
		
		final Random ran = new Random();
		
		// Get the sliderWidget
		SeekBar seekBar = (SeekBar) findViewById(R.id.SliderID);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{
				for (int i = 0; i < widgets.length; i++)
				{
					// Get the RGB components
					int Rcolor = Color.red(baseColors[i]);
					int Gcolor = Color.green(baseColors[i]);
					int Bcolor = Color.blue(baseColors[i]);
					
					if (!(baseColors[i] == Color.WHITE || (Rcolor == 128 && Gcolor == 128 && Bcolor == 128)))
					{
						final int randomRColor = ran.nextInt(255);
						final int randomGColor = ran.nextInt(255);
						final int randomBColor = ran.nextInt(255);
						
						// 230 is to avoid non white/grey rectangles to become white
						int newRcolor = Rcolor + ((randomRColor - Rcolor) * progress / 50);
						int newGcolor = Gcolor + ((randomGColor - Gcolor) * progress / 50);
						int newBcolor = Bcolor + ((randomBColor - Bcolor) * progress / 50);
						
						widgets[i].setBackgroundColor(Color.rgb(newRcolor, newGcolor, newBcolor));
					}
				}
			}
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.more_info)
		{
			
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
			dialogBuilder.setTitle("Inspired by the work of artist such as Piet Mondrian and Ben Nicholson");
			dialogBuilder.setMessage("Click below to learn more!");
			dialogBuilder.setCancelable(true);
			
			dialogBuilder.setNegativeButton("Visit MOMA", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					String url = "http://www.moma.com";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			});
			
			dialogBuilder.setPositiveButton("Not now", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			
			AlertDialog dialog = dialogBuilder.create();
			dialog.show();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}