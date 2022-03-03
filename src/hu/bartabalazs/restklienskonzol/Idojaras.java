package hu.bartabalazs.restklienskonzol;

public class Idojaras {

    private String megye;
    private Elorejelzes mai;
    private Elorejelzes holnapi;

    public Idojaras(String sor) {
        String[] adatok = sor.split("\\t+", -1);
        this.megye = adatok[0].trim();
        String e = adatok[1].trim();
        String minMax = adatok[2].trim();
        this.mai = new Elorejelzes(e, minMax);
        e = adatok[3].trim();
        minMax = adatok[4].trim();
        this.holnapi = new Elorejelzes(e, minMax);
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzes getMai() {
        return mai;
    }

    public void setMai(Elorejelzes mai) {
        this.mai = mai;
    }

    public Elorejelzes getHolnapi() {
        return holnapi;
    }

    public void setHolnapi(Elorejelzes holnapi) {
        this.holnapi = holnapi;
    }

    @Override
    public String toString() {
        return megye + ": \n\t mai=" + mai +
                        "\n\t holnapi=" + holnapi;
    }
}
