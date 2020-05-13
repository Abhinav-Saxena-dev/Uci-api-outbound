package com.samagra.InterImp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audio implements TypeInterface{
  public String url;
  public String caption;
}
