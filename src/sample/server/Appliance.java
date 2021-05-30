package sample.server;

import java.io.Serializable;

public abstract class Appliance implements Comparable<Appliance>, Serializable {
    String nameApp;
    int powerApp;
    String on;

    public Appliance(String nameApp, int powerApp, String on) {
        this.nameApp = nameApp;
        this.powerApp = powerApp;
        this.on = on;
    }

    public String getNameApp() {
        return nameApp;
    }

    public void setNameApp(String nameApp) {
        this.nameApp = nameApp;
    }

    public int getPowerApp() {
        return powerApp;
    }

    public void setPowerApp(int powerApp) {
        this.powerApp = powerApp;
    }

    public String isOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    @Override
    public int compareTo(Appliance o) {
        return this.getPowerApp() - o.getPowerApp();
    }
}
