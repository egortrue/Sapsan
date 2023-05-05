
def call() {

    echo "JOB_NAME = ${env.JOB_NAME}"
    echo "JOB_BASE_NAME = ${env.JOB_BASE_NAME}"

    if (multiBranch) {
        cleanWs()
        checkout scm
        String filePath = ".ci/"
        readFile()
    } else {
        libraryResource()
        String filePath = ""
    }
}