package sapsan.components

import sapsan.Context

@Singleton
class ReleaseNotes extends Context {

  def publish() {
    Context.env.echo nexusUrl
    Context.env.echo bbUrl
  }

}
