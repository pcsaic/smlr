package ru.ref.smlr.services;

public interface KeyConverterService {

    String idToKey(long id);

    Long keyToId(String key);
}
