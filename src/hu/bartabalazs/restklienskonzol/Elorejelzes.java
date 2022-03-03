package hu.bartabalazs.restklienskonzol;

import java.util.HashMap;

public class Elorejelzes {
    private String szovegesElorejelzes;
    private int min;
    private int max;

    public Elorejelzes(String elo, String minmax) {
        this.szovegesElorejelzes = elo;
        String[] st = minmax.split("/");
        this.min = Integer.parseInt(st[0]);
        this.max = Integer.parseInt(st[1]);
    }

    public String getSzovegesElorejelzes() {
        return szovegesElorejelzes;
    }

    public void setSzovegesElorejelzes(String szovegesElorejelzes) {
        this.szovegesElorejelzes = szovegesElorejelzes;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return szovegesElorejelzes +
                ", min=" + min +
                ", max=" + max;
    }
}
