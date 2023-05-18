package sapsan.core

import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
import sapsan.util.Log

final class Stage extends Context {

    enum Status {
        NOT_STARTED,
        SKIPPED,
        STARTED,
        SUCCESS,
        UNSTABLE,
        FAILED
    }

    static Status lastStatus = Status.NOT_STARTED

    String name
    Closure steps
    Status status = Status.NOT_STARTED

    Stage(String name, Closure steps) {
        this.name = name
        this.steps = steps
    }

    void execute() {
        status = Status.STARTED
        try {
            Context.pipeline.stage(name) {
                if (lastStatus == Status.FAILED) {
                    status = Status.SKIPPED
                    Utils.markStageSkippedForConditional(name)
                    return
                }

                steps()
            }
            status = Status.SUCCESS
        } catch (Exception exception) {
            Log.error(exception.message, exit: false)
            status = Status.FAILED
        }
        lastStatus = status
    }

    void haveCondition(Closure<Boolean> condition) {
        if (condition.call() == false) {
            setStatus(Status.SKIPPED)
        }
    }
}