package ru.ref.smlr.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    private Map<Long, String> map = new ConcurrentHashMap<>();

    @Autowired
    KeyConverterService converter;
    AtomicLong sequence = new AtomicLong(10000000L);

    @Override
    public String add(String link) {
        lombok.val id = sequence.getAndIncrement();
        lombok.val key = converter.idToKey(id);
        map.put(id, link);
        return key;
    }

    @Override
    public Get getLink(String key) {
        lombok.val id = converter.keyToId(key);
        lombok.val result = map.get(id);
        if (result == null) {
            return new Get.NotFound(key);
        } else {
            return new Get.Link(result);
        }

    }
}
