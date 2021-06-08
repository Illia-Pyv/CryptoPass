package main.program.java.constants;

/**
 * This class contains all the regular expressions needed to construct more
 * complex regular expressions.
 * 
 * @author Illia Pyvovar
 * @version 1.0
 */
public final class Regex {

    /**
     * This character marks the beginning of a regex string.
     */
    public static final String START = "^";

    /**
     * This character marks the end of a regex string.
     */
    public static final String END = "$";

    /**
     * This regex symbolizes a space in regex notation.
     */
    public static final String SPACE = " ";

    /**
     * This regex symbolizes a semicolon in regex notation.
     */
    public static final String SEMICOLON = ";";

    /**
     * This regex represents a comma in regex notation.
     */
    public static final String COMMA = ",";

    /**
     * This regex represents the symbols used to separate the user input.
     */
    public static final String SEPERATORS = "[" + Regex.SPACE + Regex.COMMA + Regex.SEMICOLON + "]";

    /**
     * This regex indicates when a new line starts in a string.
     */
    public static final String NEWLINE = "\n";

    /**
     * This regex describes the position of the input characters into which rotor pin they go into.
     */
    public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !?;:.,-_#)(|^][@$%&=~+*`";

    /**
     * This regex describes the position of the rotor no.1 Characters in.
     */
    public static final String ROTOR1_CHARACTERS_IN = "ab?cd;ef7g(h0|ij$kl4m^n:op]qr-s#tu8vw_xy&z=A5BC~DE3F%GH,6I[J.KL+MN)OP*QR9ST1U@VW`XY2Z!";

    /**
     * This regex describes the position of the rotor no.1 Characters out.
     */
    public static final String ROTOR1_CHARACTERS_OUT = "^$kl4mMN)OP*QRd;YwDE7g(hC,3Fef1U@V_B%GHn:op|6I[J.Kj2L+ab~W`X0iZ!]qr-s#tu8vxy&z=A59ST?c";

    /**
     * This regex describes the position of the rotor no.2 Characters in.
     */
    public static final String ROTOR2_CHARACTERS_IN = "f^n:oW`X7g(h0ij$kE3F%DVZ!I[JaY2b?A5s#tu8HB6*QRzC~Kcd;ew,p]qr-l4|my&G=L+MN)OP_xS.9vT1U@";

    /**
     * This regex describes the position of the rotor no.2 Characters out.
     */
    public static final String ROTOR2_CHARACTERS_OUT = "ewp]qr)OP_xS.U@n:fj$kE4aY2,bW`oiu^8my&G=9vT1L+MN5s#t3X7~Z!6H%DI|lJA-Rz*CVF?[QBg(h0Kcd;";

    /**
     * This regex describes the position of the rotor no.3 Characters in.
     */
    public static final String ROTOR3_CHARACTERS_IN = "%cdf^nMN)WOju8H|rm9v7gkB6*;VlZ!U@=L+ep_xS(zP,D4#aY3QR$q].IC~KE:o`Xy&Gh0T1F[J2b?Awi5s-t";

    /**
     * This regex describes the position of the rotor no.3 Characters out.
     */
    public static final String ROTOR3_CHARACTERS_OUT = ":Sw`3Q7gkB6zP*KA;VlU@dW=L+ei5s-t$q].aEI)Cp_Xy&fm9x^nJ,2b?Y~Gh0RoT1F[%cOZ!ju8Hrv(MN|D4#";

    /**
     * This regex represents all characters that can be inputed into the
     * commandline.
     */
    public static final String INPUT_CHARACTERS = "[a-zA-Z0-9 ?;,)(|$:#_=~%+*@`&.^!]+";

    // private constructor in a utility class
    private Regex() {

    }
}
