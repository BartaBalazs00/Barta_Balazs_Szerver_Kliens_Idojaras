package hu.bartabalazs.restklienskonzol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Szerver {

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        System.out.println("A szervel indul");
        try {
            ServerSocket socket = new ServerSocket(8080);
            while (true){
                Socket kapcsolat = socket.accept();
                InetAddress ugyfel = kapcsolat.getInetAddress();

                Ugyfelkiszolgalo uk = new Ugyfelkiszolgalo(kapcsolat);
                exe.submit(uk);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.err.println(e);
        }

    }
}
