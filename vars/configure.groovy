import sapsan.jenkins.Pipeline

def call() {

    echo "JOB_NAME = ${env.JOB_NAME}"
    echo "JOB_BASE_NAME = ${env.JOB_BASE_NAME}"
    echo "JOB_URL = ${env.JOB_URL}"
    echo "BUILD_URL = ${env.BUILD_URL}"

    echo "NODE_NAME = ${env.NODE_NAME}"
    echo "NODE_LABELS = ${env.NODE_LABELS}"


    Pipeline.type = env.JOB_NAME.contains('/') ? Pipeline.Type.MULTIBRANCH : Pipeline.Type.CLASSIC

    echo "Pipeline.Type = ${Pipeline.type}"

    sh "ls -al $env.WORKSPACE"

//    if (multiBranch) {
//        cleanWs()
//        checkout scm
//        String filePath = ".ci/"
//        readFile()
//    } else {
//        libraryResource()
//        String filePath = ""
//    }
}