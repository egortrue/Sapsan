package sapsan.core

class Job extends Context {
    static String name

    static String getUrl() {
        script.env.BUILD_URL
    }

    static String getBaseUrl() {
        url.split("/")[0..3].join('')
    }
}
