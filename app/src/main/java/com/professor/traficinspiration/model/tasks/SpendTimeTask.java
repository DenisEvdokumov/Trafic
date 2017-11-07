package com.professor.traficinspiration.model.tasks;


import android.app.Activity;

import com.professor.traficinspiration.model.Order;

import java.util.Map;

public class SpendTimeTask extends Task {

    public SpendTimeTask(Order order, String taskName) {
        super(order, taskName);
    }

    public boolean executeTask(Map<String, String> parameters) {

        //        // Get running processes
//        ActivityManager manager = (ActivityManager) ApplicationContext.getContext().getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
//        manager.getRunningServices(Integer.MAX_VALUE);
//
//        runningProcesses.get(0).pkgList;
//
//        if (runningProcesses != null && runningProcesses.size() > 0) {
//            // Set data to the list adapter
//            setListAdapter(new ListAdapter(this, runningProcesses));
//        } else {
//            // In case there are no processes running (not a chance :))
//            Toast.makeText(getApplicationContext(), "No application is running", Toast.LENGTH_LONG).show();
//        }


//        try
//        {
//            Process mLogcatProc = null;
//            BufferedReader reader = null;
//            mLogcatProc = Runtime.getRuntime().exec(new String[]{"logcat", "-d"});
//
//            reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
//
//            String line;
//            final StringBuilder log = new StringBuilder();
//            String separator = System.getProperty("line.separator");
//
//            while ((line = reader.readLine()) != null)
//            {
//                log.append(line);
//                log.append(separator);
//            }
//            String w = log.toString();
//
//            TextView textView = (TextView) ApplicationContext.getContext().findViewById(R.id.testView);
//            textView.setText(w);
//
////            Toast.makeText(ApplicationContext.getContext(),w, Toast.LENGTH_LONG).show();
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(ApplicationContext.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//        }


        return false;
    }

    @Override
    public void executeTask(Activity activity) {

    }
}
