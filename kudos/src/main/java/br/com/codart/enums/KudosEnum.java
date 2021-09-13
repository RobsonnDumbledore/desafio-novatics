package br.com.codart.enums;

import lombok.Getter;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum KudosEnum {

  SUPER("SUPER", 100, 25),
  GREAT("GREAT", 50, 15),
  GOOD("GOOD", 20, 8),
  NICE("NICE", 10, 5),
  OK("OK", 5, 2);

  private final String label;
  private final Integer point;
  private final Integer value;

  public static List<KudosEnum> sorting() {
    List<KudosEnum> kudos = new ArrayList<>(Arrays.asList(KudosEnum.values()));
    kudos.sort(Comparator.comparing(KudosEnum::getPoint).reversed());
    return kudos;
  }
}
