package sapsan.module

import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline

class Checkout extends Module {

    String url
    String branch

    @Override
    protected Map getProperties() {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            return super.properties
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            return null
        }
    }

    @Override
    protected void checkProperties(Map properties) {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            assert properties["url"] != null
            assert properties["branch"] != null
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            assert Context.pipeline.scm != null
        }
    }

    @Override
    protected void initProperties(Map properties) {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = properties["url"]
            branch = properties["branch"]
        } else if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            url = Context.pipeline.scm.userRemoteConfigs[0].url
            branch = Job.branch
        }
    }

    @Override
    protected void execute() {
        Context.pipeline.sh "ls -al"
        Context.pipeline.checkout([$class           : 'GitSCM',
                                   branches         : [[name: branch]],
                                   userRemoteConfigs: [[credentialsId: 'my-cred', url: url]]])
        Context.pipeline.sh "ls -al"
        Context.pipeline.sh "exit 1"
    }
}
