package main.program.java.constants;

public final class Messages {

    /**
     * This message is displayed if the password could be successfully copied into the clipboard.
     */
    public static final String COPIED = "Copied!";

    /**
     * This message is displayed if there was nothing to be copied into the clipboard.
     */
    public static final String NOT_COPIED = "Nothing to copy!";

    /**
     * This message is written down in the config.txt file.
     */
    public static final String WARNING = "\nIt seems that you have found your own UUPK.\nIt is strongly recommended not to delete the file or your UUPK,\nas it will resolve in the loss passwords (Unless you wrote your UUPK down and you can sign in.).\nIt's also not recommended to change even one character of your password key,\nas it will change your passwords completely.";

    // private constructor for utility class
    private Messages() {

    }
}
