package com.alex90bar.pastebox.repository;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PasteboxEntity {

  private int id;
  private String data;
  private String hash;
  private LocalDateTime lifetime;
  private boolean isPublic;

}
