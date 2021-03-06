package main.program.java.constants;

/**
 * This class contains all the error messages that will later be output for the
 * user.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Errors {

        /**
         * This error message is displayed if the user enters an invalid input.
         */
        public static final String EMPTY_INPUT = "Empty Input!";

        public static final String INVALID_INPUT_CHARS = "Invalid characters: %s";

        /**
         * This error message is displayed if no more characters can be entered into the input field.
         */
        public static final String TOO_MANY_PARAMETERS = "Input too big!";

        // Utility classes have a private constructor.
        private Errors() {

        }
}
