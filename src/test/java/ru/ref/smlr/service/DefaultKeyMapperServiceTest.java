package ru.ref.smlr.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.ref.smlr.services.DefaultKeyMapperService;
import ru.ref.smlr.services.KeyConverterService;
import ru.ref.smlr.services.KeyMapperService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DefaultKeyMapperServiceTest {

    @InjectMocks
    KeyMapperService service = new DefaultKeyMapperService();

    private String KEY = "aAbBcCdD";
    private String LINK_A = "http://www.eveonline.com";
    private String LINK_B = "http://www.wow.ru";

    @Mock
    KeyConverterService converter;
    private String KEY_A = "abc";
    private String KEY_B = "cde";
    private Long ID_A = 10000000L;
    private Long ID_B = 10000001L;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(converter.keyToId(KEY_A)).thenReturn(ID_A);
        Mockito.when(converter.idToKey(ID_A)).thenReturn(KEY_A);
        Mockito.when(converter.keyToId(KEY_B)).thenReturn(ID_B);
        Mockito.when(converter.idToKey(ID_B)).thenReturn(KEY_B);
    }

    @Test
    public void clientCanAddLinks() {
        lombok.val keyA = service.add(LINK_A);
        assertEquals(new KeyMapperService.Get.Link(LINK_A), service.getLink(keyA));
        lombok.val keyB = service.add(LINK_B);
        assertEquals(new KeyMapperService.Get.Link(LINK_B), service.getLink(keyB));
        assertNotEquals(keyA, keyB);
    }

    @Test
    public void clientCanNotTakeLinkIfKeyIsNotFoundInService() {
        assertEquals(new KeyMapperService.Get.NotFound(KEY), service.getLink(KEY));
    }


}
