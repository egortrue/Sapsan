package sapsan.components

import sapsan.core.Context

@Singleton
class ReleaseNotes extends Context {

  def publish() {
    Context.script.echo nexusUrl
    Context.script.echo bbUrl
  }

}
