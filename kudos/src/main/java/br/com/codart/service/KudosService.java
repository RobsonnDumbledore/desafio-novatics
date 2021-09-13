package br.com.codart.service;

import java.util.*;
import java.math.BigDecimal;
import br.com.codart.vo.KudosVO;
import br.com.codart.enums.KudosEnum;
import br.com.codart.utils.MoneyUtils;
import org.springframework.stereotype.Service;

@Service
public class KudosService {

  private List<KudosVO> decomposePoints(Integer points) {
    List<KudosEnum> kudos = KudosEnum.sorting();
    List<KudosVO> KudosVO = new ArrayList<>();
    for (KudosEnum value : kudos) {
      KudosVO.add(new KudosVO(
              value.getLabel(),
              value.getPoint(),
              value.getValue(),
              (points / value.getPoint()))
      );
      points = points % value.getPoint();
    }
    return KudosVO;
  }

  private Integer calculateBalance(List<KudosVO> data) {
    int amount = 0;
    for (KudosVO response : data) {
      amount += response.getValue() * response.getPraiseMultiplier();
    }
    return amount;
  }

  private String writeLiteralBalance(Integer amount) {
    return MoneyUtils.get(new BigDecimal(amount),
            "real",
            "reais",
            "centavo",
            "centavos");
  }

  private List<String> groupCompliments(List<KudosVO> responses) {
    List<String> kudos = new ArrayList<>();
    responses.forEach(c -> {
      for (int i = 0; i < c.getPraiseMultiplier(); i++) {
        kudos.add(c.getKudos());
      }
    });
    return kudos;
  }

  private String formatCompliments(List<String> compliments) {
    return compliments.toString()
            .replace("[", "")
            .replace("]", "");
  }

  public String showMessage(Integer points) {
    List<KudosVO> kudos = decomposePoints(points);
    List<String> compliments = groupCompliments(kudos);
    Integer balance = calculateBalance(kudos);
    String literalBalance = writeLiteralBalance(balance);
    String list = formatCompliments(compliments);

    StringBuilder builder = new StringBuilder("Você recebeu ")
            .append(literalBalance)
            .append(" em retorno aos kudos ")
            .append(list)
            .append("!");
    return builder.toString();
  }

}
