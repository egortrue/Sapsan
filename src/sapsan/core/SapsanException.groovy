package sapsan.core

import sapsan.utils.Colors

class SapsanException extends Exception {

    SapsanException(String message) {
        this.message = Colors.red("SAPSAN: $message")
    }

}
