package sapsan.jenkins

import sapsan.core.Context

class Stage extends Context {

  enum Status {
    NOT_STARTED,
    SKIPPED,
    STARTED,
    SUCCESS,
    UNSTABLE,
    FAILED
  }

  String name
  Closure steps
  Status status = Status.NOT_STARTED

  Stage(String name, Closure steps) {
    this.name = name
    this.steps = steps
  }

  void call() {
    setStatus(Status.STARTED)
    try {
      Context.script.stage(name) {
        steps(owner: this)
      }
      setStatus(Status.FINISHED)
    } catch (Exception exception) {
      Context.script.error exception.message
      setStatus(Status.FAILED)
    }
  }

  void haveCondition(Closure<Boolean> condition) {
    if (condition.call() == false) {
      setStatus(Status.SKIPPED)
    }
  }

  void setStatus(Status status) {
//    Pipeline.log "[${this.name}] status changed (${this.status} -> ${status})"
    this.status = status
  }
}