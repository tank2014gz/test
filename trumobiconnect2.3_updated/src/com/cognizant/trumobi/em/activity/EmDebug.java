

package com.cognizant.trumobi.em.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.cognizant.trumobi.R;
import com.cognizant.trumobi.commonabstractclass.TruMobiBaseActivity;
import com.cognizant.trumobi.em.EmEmController;
import com.cognizant.trumobi.em.EmPreferences;
import com.cognizant.trumobi.em.Email;
import com.cognizant.trumobi.exchange.EmEas;
import com.cognizant.trumobi.exchange.utility.EmFileLogger;

public class EmDebug extends TruMobiBaseActivity implements OnCheckedChangeListener {
	private TextView mVersionView;
	private CheckBox mEnableDebugLoggingView;
	private CheckBox mEnableSensitiveLoggingView;
	private CheckBox mEnableExchangeLoggingView;
	private CheckBox mEnableExchangeFileLoggingView;

	private EmPreferences mPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.em_debug);

		mPreferences = EmPreferences.getPreferences(this);

		mVersionView = (TextView) findViewById(R.id.version);
		mEnableDebugLoggingView = (CheckBox) findViewById(R.id.debug_logging);
		mEnableSensitiveLoggingView = (CheckBox) findViewById(R.id.sensitive_logging);

		mVersionView.setText(String.format(
				getString(R.string.debug_version_fmt).toString(),
				getString(R.string.build_number)));

		mEnableDebugLoggingView.setChecked(Email.DEBUG);
		mEnableSensitiveLoggingView.setChecked(Email.DEBUG_SENSITIVE);

		// EXCHANGE-REMOVE-SECTION-START
		mEnableExchangeLoggingView = (CheckBox) findViewById(R.id.exchange_logging);
		mEnableExchangeFileLoggingView = (CheckBox) findViewById(R.id.exchange_file_logging);
		mEnableExchangeLoggingView.setChecked(EmEas.PARSER_LOG);
		mEnableExchangeFileLoggingView.setChecked(EmEas.FILE_LOG);
		// EXCHANGE-REMOVE-SECTION-END

		// Note: To prevent recursion while presetting checkboxes, assign all
		// listeners last
		mEnableDebugLoggingView.setOnCheckedChangeListener(this);
		mEnableSensitiveLoggingView.setOnCheckedChangeListener(this);
		// EXCHANGE-REMOVE-SECTION-START
		mEnableExchangeLoggingView.setOnCheckedChangeListener(this);
		mEnableExchangeFileLoggingView.setOnCheckedChangeListener(this);
		// EXCHANGE-REMOVE-SECTION-END
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.debug_logging:
			Email.DEBUG = isChecked;
			mPreferences.setEnableDebugLogging(Email.DEBUG);
			break;
		case R.id.sensitive_logging:
			Email.DEBUG_SENSITIVE = isChecked;
			mPreferences.setEnableSensitiveLogging(Email.DEBUG_SENSITIVE);
			break;
		// EXCHANGE-REMOVE-SECTION-START
		case R.id.exchange_logging:
			mPreferences.setEnableExchangeLogging(isChecked);
			break;
		case R.id.exchange_file_logging:
			mPreferences.setEnableExchangeFileLogging(isChecked);
			if (!isChecked) {
				EmFileLogger.close();
			}
			break;
		// EXCHANGE-REMOVE-SECTION-END
		}

		updateLoggingFlags(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.dump_settings) {
			EmPreferences.getPreferences(this).dump();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.em_debug_option, menu);
		return true;
	}

	/**
	 * Load enabled debug flags from the preferences and upadte the EAS debug
	 * flag.
	 */
	public static void updateLoggingFlags(Context context) {
		// EXCHANGE-REMOVE-SECTION-START
		EmPreferences prefs = EmPreferences.getPreferences(context);
		int debugLogging = prefs.getEnableDebugLogging() ? EmEas.DEBUG_BIT : 0;
		int exchangeLogging = prefs.getEnableExchangeLogging() ? EmEas.DEBUG_EXCHANGE_BIT
				: 0;
		int fileLogging = prefs.getEnableExchangeFileLogging() ? EmEas.DEBUG_FILE_BIT
				: 0;
		int debugBits = debugLogging | exchangeLogging | fileLogging;
		EmEmController.getInstance(context).serviceLogging(debugBits);
		// EXCHANGE-REMOVE-SECTION-END
	}
}
