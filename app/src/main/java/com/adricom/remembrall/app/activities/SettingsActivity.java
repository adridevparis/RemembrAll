package com.adricom.remembrall.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import com.adricom.remembrall.app.R;
import com.adricom.remembrall.app.adapters.SettingsAdapter;
import com.adricom.remembrall.app.enums.SettingsFieldEnum;
import com.adricom.remembrall.app.helpers.SettingsHelper;
import com.adricom.remembrall.app.models.SettingsItem;

import java.util.List;

public class SettingsActivity extends Activity {

    private List<SettingsItem> items;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.settings_coordinator_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setActionBar(toolbar);

        if (!SettingsHelper.IsInitialized())
            SettingsHelper.Init(this);

        final ListView listView = (ListView) findViewById(R.id.settings_list_view);

        items = SettingsHelper.Instance.getAllSettings();

        final SettingsAdapter adapter = new SettingsAdapter(this, coordinatorLayout, R.layout.settings_item, items);
        adapter.setIsItemEnabled(SettingsFieldEnum.DEFAULT_REMINDER_TIME.getIndex(), (boolean) SettingsHelper.Instance.getSetting(SettingsFieldEnum.USE_DEFAULT_REMINDER_TIME).Value);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        SettingsHelper.Instance.saveSettings();
        super.onDestroy();
    }
}