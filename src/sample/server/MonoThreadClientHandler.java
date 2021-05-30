package sample.server;

import java.io.*;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;


    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            String command = WorkWithServer.getUTF(clientDialog);
            switch (command) {
                case "get list":
                    System.out.println("Отправляю список всех приборов...");
                    WorkWithServer.sendListToClient(clientDialog);
                    break;
                case "get list powerUp":
                    System.out.println("Отправляю список включенных приборов...");
                    WorkWithServer.sendListPowerUp(clientDialog);
                    break;
                case "write carsApp info":
                    System.out.println("Получаю информацию об объекте автомобильной перефирии...");
                    WorkWithServer.getCarsInfoFromClient(clientDialog);
                    break;
                case "write kitchenApp info":
                    System.out.println("Получаю информацию о кухонном приборе...");
                    WorkWithServer.getKitchenInfoFromClient(clientDialog);
                    break;
                case "write houseApp info":
                    System.out.println("Получаю информацию о бытовом приборе...");
                    WorkWithServer.getHouseInfoFromClient(clientDialog);
                    break;
                case "send min max power":
                    System.out.println("Получаю информацию о минимальной и максимальной мощности...");
                    WorkWithServer.getMinMaxPowerFromClient(clientDialog);
                    break;
                case "get list apps in power area":
                    System.out.println("Отправлаю список приборов из диапазона");
                    WorkWithServer.sendListPowerInArea(clientDialog);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
