package itmo.project.bean;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public record EntriesBean<T>(List<T> entryList) {
    public EntriesBean() {
        this(new ArrayList<>());
    }

    public void add(Optional<T> element) {
        log.info("Add element in EntriesBean");
        element.ifPresent(entryList::add);
    }

}
