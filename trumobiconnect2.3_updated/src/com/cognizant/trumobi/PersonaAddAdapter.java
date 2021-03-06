/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cognizant.trumobi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.TruBoxSDK.SharedPreferences;
import com.cognizant.trumobi.R;
import com.cognizant.trumobi.em.Email;
import com.cognizant.trumobi.externaladapter.ExternalAdapterRegistrationClass;
import com.cognizant.trumobi.log.PersonaLog;
import com.cognizant.trumobi.persona.PersonaMainActivity;
/**
 * Adapter showing the types of items that can be added to a {@link PersonaWorkspace}.
 */
/**
 * 
 * KEYCODE					AUTHOR					PURPOSE
 * PIMSettings				290661					
 */
public class PersonaAddAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    
    private final ArrayList<ListItem> mItems = new ArrayList<ListItem>();
    
/*    public static final int ITEM_LAUNCHER_ACTION = 0;
   
*/
    public static final int ITEM_APPWIDGET = 0;
    public static final int ITEM_SHORTCUT = 1;
    private ExternalAdapterRegistrationClass mExtAdapReg;
 //   public static final int ITEM_ANYCUT = 2;
  /*  public static final int ITEM_LIVE_FOLDER = 4;
    public static final int ITEM_WALLPAPER = 5;*/
    private Typeface themeFont=null;
    /**
     * Specific item in our list.
     */
    public class ListItem {
        public final CharSequence text;
        public final Drawable image;
        public final int actionTag;
        
        public ListItem(Resources res, int textResourceId, int imageResourceId, int actionTag) {
            text = res.getString(textResourceId);
            if (imageResourceId != -1) {
                image = res.getDrawable(imageResourceId);
            } else {
                image = null;
            }
            this.actionTag = actionTag;
        }
    }
    
    public PersonaAddAdapter(PersonaLauncher personaLauncher) {
        super();
        themeFont=personaLauncher.getThemeFont();
        mInflater = (LayoutInflater) personaLauncher.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
        // Create default actions
        Resources res = personaLauncher.getResources();
/*        
        mItems.add(new ListItem(res, R.string.launcher_actions,
                R.drawable.all_apps_button, ITEM_LAUNCHER_ACTION));
        
   ;

 ;*/
    	// PIMSettings
        boolean showWidgets = true;
        boolean showShortcuts = true;
        if(PersonaMainActivity.isRovaPoliciesOn) {
        mExtAdapReg = ExternalAdapterRegistrationClass.getInstance(Email.getAppContext());
        showWidgets = mExtAdapReg.getExternalPIMSettingsInfo().bWidgets;
        showShortcuts = mExtAdapReg.getExternalPIMSettingsInfo().bShortcuts;
        }
        if(showWidgets)
        mItems.add(new ListItem(res, R.string.group_widgets,
                R.drawable.pr_ic_launcher_appwidget, ITEM_APPWIDGET));
        if(showShortcuts)
        mItems.add(new ListItem(res, R.string.group_shortcuts,
                R.drawable.pr_ic_launcher_shortcut, ITEM_SHORTCUT));
       /*
        mItems.add(new ListItem(res, R.string.pref_label_shirtcuts,
                R.drawable.pr_ic_launcher_shortcut, ITEM_ANYCUT));*/
    /*    mItems.add(new ListItem(res, R.string.group_live_folders,
                R.drawable.ic_launcher_add_folder, ITEM_LIVE_FOLDER));
        
        mItems.add(new ListItem(res, R.string.group_wallpapers,
                R.drawable.ic_launcher_wallpaper, ITEM_WALLPAPER));*/

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = (ListItem) getItem(position);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.pr_add_list_item, parent, false);
        }
        
        TextView textView = (TextView) convertView;
        textView.setTag(item);
        textView.setText(item.text);
        textView.setCompoundDrawablesWithIntrinsicBounds(item.image, null, null, null);
        if(themeFont!=null)textView.setTypeface(themeFont);
        return convertView;
    }

    public int getCount() {
    	PersonaLog.d("PersonaAddAdapter", "count : " + mItems.size());
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
}
