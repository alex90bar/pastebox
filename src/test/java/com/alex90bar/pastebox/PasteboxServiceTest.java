package com.alex90bar.pastebox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.alex90bar.pastebox.api.response.PasteboxResponse;
import com.alex90bar.pastebox.exception.NotFoundEntityException;
import com.alex90bar.pastebox.repository.PasteboxEntity;
import com.alex90bar.pastebox.repository.PasteboxRepository;
import com.alex90bar.pastebox.repository.PasteboxRepositoryMap;
import com.alex90bar.pastebox.service.PasteboxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PasteboxServiceTest {

  @Autowired
  private PasteboxService pasteboxService;

  @MockBean
  private PasteboxRepositoryMap pasteboxRepository;

  @Test
  public void notExistHash(){
    when(pasteboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
    assertThrows(NotFoundEntityException.class, () -> pasteboxService.getByHash("asdsqwqcdsdfv"));
  }

  @Test
  public void getExistHash(){
    PasteboxEntity entity = new PasteboxEntity();
    entity.setHash("1");
    entity.setData("Data_1");
    entity.setPublic(true);

    when(pasteboxRepository.getByHash("1")).thenReturn(entity);

    PasteboxResponse expected = new PasteboxResponse("Data_1", true);
    PasteboxResponse actual = pasteboxService.getByHash("1");

    assertEquals(expected, actual);
  }

}
