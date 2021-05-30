package sample;

import java.util.Objects;

public class CarsApp extends Appliance {
    private String battery;

    public CarsApp(String nameApp, int powerApp, String on, String battery) {
        super(nameApp, powerApp, on);
        this.battery = battery;
    }

    public String getBattery() { return battery; }

    public void setBattery(String battery) { this.battery = battery; }

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

    public String getOn() { return on; }

    public void setOn(String on) { this.on = on; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsApp carsApp = (CarsApp) o;
        return battery == carsApp.battery;
    }

    @Override
    public int hashCode() {
        return Objects.hash(battery);
    }

    @Override
    public String toString() {
        return "Автомобильная перефирия \"" + nameApp + '\"' +
                " мощностью " + powerApp +
                "Вт. Подключение к сети – " + on +
                ", наличие батареи – " + battery;
    }
}
