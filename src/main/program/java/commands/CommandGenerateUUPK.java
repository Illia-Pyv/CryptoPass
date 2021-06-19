package main.program.java.commands;

import main.program.java.constants.UUPK;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandGenerateUUPK extends CommandEncode {
    private String time = null;

    @Override
    protected int calculatePosR1(String parameters) {
        time = calculateTime();
        long timeHash = calculatePositiveHash(time);
        long num = calculatePositiveHash(parameters) + timeHash;
        return modulo(num, CHARACTERS.length());
    }

    @Override
    protected int calculatePosR2(String parameters) {
        time = calculateTime();
        long uupkhash = calculatePositiveHash(String.format("%s", calculatePositiveHash(String.format("%s", calculatePositiveHash(time)))));
        long hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters))))) + uupkhash;
        return modulo(hash, CHARACTERS.length());
    }

    @Override
    protected int calculatePosR3(String parameters) {
        time = calculateTime();
        long uupkHash = calculatePositiveHash(String.format("%s", calculatePositiveHash(time)));
        long hash = calculatePositiveHash(String.format("%s", calculatePositiveHash(parameters))) + uupkHash;
        return modulo(hash, CHARACTERS.length());
    }

    private String calculateTime() {
        if (time == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:ms");
            Date date = new Date();
            return formatter.format(date);
        }
        return time;
    }
}
