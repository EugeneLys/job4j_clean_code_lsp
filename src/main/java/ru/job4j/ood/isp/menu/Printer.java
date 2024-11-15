package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int level = item.getNumber().length();
            String space = "";
            if (level == 4) {
                space = "----";
            } else if (level == 6) {
                space = "--------";
            } else if (level > 6) {
                space = "------------";
            }
            if (level == 6) {
                System.out.println(space + item.getNumber() + " "
                        + item.getName());
            } else if (level == 4) {
                System.out.println(space + item.getNumber() + " "
                        + item.getName());
            } else {
                System.out.println(space + item.getNumber() + " "
                        + item.getName());
            }
        }
    }
}