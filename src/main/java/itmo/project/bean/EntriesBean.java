package itmo.project.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntriesBean<T> {
    private final List<T> ENTRY_LIST;

    public EntriesBean() {
        ENTRY_LIST = new ArrayList<>();
    }

    public EntriesBean(List<T> list) {

        ENTRY_LIST = list;
    }

    public void add(Optional<T> element) {

        element.ifPresent(ENTRY_LIST::add);
    }
}
