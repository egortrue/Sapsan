package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS

final class Colors {

    static String reset = "\033[0m"

    @NonCPS
    static String red(String message) {
        "\033[101m$message$reset"
    }

}
