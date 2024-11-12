package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            System.out.println(item.getNumber() + " "
                    + item.getName());
        }
    }
}
