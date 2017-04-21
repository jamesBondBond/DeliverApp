package com.example.anshul.deliverapp.utility;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.services.GPSTracker;
import com.example.anshul.deliverapp.services.GpsScheduler;
import com.example.anshul.deliverapp.services.GpsService;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by GJS280 on 10/4/2015.
 */
public class Utility {

    private static  ProgressDialog progressDialog;

//    private static AmazonS3Client sS3Client;
//    private static CognitoCachingCredentialsProvider sCredentialProvider;
//    private static TransferUtility sTransferUtility;

    /**
     * Amazon
     * Gets an instance of CognitoCachingCredentialsProvider which is
     * constructed using the given Context.
     *
     * @param context An Context instance.
     * @return A default credential provider.
     */
//    private static CognitoCachingCredentialsProvider getCredentialProvider(Context context) {
//        if (sCredentialProvider == null) {
//            sCredentialProvider = new CognitoCachingCredentialsProvider(
//                    context.getApplicationContext(),
//                    Constants.COGNITO_POOL_ID,
//                    Regions.US_EAST_1);
//        }
//
//        return sCredentialProvider;
//    }

    /**
     * Amazon
     * Gets an instance of a S3 client which is constructed using the given
     * Context.
     *
     * @param context An Context instance.
     * @return A default S3 client.
     */
//    public static AmazonS3Client getS3Client(Context context) {
//        if (sS3Client == null) {
//            sS3Client = new AmazonS3Client(getCredentialProvider(context.getApplicationContext()));
//            // Set the region of your S3 bucket
//            sS3Client.setRegion(Region.getRegion(Regions.US_EAST_1));
//        }
//        return sS3Client;
//    }

    /**
     * Amazon
     * Gets an instance of the TransferUtility which is constructed using the
     * given Context
     *
     * @param context
     * @return a TransferUtility instance
     */
//    public static TransferUtility getTransferUtility(Context context) {
//        if (sTransferUtility == null) {
//            sTransferUtility = new TransferUtility(getS3Client(context.getApplicationContext()),
//                    context.getApplicationContext());
//        }
//
//        return sTransferUtility;
//    }

    /**
     * Save data in shared preferences
     * @param mContext
     * @param key
     * @param data
     */
    public static void saveToSharedPrefs(Context mContext, String key, String data) {
        final String PREFS_NAME = "com.gojavas.taskforce.preferences";
        final SharedPreferences taskForceData = mContext.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = taskForceData.edit();
        editor.putString(key, data);
        editor.commit();
    }


    public static String getDateFromMilliseconds(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy HH:mm:ss", cal).toString();
        return date;
    }

    /**
     * Get data from shared preferences
     * @param mContext
     * @param key
     * @return saved value
     */
    public static String getFromSharedPrefs(Context mContext, String key) {
        final String PREFS_NAME = "com.gojavas.taskforce.preferences";
        final SharedPreferences taskForceData = mContext.getSharedPreferences(PREFS_NAME, 0);
        final String preData = taskForceData.getString(key, "");
        return preData;
    }

    /**
     * Delete data from shared preference based on key
     * @param mContext
     * @param key
     */

    public static void deleteFromSharedPrefs(Context mContext, String key){
        final String PREFS_NAME = "com.gojavas.taskforce.preferences";
        final SharedPreferences taskForceData = mContext.getSharedPreferences(PREFS_NAME, 0);
        taskForceData.edit().remove(key).commit();
    }


