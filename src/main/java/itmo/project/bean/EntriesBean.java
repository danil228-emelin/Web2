package itmo.project.bean;

import java.util.ArrayList;
import java.util.List;

public class EntriesBean {
    private final List<Entry> ENTRY_LIST;

    public EntriesBean() {
        ENTRY_LIST = new ArrayList<>();
    }

    public EntriesBean(List<Entry> list) {

        ENTRY_LIST = list;
    }
}
