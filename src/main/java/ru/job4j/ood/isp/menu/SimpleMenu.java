package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
   /*  добавьте реализацию*/
        MenuItem item = new SimpleMenuItem(childName, actionDelegate);
        for (MenuItem i : rootElements) {
            if (parentName.equals(i.getName())) {
                i.getChildren().add(item);
                return true;
            }
        }
        return  false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        /*  добавьте реализацию*/
        return null;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
                /*  добавьте реализацию*/
        return null;
    }

    private Optional<ItemInfo> findItem(String name) {
        /*  добавьте реализацию*/
        MenuItem item;
        String number;
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            item = iterator.next().menuItem;
            number = iterator.numbers.getLast();
            if (name.equals(item.getName())) {
                return Optional.of(new ItemInfo(item, number));
            }
        }
        return null;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}