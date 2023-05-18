package sapsan.core

import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
import sapsan.util.Log
import sapsan.util.Log.LogException

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
        Context.pipeline.stage(name) {
            if (lastStatus == Status.FAILED) {
                status = Status.SKIPPED
                Utils.markStageSkippedForConditional(name)
                return
            }

            try {
                status = Status.STARTED
                steps()
                status = Status.SUCCESS
            } catch (LogException e) {
                status = Status.FAILED
            } catch (Exception e) {
                Log.error(e.message, false)
                status = Status.FAILED
            }
            
            if (lastStatus != Status.FAILED)
                lastStatus = status
        }
    }

    void haveCondition(Closure<Boolean> condition) {
        if (condition.call() == false) {
            setStatus(Status.SKIPPED)
        }
    }
}