package ru.ref.smlr.service;

import org.junit.Test;
import ru.ref.smlr.services.DefaultKeyConverterService;
import ru.ref.smlr.services.KeyConverterService;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DefaultKeyConverterServiceTest {

    KeyConverterService service = new DefaultKeyConverterService();

    @Test
    public void givenIdMustBeConvertableBothWays() {

        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            Long initialId = Math.abs(rand.nextLong());
            String key = service.idToKey(initialId);
            Long id = service.keyToId(key);
            assertEquals(initialId, id);
        }
    }
}
