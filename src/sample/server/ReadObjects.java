package sample.server;

import sample.Appliance;
import sample.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadObjects {
    static void readObjects() throws FileNotFoundException {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("server.dat")))
        {
            WorkWithServer.appsList = (List) objectInputStream.readObject();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}


