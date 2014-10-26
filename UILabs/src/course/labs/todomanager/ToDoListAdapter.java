package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the ToDoItems at pos positions

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem) getItem(position);
		
		ViewHolder holder;
		
		if (convertView == null)
		{
			// Inflate the View for this ToDoItem from todo_item.xml
			LayoutInflater li = LayoutInflater.from(mContext);
			RelativeLayout itemLayout = (RelativeLayout) li.inflate(R.layout.todo_item, parent, false);
			
			// Set the holder
			holder = new ViewHolder();
			holder.title = (TextView) itemLayout.findViewById(R.id.titleView);
			holder.status = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
			holder.priority = (TextView) itemLayout.findViewById(R.id.priorityView);
			holder.date = (TextView) itemLayout.findViewById(R.id.dateView);
			
			convertView = itemLayout;
			convertView.setTag(holder);
		}
		else
			holder = (ViewHolder) convertView.getTag();

		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View corresponds to the user interface elements defined in the layout file

		// Display Title in TextView
		final TextView titleView = holder.title;
		titleView.setText(toDoItem.getTitle());

		// Set up Status CheckBox
		final CheckBox statusView = holder.status;
		statusView.setChecked(toDoItem.getStatus().equals(ToDoItem.Status.DONE));

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
				Log.i(TAG, "Entered onCheckedChanged()");

				// set up an OnCheckedChangeListener, which is called when the user toggles the status checkbox
				if (isChecked)
					toDoItem.setStatus(ToDoItem.Status.DONE);
				else
					toDoItem.setStatus(ToDoItem.Status.NOTDONE);
			}
		});

		// Display Priority in a TextView
		final TextView priorityView = holder.priority;
		priorityView.setText(toDoItem.getPriority().toString());

		// Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and time String

		final TextView dateView = holder.date;
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		// Return the View you just created
		return convertView;
	}
	
	static class ViewHolder 
	{
		TextView title;
		CheckBox status;
		TextView priority;
		TextView date;
	}
}
