package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    ActionDelegate action = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        TodoApp app = new TodoApp();
        request();
        while (true) {
            String answer = scanner.nextLine();
            if ("0".equals(answer)) {
                break;
            }
            if ("1".equals(answer)) {
                out("Введите название нового пункта меню:");
                String name = scanner.nextLine();
                menu.add(Menu.ROOT, name, app.action);
            }
            if ("2".equals(answer)) {
                out("Введите название основного пункта меню:");
                var parent = scanner.nextLine();
                while (menu.select(parent).isEmpty()) {
                    out("Повторите ввод или введите '0'");
                    parent = scanner.nextLine();
                }
                out("Введите название подпункта:");
                var child = scanner.nextLine();
                menu.add(parent, child, app.action);
            }
            if ("3".equals(answer)) {
                out("Введите название пункта, действие которого вы хотите вызвать:");
                var name = scanner.nextLine();
                menu.select(name).get().getActionDelegate().delegate();
            }
            if ("4".equals(answer)) {
                printer.print(menu);
            }
            answer = null;
            request();
        }
    }

    private static void request() {
        var ls = System.lineSeparator();
        out("Выберите действие (введите число):" + ls
                + "1 - Добавить основной пункт в меню" + ls
                + "2 - Добавить подпункт в основной пункт меню" + ls
                + "3 - Вызвать действие из пункта меню" + ls
                + "4 - Вывести меню в консоль" + ls
                + "0 - Завершить." + ls);
    }

    private static void out(String text) {
        System.out.println(text);
    }
}
