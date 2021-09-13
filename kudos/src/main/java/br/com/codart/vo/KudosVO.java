package br.com.codart.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KudosVO {
  private String kudos;
  private Integer point;
  private Integer value;
  private Integer praiseMultiplier;
}
