package com.twistedequations.fragmentstutorail;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ElementsDetailsActivity extends FragmentActivity
{
	@Override
	protected void onCreate(Bundle arg0)
	{
		setContentView(R.layout.elements_details_activity);
		super.onCreate(arg0);
		
		if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			finish();
		}
		Intent intent = this.getIntent();
		Bundle element = intent.getBundleExtra("bundle");
		
		Fragment ElementDetails = new ElementsDetailsFragment();
		ElementDetails.setArguments(element);
		
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.elementDetailsFrame, ElementDetails);
		transaction.commit();
		
	}
}
