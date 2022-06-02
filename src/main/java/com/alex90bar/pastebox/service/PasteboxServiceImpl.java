package com.alex90bar.pastebox.service;

import com.alex90bar.pastebox.api.request.PasteboxRequest;
import com.alex90bar.pastebox.api.request.PublicStatus;
import com.alex90bar.pastebox.api.response.PasteboxResponse;
import com.alex90bar.pastebox.api.response.PasteboxUrlResponse;
import com.alex90bar.pastebox.repository.PasteboxEntity;
import com.alex90bar.pastebox.repository.PasteboxRepositoryMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {

  private String host = "http://pastebox.com";
  private int publicListSize = 10;

  private final PasteboxRepositoryMap repository;
  private AtomicInteger idGenerator = new AtomicInteger(0);

  @Override
  public PasteboxResponse getByHash(String hash) {
    PasteboxEntity pasteboxEntity = repository.getByHash(hash);
    return new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic());
  }

  @Override
  public List<PasteboxResponse> getFirstPublicPasteboxes() {
    List<PasteboxEntity> list = repository.getListOfPublicAndAlive(publicListSize);

    return list.stream().map(pasteboxEntity ->
      new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic()))
        .collect(Collectors.toList());
  }

  @Override
  public PasteboxUrlResponse create(PasteboxRequest request) {
    int hash = generateId();
    PasteboxEntity pasteboxEntity = new PasteboxEntity();
    pasteboxEntity.setData(request.getData());
    pasteboxEntity.setId(hash);
    pasteboxEntity.setHash(Integer.toHexString(hash));
    pasteboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
    pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
    repository.add(pasteboxEntity);

    return new PasteboxUrlResponse(host + "/" + pasteboxEntity.getHash());
  }

  private int generateId() {
    return idGenerator.getAndIncrement();
  }
}
