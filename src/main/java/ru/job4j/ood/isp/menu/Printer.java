package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int length = item.getNumber().length();
            String space = "";
            if (length == 4) {
                space = "----";
            } else if (length == 6) {
                space = "--------";
            } else if (length > 6) {
                space = "------------";
            }
            System.out.println(space + item.getNumber() + " " + item.getName());
        }
    }
}