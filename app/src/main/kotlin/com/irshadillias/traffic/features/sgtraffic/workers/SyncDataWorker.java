package com.irshadillias.traffic.features.sgtraffic.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class SyncDataWorker extends Worker {

    private static final String TAG = SyncDataWorker.class.getSimpleName();

    public SyncDataWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Context applicationContext = getApplicationContext();
        //simulate slow work
        Log.i(TAG, "Fetching Data from Remote host");
        //WorkerUtils.sleep();

        /*try {
            //create a call to network
            Call<List<Book>> call = bookService.fetchBooks();
            Response<List<Book>> response = call.execute();

            if (response.isSuccessful() && response.body() != null && !response.body().isEmpty() && response.body().size() > 0) {

                String data = WorkerUtils.toJson(response.body());
                Log.i(TAG, "data fetched from network successfully");

                //delete existing book data
                bookDao.deleteBooks();

                bookDao.saveBooks(response.body());

                WorkerUtils.makeStatusNotification(applicationContext.getString(R.string.new_data_available), applicationContext);

                return Result.success();
            } else {
                return Result.retry();
            }


        } catch (Throwable e) {
            e.printStackTrace();
            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error fetching data", e);
            return Result.failure();
        }*/
        return Result.success();
    }


    @Override
    public void onStopped() {
        super.onStopped();
        Log.i(TAG, "OnStopped called for this worker");
    }
}
