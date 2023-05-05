package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS

final class Colors {

    static String reset = "\\e[0m"

    @NonCPS
    static String red(String message) {
        "\\e[31m$message$reset"
    }

}
