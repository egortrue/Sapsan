package sapsan.core

class Job extends Context {
    static String getUrl() {
        script.env.BUILD_URL
    }

    static String getName() {
        script.env.JOB_NAME
    }

    static String getBaseUrl() {
        url.split("/")[0..2].join('/')
    }

    static String getPath() {
        url.split("/")[3..-1].join('/')
    }
}
