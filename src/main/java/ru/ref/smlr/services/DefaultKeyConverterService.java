package ru.ref.smlr.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultKeyConverterService implements KeyConverterService {

    static char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();

    private static final Map<Character, Long> charToLong;
    static {
        charToLong = new ConcurrentHashMap<>();
        for(int i = 0; i<chars.length; i++){
            Long c = (long) i;
            charToLong.put(chars[i], c);
        }
    }

    @Override
    public String idToKey(long id) {
        long n = id;
        StringBuilder builder = new StringBuilder();
        while (n != 0L) {
            builder.append(chars[(int) (n % chars.length)]);
            n /= chars.length;
        }
        return builder.reverse().toString();
    }

    @Override
    public Long keyToId(String key) {
        char[] charsKey = key.toCharArray();
        List <Long> listLong = new ArrayList<>();
        for (char i: charsKey) {
             listLong.add(charToLong.get(i));
        }
        return listLong.stream().reduce(0L, (a, b) -> a * chars.length + b);
    }
}