    /**
     * Hide keyboard
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * Get device id
     * @return
     */
    public static String getDeviceId() {
        Context context = AppController.getInstance();
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * Get screen width
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);
        return screenWidth;
    }

    /**
     * Get screen metrics width
     * @param activity
     * @return
     */
    public static int getMetricsWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.widthPixels;
    }

    /**
     * Get screen height
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        return screenHeight;
    }

    /**
     * Get screen metrics height
     * @param activity
     * @return
     */
    public static int getMetricsHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.heightPixels;
    }

    /**
     * Show toast message
     * @param mContext
     * @param string
     */
    public static void showToast(Context mContext, String string) {
        Toast toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 10, 100);
        toast.show();
    }

    /**
     * Show toast message
     * @param mContext
     * @param string
     */
    public static void showShortToast(Context mContext, String string) {
        Toast toast = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 10, 100);
        toast.show();
    }

    public static void showProgrss(String message,Context context) {

        progressDialog = new ProgressDialog(context);
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
        }
    }


    public static void hideProgrss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    /**
     * Show alert dialog
     * @param context
     * @param title
     * @param message
     */
    public static void showAlertDialog(final Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) context).finish();
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Show alert dialog
     * @param context
     * @param title
     * @param message
     */
    public static void showNormalAlertDialog(final Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static String getApplicationPath(String directory) {
        return Environment.getExternalStorageDirectory() + "/GoDelivery/Current/" + directory;
    }

    public static String getApplicationBackupPath() {
        String path =  Environment.getExternalStorageDirectory() + "/GoDelivery/Backup/";
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * Check whether internet is connected or not
     * @param mContext
     * @return
     */
    public static boolean isInternetConnected(Context mContext) {
        final ConnectivityManager connection = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connection != null&& (connection.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)|| (connection.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        }
        return false;
    }

    public static HashMap<String,String> getLocationScheduler(Context context){

        GPSTracker gps = new GPSTracker(context);
        HashMap<String,String> map=new HashMap<>();

        // check if GPS enablred
        if(gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String network_type=gps.getNetworkType();


            map.put(Constants.LATITUDE, latitude + "");
            map.put(Constants.LONGITUDE,longitude+"");
            map.put(Constants.PROVIDER,network_type);

            // \n is for new line
            // Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
        return map;
    }

    /**
     * Scheduler for send tracking details
     *
     * @param context
     */
    public static void startTrackScheduler(Context context) {
        Intent intent = new Intent(context, GpsScheduler.class);
        PendingIntent pintent = PendingIntent.getService(context, Constants.ALARM_TRACK_KEY, intent, 0);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long repeatingTime = 1 * 60 * 1000; // 1 minute
//        long repeatingTime = 30*1000;
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), repeatingTime, pintent);
    }


    public static void cancelAlarm(Context context){

        Intent intent = new Intent(context, GpsScheduler.class);
        PendingIntent pendingIntent = PendingIntent.getService(context,Constants.ALARM_TRACK_KEY, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }
    /**
     * Extract the sqlite database from device
     * @param context
     */
    public static void extractDatabaseFromDevice(Context context) {
        File sd = Environment.getExternalStorageDirectory();
        String DB_PATH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DB_PATH = context.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator;
        }
        else {
            DB_PATH = context.getFilesDir().getPath() + context.getPackageName() + "/databases/";
        }
        if (sd.canWrite()) {
            //TaskForce DB
            String currentDBPath = "TaskForce.db";
            String backupDBPath = "TaskForce_backup.db";
            File currentDB = new File(DB_PATH, currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists()) {
                try {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


        String PATH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            PATH = context.getFilesDir().getAbsolutePath() + File.separator;
        }
        else {
            PATH = context.getFilesDir().getPath() + context.getPackageName() + "/files/";
        }
        if (sd.canWrite()) {
            //TaskForce DB
            String currentPath = "Design.txt";
            String backupPath = "Design_backup.txt";
            File current = new File(PATH, currentPath);
            File backup = new File(sd, backupPath);

            if (current.exists()) {
                try {
                    FileChannel src = new FileInputStream(current).getChannel();
                    FileChannel dst = new FileOutputStream(backup).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }






    // Check dates are equal or not
    public static boolean areEqual(long logintime, long currentTimeMillis) {
        try {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(new Date(logintime));

            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date(currentTimeMillis));

            return ((c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
                    &&
                    (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Copy bitmap to a location
     * @param bitmap
     * @param destinationPath
     * @param fileName
     * @return
     */
    public static boolean copyBitmap(Bitmap bitmap, String destinationPath, String fileName) {
        boolean isok = true;
        FileOutputStream fos = null;
        File dir = new File(destinationPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName);
        if (file.exists ()) file.delete ();
        try {
            fos = new FileOutputStream(file);
            bitmap.setHasAlpha(true);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            isok = false;
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                isok = false;
                e.printStackTrace();
            }
        }
        return isok;
    }

    /**
     * Send sms to customer
     * @param phoneNo
     * @param sms
     * @param mContext
     */
    public static void sendSms(String phoneNo, String sms, Context mContext) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(sms);
        smsManager.sendMultipartTextMessage(phoneNo, null, parts, null, null);

//        CallLogEntity callLogEntity = new CallLogEntity();
//        callLogEntity.setusername(Utility.getFromSharedPrefs(mContext, Constants.USERNAME_KEY));
//        callLogEntity.setname(phoneNo);
//        callLogEntity.setnumber(phoneNo);
//        callLogEntity.setduration("0");
//        callLogEntity.setsync("0");
//        callLogEntity.settype(Constants.PROFESSIONAL);
//        callLogEntity.setdate(System.currentTimeMillis() + "");
//        callLogEntity.setcall_sms(Constants.SMS);
//
//        CallLogHelper.getInstance().insertCallLog(callLogEntity);
    }


    /**
     * Show dialog
     */
    public static void showDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }






    /**
     * Get the actual bitmap from path
     * @return
     */
    public static Bitmap getBitmap() {
        File image = new File("/storage/emulated/0/DCIM/Screenshots/Screenshot_2015-05-21-01-39-53.png");
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
        return  bitmap;
    }

    public static void saveBitmap() {
        Bitmap bitmap = getBitmap();
        FileOutputStream fos = null;
        try {
            fos = AppController.getInstance().openFileOutput("screenshot.png", Context.MODE_PRIVATE);
//            fos.write(jsonObject.toString().getBytes());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getBitmapFromData() {
        File sd = Environment.getExternalStorageDirectory();
        Context context = AppController.getInstance();
        String PATH;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            PATH = context.getFilesDir().getAbsolutePath() + File.separator;
        }
        else {
            PATH = context.getFilesDir().getPath() + context.getPackageName() + "/files/";
        }
        if (sd.canWrite()) {
            //TaskForce DB
            String currentPath = "screenshot.png";
            String backupPath = "screenshot_backup.png";
            File current = new File(PATH, currentPath);
            File backup = new File(sd, backupPath);

            if (current.exists()) {
                try {
                    FileChannel src = new FileInputStream(current).getChannel();
                    FileChannel dst = new FileOutputStream(backup).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Decode bitmap and scale it to required size
     * @param file
     * @return
     */
    public static Bitmap decodeFile(File file) {
        Context context = AppController.getInstance();
        int mSize = getScreenWidth(context) / 2;
        try {
            // Decode image size
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = options.outWidth, height_tmp = options.outHeight;
            int scale = 1;
            while(true) {
                if(width_tmp/2 < mSize || height_tmp/2 < mSize)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get file name from its path
     * @param filePath
     * @return
     */
    public static String getFileNameFromPath(String filePath) {
        String name = "";
        if(filePath.contains("/")) {
            name = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
        }
        return name;
    }



    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    /**
     * Get scaled bitmap from bytes array
     * @param data
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap getScaledBitmap(byte[] data, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * Get scaled bitmap from file path
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap getScaledBitmap(String path, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * Calculate bitmap insample size
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }


    // get current date and time for free recharge
    public static String getDateForFR(){

        // get current date and time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        String strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: "+strDate);

        return strDate;

    }


    public  static boolean   getDaysDiff(Context context){
        Calendar today = Calendar.getInstance();
        long difference = today.getTimeInMillis() - PreferenceManager.getLongInPref(context,AppConstants.LOGIN_TIME,0);
        int days = (int) (difference/ (1000*60*60*24));

        if (days>10){
            PreferenceManager.saveStringInPref(context,AppConstants.App_CLOSE,"true");

            return true;
        }

        return false;
    }
    public static  void getLocationOnOff(final Context context){
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("GPS Network Not Enabled");
            dialog.setPositiveButton("Open Setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    ((Activity)context).finish();
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    ((Activity)context).finish();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
        }

    }
}
