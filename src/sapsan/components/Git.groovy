package sapsan.components

import com.cloudbees.groovy.cps.NonCPS
import sapsan.jenkins.Pipeline

class Git extends Component {

  private static Git _instance

  static Git getInstance() {
    if (_instance == null)
      _instance = new Git()
    return _instance
  }

  enum Type {
    CLASSIC,
    MULTIBRANCH
  }

  Type type
  String url
  String branch

  @Override
  @NonCPS
  void initParameters(Map parameters) {
    type = parameters["type"] as Type
    url = parameters["url"]
    branch = parameters["branch"]

    assert type != null
    assert url != null
    assert branch != null
  }

  static void checkout() {
    Pipeline.stage("Checkout") {
      log "checkout repo from $instance.url"
    }
  }
}
