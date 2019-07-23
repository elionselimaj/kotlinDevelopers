package al.logic.android.kotlinapplication.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler io();

    Scheduler ui();

    Scheduler computation();
}
