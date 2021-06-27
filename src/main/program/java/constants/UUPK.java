package main.program.java.constants;

/**
 * This class describes the:
 * <p>User Unique Password Key (UUPK)</p>
 */
public class UUPK {
    private String uupk;
    private static UUPK instance;
    private static final int UUPK_LENGTH = 6;

    private UUPK(String uupk) {
        this.uupk = uupk;
    }

    /**
     * This method returns an instance of this class.
     *
     * @param uupk if an instance of this class does not exist yet, then it sets the UUPK string to this parameter
     * @return Returns an instance of this class
     */
    public static UUPK getInstance(String uupk) {
        if (instance == null) {
            instance = new UUPK(uupk);
        }
        return instance;
    }

    /**
     * This method gets the uupk.
     *
     * @return Returns the uupk as a string
     */
    public String getUUPK() {
        return this.uupk;
    }

    /**
     * This method sets the UUPK.
     *
     * @param uupk the string that the UUPK should be set as
     */
    public void setUUPK(String uupk) {
        this.uupk = uupk;
    }

    /**
     * This method checks if the UUPK read, is valid.
     *
     * @return Returns true if the UUPK is valid and returns false otherwise
     */
    public boolean isValid() {
        if (this.uupk == null || !(uupk.length() == UUPK_LENGTH)) {
            return false;
        }
        for (int i = 0; i < this.uupk.length(); i++) {
            if (!(this.uupk.charAt(i) < '~' && this.uupk.charAt(i) > '!')) {
                return false;
            }
        }
        return true;
    }
}
