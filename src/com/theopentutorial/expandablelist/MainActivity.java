package com.theopentutorial.expandablelist;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.theopentutorial.expandablelist.adapters.ExpandableListAdapter;

public class MainActivity extends Activity {

	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> laptopCollection;
	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		createGroupList();

		createCollection();

		expListView = (ExpandableListView) findViewById(R.id.laptop_list);
		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, laptopCollection);
		expListView.setAdapter(expListAdapter);

		//setGroupIndicatorToRight();

		expListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final String selected = (String) expListAdapter.getChild(
						groupPosition, childPosition);
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
						.show();

				return true;
			}
		});
	}

	private void createGroupList() {
		groupList = new ArrayList<String>();
		groupList.add("hp");
		groupList.add("hcl");
		groupList.add("lenovo");
		groupList.add("sony");
		groupList.add("dell");
		groupList.add("samsung");
	}

	private void createCollection() {
		// preparing laptops collection(child)
		String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540",
				"HP Envy 4-1025TX" };
		String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
		String[] lenovoModels = { "Z Series", "Essential G Series",
				"ThinkPad X Series", "Ideapad Z Series" };
		String[] sonyModels = { "VAIO E Series", "VAIO Z Series",
				"VAIO S Series", "VAIO YB Series" };
		String[] dellModels = { "Inspiron", "Vostro", "XPS" };
		String[] samsungModels = { "NP Series", "Series 5", "SF Series" };

		laptopCollection = new LinkedHashMap<String, List<String>>();

		for (String laptop : groupList) {
			if (laptop.equals("hp")) {
				loadChild(hpModels);
			} else if (laptop.equals("dell"))
				loadChild(dellModels);
			else if (laptop.equals("sony"))
				loadChild(sonyModels);
			else if (laptop.equals("hcl"))
				loadChild(hclModels);
			else if (laptop.equals("samsung"))
				loadChild(samsungModels);
			else if (laptop.equals("lenovo"))
				loadChild(lenovoModels);

			laptopCollection.put(laptop, childList);
		}
	}

	private void loadChild(String[] laptopModels) {
		childList = new ArrayList<String>();
		for (String model : laptopModels)
			childList.add(model);
	}

	@SuppressWarnings("unused")
	private void setGroupIndicatorToRight() {
		/* Get the screen width */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;

		expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
				- getDipsFromPixel(5));
	}

	// Convert pixel to dip
	public int getDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
