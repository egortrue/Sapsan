package sapsan.core

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.Memoized

final class Job extends Context {

    /**
     *  Полный путь до джобы
     */
    @NonCPS
    @Memoized
    static String getName() {
        if (pipeline.env.BRANCH_NAME)
            return pipeline.env.JOB_NAME.split('/')[0..-2].join('/')
        return pipeline.env.JOB_NAME
    }

    @NonCPS
    @Memoized
    static String getBuildNumber() {
        pipeline.env.BUILD_NUMBER
    }

    @NonCPS
    @Memoized
    static String getTag() {
        pipeline.env.BUILD_TAG
    }

    @NonCPS
    @Memoized
    static String getBranch() {
        pipeline.env.BRANCH_NAME
    }

    @NonCPS
    @Memoized
    static String getUrl() {
        pipeline.env.BUILD_URL
    }

    @NonCPS
    @Memoized
    static String getBaseUrl() {
        url.split('/')[0..2].join('/')
    }

    static void setBuildName(String name) {
        pipeline.currentBuild.displayName = name
    }

    static String getInfo() {
        """
        [Job Information]
        name=$name
        branch=$branch
        url=$url
        baseUrl=$baseUrl
        """.stripIndent()
    }
}
