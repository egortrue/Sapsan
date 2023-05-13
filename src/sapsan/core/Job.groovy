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

    static String getInfo() {
        """
        JOB_NAME=${script.env.JOB_NAME}
        JOB_BASE_NAME=${script.env.JOB_BASE_NAME}
        JOB_URL=${script.env.JOB_URL}
        BUILD_URL=${script.env.BUILD_URL}
        NODE_NAME=${script.env.NODE_NAME}
        NODE_LABELS=${script.env.NODE_LABELS}
        """.stripIndent()
    }
}
