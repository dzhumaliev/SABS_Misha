package kg.sunrise.sabs.utils;
import android.content.Context;
import android.content.SharedPreferences;


public class SpinnerPreference {
    private static final String ACCESS_TOKEN = "";
    private static final String SETTINGS_STORAGE_NAME = "ds.credo";

    public static String getString(Context context, String key, String defValue) {
        return getPrefs(context).getString(key, defValue);
    }
    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE);
    }
    private static SharedPreferences.Editor getEditor(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE).edit();
    }
    public static String getAccessToken(Context context) {
        return getString(context, ACCESS_TOKEN, "");
    }
    public static void setAccessToken(Context context, String token) {
        getEditor(context).putString(ACCESS_TOKEN, token).commit();
    }

}
