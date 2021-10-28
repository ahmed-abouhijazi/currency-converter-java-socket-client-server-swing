/*
 *Copyright reserved to monsieur ahmed ABOUHIJAZI CHI D3IWA
 */



package inetaddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;


public class SocketServer {
    
    public static void main(String[] args) throws IOException {
        double Dollar;
        double Euro;
        String Sdollar;
        String Seuro;
         ServerSocket socketEcoute = new ServerSocket(7777);
         while(true) {
             Socket socketConnexion = socketEcoute.accept();
             BufferedReader entreeDepuisClient = new BufferedReader(new InputStreamReader(socketConnexion.getInputStream()));
             DataOutputStream sortieVersClient = new DataOutputStream(socketConnexion.getOutputStream());
             Sdollar = entreeDepuisClient.readLine();
             System.out.println(Sdollar);
             if(!Sdollar.isEmpty() && isNumeric(Sdollar)) {
             Dollar= Double.valueOf(Sdollar);
             System.out.println("Dollar: " + Sdollar + "$");
             Euro= 0.86*Dollar;
             Seuro= String.valueOf(Euro);
             System.out.println("Euro: " + Seuro + "€");
             Seuro = Seuro.toUpperCase()+'\n';
             sortieVersClient.writeBytes(Seuro);
             }
             else {
            	 System.out.println("its not a valid number");
            	 Seuro="";
            	 Seuro = Seuro.toUpperCase()+'\n';
            	 sortieVersClient.writeBytes(Seuro);
             }

         }
         

    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    



}