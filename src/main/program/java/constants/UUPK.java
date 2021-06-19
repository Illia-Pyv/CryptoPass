package main.program.java.constants;

/**
 * This class describes the:
 * <p>User Unique Password Key (UUPK)</p>
 */
public class UUPK {
    private String uupk;
    private static UUPK instance;
    private static final int UUPK_LENGTH = 16;
    private static final int MIN_VARIATION = 1;

    private UUPK(String uupk) {
        this.uupk = uupk;
    }

    public static UUPK getInstance(String uupk) {
        if (instance == null) {
            instance = new UUPK(uupk);
        }
        return instance;
    }

    public String getUUPK() {
        return this.uupk;
    }

    public void setUUPK(String uupk) {
        this.uupk = uupk;
    }

    public boolean isValid() {
        if (this.uupk == null) {
            return false;
        }
        int length = this.uupk.length();
        int smallLetters = 0;
        int capitalLetters = 0;
        int numbers = 0;
        int special = 0;
        if (length < UUPK_LENGTH) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            String c = String.format("%s", this.uupk.charAt(i));
            if (c.matches(Regex.SMALL_LETTERS)) {
                smallLetters++;
            } else if (c.matches(Regex.CAPITAL_LETTERS)) {
                capitalLetters++;
            } else if (c.matches(Regex.NUMBERS)) {
                numbers++;
            } else {
                special++;
            }
        }

        if (smallLetters < MIN_VARIATION || capitalLetters < MIN_VARIATION || numbers < MIN_VARIATION || special < MIN_VARIATION) {
            return false;
        } else {
            return true;
        }
    }
}
