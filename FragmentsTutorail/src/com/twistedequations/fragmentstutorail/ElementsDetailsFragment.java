package com.twistedequations.fragmentstutorail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ElementsDetailsFragment extends Fragment 
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view;		
		view = inflater.inflate(R.layout.elements_details_fragment, container, false);
		
		TextView text = (TextView)view.findViewById(R.id.detailsText);
		
		Bundle element = this.getArguments();
		int position = element.getInt("position");
		
		String[] elementDetails = getActivity().getResources().getStringArray(R.array.ElementsInfoArray);
		text.setText(elementDetails[position]);
		
		return view;
	}
	

}
