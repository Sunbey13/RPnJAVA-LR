package sample.server;

import sample.Main;
import sample.ShowInPowerAreaController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WorkWithServer {
    static List<Appliance> appsList = new ArrayList<>();
    static int minPower;
    static int maxPower;

    public static synchronized String getUTF(Socket clientDialog) throws IOException {
        DataInputStream ois = new DataInputStream(clientDialog.getInputStream());
        return ois.readUTF();
    }


    public static synchronized void sendListToClient(Socket clientDialog) {
        try {
            ReadObjects.readObjects();
            ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
            oos.writeObject(appsList);
            System.out.println("Список приборов отправлен клиенту.");
            oos.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized void sendListPowerUp(Socket clientDialog) {
        try{
            ReadObjects.readObjects();
            List<Appliance> list = new ArrayList<>();
            ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
            for (Appliance element: appsList) {
                if (element.on.equals("Включен")){
                   list.add(element);
                }
            }
            oos.writeObject(list);
            System.out.println("Список включенных приборов отправлен клиенту.");
            oos.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized void getCarsInfoFromClient(Socket clientDialog) {
        try {
            DataInputStream ios = new DataInputStream(clientDialog.getInputStream());
            appsList.add(new CarsApp(ios.readUTF(), ios.readInt(), ios.readUTF(), ios.readUTF()));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("server.dat"));
            oos.writeObject(appsList);
            System.out.println("Объект записан на сервере!");
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void getKitchenInfoFromClient(Socket clientDialog){
        try {
            DataInputStream ios = new DataInputStream(clientDialog.getInputStream());
            appsList.add(new KitchenApp(ios.readUTF(), ios.readInt(), ios.readUTF(), ios.readUTF()));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("server.dat"));
            oos.writeObject(appsList);
            System.out.println("Объект записан на сервере!");
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void getHouseInfoFromClient(Socket clientDialog){
        try {
            DataInputStream ios = new DataInputStream(clientDialog.getInputStream());
            appsList.add(new HouseApp(ios.readUTF(), ios.readInt(), ios.readUTF(), ios.readUTF()));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("server.dat"));
            oos.writeObject(appsList);
            System.out.println("Объект записан на сервере!");
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void getMinMaxPowerFromClient(Socket clientDialog){
        try {
            DataInputStream ios = new DataInputStream(clientDialog.getInputStream());
            minPower = ios.readInt();
            maxPower = ios.readInt();
            System.out.println("Минимальная и максимальная мощность получены!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void sendListPowerInArea(Socket clientDialog){
        try{
            ReadObjects.readObjects();
            List<Appliance> powerlist = new ArrayList<>();
            ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
            for (Appliance element: appsList) {
                if (element.powerApp > minPower && element.powerApp < maxPower){
                    powerlist.add(element);
                }
            }
            oos.writeObject(powerlist);
            System.out.println("Список приборов из диапазона отправлен клиенту.");
            oos.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}