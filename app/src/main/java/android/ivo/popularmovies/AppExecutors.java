package android.ivo.popularmovies;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private final Executor mDiskIOExecutor;
    private final Executor mNetworkExecutor;
    private final Executor mMainThread;

    public Executor getDiskIOExecutor() {
        return mDiskIOExecutor;
    }

    public Executor getNetworkExecutor() {
        return mNetworkExecutor;
    }

    public Executor getMainThread() {
        return mMainThread;
    }

    private AppExecutors() {
        mDiskIOExecutor = Executors.newSingleThreadExecutor();
        mNetworkExecutor = Executors.newFixedThreadPool(3);
        mMainThread = new MainThreadExecutor();
    }

    public static AppExecutors getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final AppExecutors INSTANCE = new AppExecutors();
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mHandler = new android.os.Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    }
}
