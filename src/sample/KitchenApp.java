package sample;

import java.util.Objects;

public class KitchenApp extends Appliance {
    private String products;

    public KitchenApp(String nameApp, int powerApp, String products, String on) {
        super(nameApp, powerApp, on);
        this.products = products;
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

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getOn() { return on; }

    public void setOn(String on) { this.on = on; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitchenApp that = (KitchenApp) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "Кухонный прибор \"" + nameApp +
                "\" мощностью " + powerApp +
                "Вт. Подключение к сети – " + on +
                ", работает с продуктами: " + products;
    }
}
