import sapsan.jenkins.Pipeline

def call() {
    if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
        checkout scm
    } else {
        checkout([
            $class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[$class: 'CleanCheckout']],
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: '<gitCredentials>', url: '<gitRepoURL>']]
        ])
    }
}