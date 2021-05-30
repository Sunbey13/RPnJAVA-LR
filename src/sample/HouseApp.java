package sample;

import java.util.Objects;

public class HouseApp extends Appliance {
    String display;

    public HouseApp(String nameApp, int powerApp, String on, String display) {
        super(nameApp, powerApp, on);
        this.display = display;
    }

    public String getDisplay() { return display; }

    public void setDisplay(String display) { this.display = display; }

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
        HouseApp houseApp = (HouseApp) o;
        return display == houseApp.display;
    }

    @Override
    public int hashCode() {
        return Objects.hash(display);
    }

    @Override
    public String toString() {
        return "Бытовой прибор \"" + nameApp + "\"" +
                ", мощностью " + powerApp +
                "Вт. Подключение к сети – " + on +
                ", наличие дисплея – " + display;
    }
}
