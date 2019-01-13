package ru.ref.smlr.service;

import org.junit.Test;
import ru.ref.smlr.services.DefaultKeyMapperService;
import ru.ref.smlr.services.KeyMapperService;

import static org.junit.Assert.assertEquals;

public class DefaultKeyMapperServiceTest {
    KeyMapperService service = new DefaultKeyMapperService();

    private String KEY = "aAbBcCdD";
    private String LINK = "http://www.eveonline.com";
    private String LINK_NEW = "http://www.wow.ru";

    @Test
    public void clientCanNotAddExistingKey() {
        service.add(KEY, LINK);
        assertEquals(new KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW));
        assertEquals(new KeyMapperService.Get.Link(LINK), service.getLink(KEY));
    }

    @Test
    public void clientCanNotTakeLinkIfKeyIsNotFoundInService(){
        assertEquals(new KeyMapperService.Get.NotFound(KEY), service.getLink(KEY));
    }


}
