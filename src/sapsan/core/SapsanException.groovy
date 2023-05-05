package sapsan.core

import sapsan.utils.Colors

class SapsanException extends Exception {

    SapsanException(String message) {
        super(Colors.red("SAPSAN: $message"))
    }

}
