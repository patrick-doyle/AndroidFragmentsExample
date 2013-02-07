package com.twistedequations.fragmentstutorail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity implements ElementsListFragment.ElementsListClickHandler 
{
	String tag = this.getClass().getSimpleName();
	boolean dualPane;
	FrameLayout infoFrame;
	int lastPosition = -1;
	Bundle element = new Bundle();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		infoFrame = (FrameLayout)findViewById(R.id.infoFrame);
		//Test if the framelayout is there for the second fragment
		dualPane = (infoFrame!=null && infoFrame.getVisibility()==View.VISIBLE);
		
		if(savedInstanceState!=null)
		{
			int restoredPosition = savedInstanceState.getInt("position");
			
			if(restoredPosition!=-1)
			{
				onHandleElementClick(restoredPosition);
			}
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main_activity, menu);
		return true;
	}

	@Override
	public void onHandleElementClick(int position)
	{
		this.lastPosition = position;
		Log.i(tag, "Clicked at Position "+position );		
		element.putInt("position", position);
		if(dualPane)//if the frame is there for the fragment then load the fragment into it
		{	
			Fragment ElementDetails = new ElementsDetailsFragment();
			ElementDetails.setArguments(element);
			
			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.infoFrame, ElementDetails);
			transaction.addToBackStack(null);
			transaction.commit();
		}
		else//otherwise start a new activity for the fragment
		{
			Intent intent = new Intent(this, ElementsDetailsActivity.class);
			intent.putExtra("bundle", element);
			startActivity(intent);
		}
	}

	//Save the position of the last element
	@Override
	protected void onSaveInstanceState(Bundle outState) 
	{	
		outState.putInt("position", lastPosition);	
		super.onSaveInstanceState(outState);
	}
	
	

}
