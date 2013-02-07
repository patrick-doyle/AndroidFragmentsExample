package com.twistedequations.fragmentstutorail;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ElementsListFragment extends Fragment implements OnItemClickListener
{
	String tag = this.getClass().getSimpleName();
	ListView elementsList;
	ArrayAdapter<String> adapter;
	Context context;
	ElementsListClickHandler handler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		Log.i(tag, "onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) 
	{		
		super.onAttach(activity);
		//Test if the activity impelments the method to handle the clicks
		try
		{
			handler = (ElementsListClickHandler) getActivity();
		}
		catch (ClassCastException e)
		{
			Log.i(tag,"Acivity " + getActivity().getClass().getSimpleName()+ 
					" does not impelment the ElementsListClickHandler interface");
		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		context = getActivity();
		View view;		
		view = inflater.inflate(R.layout.elements_list_fragment, container, false);
		
		String[] elements = context.getResources().getStringArray(R.array.ElementsArray);
		
		elementsList = (ListView) view.findViewById(R.id.elementsList);
		adapter = new ArrayAdapter<String>(context, R.layout.elements_row, R.id.elementsListText, elements);
		
		elementsList.setAdapter(adapter);
		elementsList.setOnItemClickListener(this);
		return view;
	}
	
	public interface ElementsListClickHandler
	{
		public void onHandleElementClick(int position);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) 
	{
		handler.onHandleElementClick(position);		
	}
}
