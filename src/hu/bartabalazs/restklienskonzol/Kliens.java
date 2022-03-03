package hu.bartabalazs.restklienskonzol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try{
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Válaszzon a menüpontok közül: ");
                System.out.println("1: Átlag legmelegebb ma");
                System.out.println("2: Átlag leghidegebb ma");
                System.out.println("3: Átlag  legmelegebb holnap");
                System.out.println("4: Átlag leghidegebb holnap");
                System.out.println("5: összes adat kiírása");
                System.out.println("0: Kilépés");
                menu = sc.nextInt();
                szervernek.writeInt(menu);
                szervernek.flush();
                System.out.println(szervertol.readUTF());

            }while(menu != 6);
        }catch (IOException e){
            System.err.println(e);
        }

    }
}
