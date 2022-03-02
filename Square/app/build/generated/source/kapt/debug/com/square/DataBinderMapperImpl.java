package com.square;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.square.databinding.ActivityEmployeeListBindingImpl;
import com.square.databinding.IncludeProgressBarBindingImpl;
import com.square.databinding.ViewEmployeeListItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYEMPLOYEELIST = 1;

  private static final int LAYOUT_INCLUDEPROGRESSBAR = 2;

  private static final int LAYOUT_VIEWEMPLOYEELISTITEM = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.square.R.layout.activity_employee_list, LAYOUT_ACTIVITYEMPLOYEELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.square.R.layout.include_progress_bar, LAYOUT_INCLUDEPROGRESSBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.square.R.layout.view_employee_list_item, LAYOUT_VIEWEMPLOYEELISTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYEMPLOYEELIST: {
          if ("layout/activity_employee_list_0".equals(tag)) {
            return new ActivityEmployeeListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_employee_list is invalid. Received: " + tag);
        }
        case  LAYOUT_INCLUDEPROGRESSBAR: {
          if ("layout/include_progress_bar_0".equals(tag)) {
            return new IncludeProgressBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for include_progress_bar is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWEMPLOYEELISTITEM: {
          if ("layout/view_employee_list_item_0".equals(tag)) {
            return new ViewEmployeeListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_employee_list_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/activity_employee_list_0", com.square.R.layout.activity_employee_list);
      sKeys.put("layout/include_progress_bar_0", com.square.R.layout.include_progress_bar);
      sKeys.put("layout/view_employee_list_item_0", com.square.R.layout.view_employee_list_item);
    }
  }
}
