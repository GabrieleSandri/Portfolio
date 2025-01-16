package com.example.server;

import Model.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class mainin {

     public static void main(String[] args) throws IOException {

         try{
         ObjectOutputStream fileOutput = new ObjectOutputStream(new FileOutputStream("gabriele@gabriele.it.txt"));
         ArrayList<email> emailist = new ArrayList<>();
         inBox inbox = new inBox(emailist,new user("gabriele@gabriele.it"));
         fileOutput.writeObject(inbox);
         fileOutput.close();}
         catch(Exception e){
             System.out.println(e);
         }

         try{
             ObjectOutputStream fileOutput = new ObjectOutputStream(new FileOutputStream("mattia@mattia.it.txt"));
             ArrayList<email> emailist = new ArrayList<>();
             inBox inbox = new inBox(emailist,new user("mattia@mattia.it"));
             fileOutput.writeObject(inbox);
             fileOutput.close();}
         catch(Exception e){
             System.out.println(e);
         }

         try{
             ObjectOutputStream fileOutput = new ObjectOutputStream(new FileOutputStream("marco@marco.it.txt"));
             ArrayList<email> emailist = new ArrayList<>();
             inBox inbox = new inBox(emailist,new user("marco@marco.it"));
             fileOutput.writeObject(inbox);
             fileOutput.close();}
         catch(Exception e){
             System.out.println(e);
         }

     }

}
