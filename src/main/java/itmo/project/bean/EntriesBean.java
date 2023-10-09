package itmo.project.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntriesBean<T> {
    private  List<T> entryList;

    public EntriesBean() {
        System.out.println("EntriesBean initialized");

        entryList = new ArrayList<>();
    }

    public EntriesBean(List<T> list) {

        entryList = list;
    }

    public void add(Optional<T> element) {
        System.out.println("Add element in EntriesBean");
        element.ifPresent(entryList::add);
    }
    public void setEntries(List<T> entries) {
        entryList = entries;
    }
    public List<T> entryList() {
        System.out.println("return entryList");
        return entryList;
    }
}
