package hu.bartabalazs.restklienskonzol;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements Runnable {
    private HashMap<String, Idojaras> elorejelzesek;
    private Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        elorejelzesek = new HashMap<>();
        this.kapcsolat = kapcsolat;

    }

    @Override
    public void run() {
        try {
            beolvas();
            System.out.println("beolvas megvan");
            DataInputStream ugyfeltol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnek = new DataOutputStream(kapcsolat.getOutputStream());

            while (true){
                int menu;
                do {
                    menu = ugyfeltol.readInt();
                    switch (menu){
                        case 1:
                            ugyfelnek.writeUTF(atlagMelegMa());
                            ugyfelnek.flush();
                            break;
                        case 2:
                            ugyfelnek.writeUTF(atlagHidegebbMa());
                            break;
                        case 3:
                            ugyfelnek.writeUTF(atlagMelegHolnap());
                            break;
                        case 4:
                            ugyfelnek.writeUTF(atlagHidegHolnap());
                            break;
                        case 5:
                            ugyfelnek.writeUTF(osszesAdat());
                            break;
                        default:
                            ugyfelnek.writeUTF("Ön a kilépést választotta");
                            break;
                    }
                }while (menu != 0);
            }
        }catch (IOException ex){
            System.err.println(ex);
        }
    }
    private String osszesAdat() {
        String s = "";
        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            s += entry.getValue()+"\n";
        };
        return s;
    }

    private String atlagHidegHolnap() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            ossz += entry.getValue().getHolnapi().getMin();
            db++;
        }
        return String.format("%f fok lesz átlagban hideg holnap.", (ossz/db));

    }

    private String atlagMelegHolnap() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            ossz += entry.getValue().getHolnapi().getMax();
            db++;
        }
        return String.format("%f fok lesz átlagban meleg holnap.", (ossz/db));
    }

    private String atlagHidegebbMa() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            ossz += entry.getValue().getMai().getMin();
            db++;
        }
        return String.format("%f fok volt átlagban hideg ma.", (ossz/db));
    }

    private String atlagMelegMa() {
        double ossz = 0;
        int db = 0;

        for (Map.Entry<String, Idojaras> entry: elorejelzesek.entrySet()){
            ossz += entry.getValue().getMai().getMax();
            db++;
        }
        return String.format("%f fok volt átlagban meleg ma.", (ossz/db));
    }
    public void beolvas(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while (sor != null){
                Idojaras i = new Idojaras(sor);
                elorejelzesek.put(i.getMegye(), i);
                sor= br.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
