package cordova.plugin.nativedatetimepicker;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.DatePicker;

import java.lang.Exception;
import java.util.Calendar;

/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaNativeDateTimePicker extends CordovaPlugin {
    private String selectedDate;
    private Activity activity;
    private FragmentManager fragmentManager;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("datetimePicker")) {
            try {
                activity = this.cordova.getActivity();
                fragmentManager = activity.getFragmentManager();

                String message = args.getString(0);
                this.datetimePickerMethod(message, callbackContext);
            } catch (Exception e) {
                this.datetimePickerMethod("", callbackContext);
            }

            return true;
        }
        return false;
    }

    private void datetimePickerMethod(String message, CallbackContext callbackContext) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = year + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + String.format("%02d", dayOfMonth);
                        callbackContext.success(selectedDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
