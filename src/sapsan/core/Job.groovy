package sapsan.core

class Job extends Context {
    static String getName() {
        script.env.JOB_NAME
    }

    static String getUrl() {
        script.env.BUILD_URL
    }

    static String getBaseUrl() {
        url.split("/")[0..2].join('/')
    }

    static String getPath() {
        "/" + url.split("/")[3..-1].join('/')
    }

    static String getInfo() {
        """
        [Job Information]
        Job.name=$name
        Job.url=$url
        Job.baseUrl=$baseUrl
        Job.path=$path
        """.stripIndent()
    }
}
