package com.professor.traficinspiration;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.activity.MainActivity;
import com.professor.traficinspiration.activity.UserInfoActivity;
import com.professor.traficinspiration.model.User;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ACCOUNT_SERVICE;
import static android.content.Context.ACTIVITY_SERVICE;
import static com.professor.traficinspiration.ApplicationContext.context;

public class TestUtils {

    public void printRunningServices(Activity context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        long currentMillis = Calendar.getInstance().getTimeInMillis();
        Calendar cal = Calendar.getInstance();
        final StringBuilder log = new StringBuilder();

        log.append("services count = ").append(services.size()).append("\n");

        String searchedService = "";

        long mostRecentService = 0;

        for (ActivityManager.RunningServiceInfo info : services) {
            cal.setTimeInMillis(currentMillis - info.activeSince);

            if (info.activeSince > mostRecentService) {
                searchedService = info.process;
                mostRecentService = info.activeSince;
            }

            log.append(String.format("%s - %d\n",
                    info.process, info.activeSince));

        }

        log.append("searchedService: ").append(searchedService).append(" - ").append(mostRecentService);

        String w = log.toString();

//        TextView textView = (TextView) context.findViewById(R.id.testView);
//        textView.setText(w);
    }

    public void showRunningTime(Activity context) {

        int pid = 0;

        String packageName = "com.professor.articledigest";

        ActivityManager manager = (ActivityManager) ApplicationContext.getContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo process: runningProcesses) {

            if (process.processName.equals(packageName)) {
                pid = process.pid;
            }
        }

        if (pid == 0) {
            return;
        }

        Toast.makeText(context, "pid = " + pid, Toast.LENGTH_LONG).show();

        String path = "/proc/" + pid + "/stat";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
        }
        String stat = null;
        try {
            stat = reader.readLine();

        } catch (IOException e) {
            Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(context, "stat = " + stat, Toast.LENGTH_LONG).show();

        final String field2End = ") ";
        final String fieldSep = " ";
        final int fieldStartTime = 20;
        final int msInSec = 1000;
        long startTime = 0;
        try {
            final String[] fields = stat.substring(stat.lastIndexOf(field2End)).split(fieldSep);
            final long t = Long.parseLong(fields[fieldStartTime]);
            Toast.makeText(context, "startTime = " + t, Toast.LENGTH_LONG).show();

//            final int tckName = Class.forName("libcore.io.OsConstants").getField("_SC_CLK_TCK").getInt(null);
//            final Object os = Class.forName("libcore.io.Libcore").getField("os").get(null);
//            final long tck = (Long)os.getClass().getMethod("sysconf", Integer.TYPE).invoke(os, tckName);
//            startTime = t * msInSec / tck;
            startTime = t;

            Toast.makeText(context, "startTime = " + startTime, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "error" + e, Toast.LENGTH_LONG).show();
        }

        final long dt = SystemClock.elapsedRealtime() - startTime;

        Toast.makeText(context, "duration = " + dt, Toast.LENGTH_LONG).show();
    }


    public void checkGoogleAccount() {
        // !!! необходимо дополнительно запрашивать доступ к аккаунтам !!!
        AccountManager manager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        Account[] list = manager.getAccounts();
        String gmail = null;

        for(Account account: list)
        {
            if(account.type.equalsIgnoreCase("com.google"))
            {
                gmail = account.name;
                break;
            }
        }
        Toast.makeText(context, gmail, Toast.LENGTH_LONG).show();

    }

    public void delay() {
        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            public void run() {
                Toast.makeText(context, "delayed running", Toast.LENGTH_LONG).show();
            }
        }, 2000);
    }

    public void createDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setTitle("Title");
        adb.setMessage("Message");
        adb.setPositiveButton("OK", null);
        Dialog dialog = adb.create();
    }

//    public void signInDialog() {
//
//        final Dialog myDialog = new Dialog(context);
//        myDialog.setContentView(R.layout.login_form);
//        myDialog.setCancelable(false);
//        myDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//
//
//        Button loginButton = (Button) myDialog.findViewById(R.id.loginButton);
//        Button registerButton = (Button) myDialog.findViewById(R.id.registerButton);
//
//        final EditText usernameEditText = (EditText) myDialog.findViewById(R.id.et_username);
//        final EditText passwordEditText = (EditText) myDialog.findViewById(R.id.et_password);
//        final EditText emailEditText = (EditText) myDialog.findViewById(R.id.et_email);
//        final EditText referrerEditText = (EditText) myDialog.findViewById(R.id.et_referrer);
//
//        myDialog.show();
//
//        registerButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String referrer = referrerEditText.getText().toString();
//
//                if (username.equals("") || password.equals("") || email.equals("")) {
//                    Toast.makeText(context, "Not all necessary fields filled", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                User user = new User();
//                user.setName(username);
//                user.setPassword(password);
//                user.setEmail(email);
//
//                ApplicationContext.getMessageService().getOrCreateUser(email, password, "login", 0L);
//
//                myDialog.dismiss();
//            }
//        });
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//
//                if (username.equals("") || password.equals("")) {
//                    Toast.makeText(context, "Not all necessary fields filled", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                ApplicationContext.getMessageService().getOrCreateUser(null, password, "login", 0L);
//
//                myDialog.dismiss();
//            }
//        });
//    }






    class CircleImageCreator {

        // make image round and set to view
//        Bitmap photo = getBitmap(user.getPhotoUrl());
//        ((ImageView)findViewById(R.id.avatar)).setImageBitmap(photo);

        private Bitmap getBitmap(final Uri photoUrl) {

            final Bitmap[] bitmap = new Bitmap[1];

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap[0] = Picasso.with(ApplicationContext.getContext()).load(photoUrl).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return getCircleMaskedBitmapUsingShader(bitmap[0], bitmap[0].getWidth());
        }

        public Bitmap getCircleMaskedBitmapUsingShader(Bitmap source, int radius) {
            if (source == null)
            {
                return null;
            }

            int diam = radius << 1;

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            Bitmap scaledBitmap = scaleTo(source, diam);
            final Shader shader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);

            Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(targetBitmap);

            canvas.drawCircle(radius, radius, radius, paint);

            return targetBitmap;
        }

        public Bitmap scaleTo(Bitmap source, int size) {
            int destWidth = source.getWidth();

            int destHeight = source.getHeight();

            destHeight = destHeight * size / destWidth;
            destWidth = size;

            if (destHeight < size)
            {
                destWidth = destWidth * size / destHeight;
                destHeight = size;
            }

            Bitmap destBitmap = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(destBitmap);
            canvas.drawBitmap(source, new Rect(0, 0, source.getWidth(), source.getHeight()), new Rect(0, 0, destWidth, destHeight), new Paint(Paint.ANTI_ALIAS_FLAG));
            return destBitmap;
        }
    }


}
