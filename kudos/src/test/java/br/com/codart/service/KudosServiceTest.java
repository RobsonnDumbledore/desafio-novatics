package br.com.codart.service;

import org.junit.Test;
import java.util.List;
import org.junit.Before;
import java.util.ArrayList;
import br.com.codart.vo.KudosVO;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;


public class KudosServiceTest {

  KudosService kudosService;
  List<KudosVO> kudos;

  @Before
  public void init() {
    kudosService = new KudosService();
    kudos = new ArrayList<>();
  }

  public Method accessible(String name, Class<?> types) throws NoSuchMethodException {
    Method method = KudosService.class.getDeclaredMethod(name, types);
    method.setAccessible(true);
    return method;
  }

  @Test
  public void calculateBalance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = accessible("calculateBalance", List.class);
    kudos.add(new KudosVO("SUPER", 100, 25, 1));
    Integer balance = (Integer) method.invoke(kudosService, kudos);
    assertThat(balance).isEqualTo(25);
  }

  @Test
  public void writeLiteralBalance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = accessible("writeLiteralBalance", Integer.class);
    String balance = (String) method.invoke(kudosService, 100);
    assertThat(balance).isEqualTo("cem reais");
  }

  @Test
  public void groupCompliments() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = accessible("groupCompliments", List.class);
    kudos = new ArrayList<>(List.of(new KudosVO("SUPER", 100, 25, 1)));
    List<String> expectedList = new ArrayList<>(List.of("SUPER"));
    List<String> currentList = (List<String>) method.invoke(kudosService, kudos);
    assertThat(currentList).isEqualTo(expectedList);
  }

  @Test
  public void formatCompliments() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method method = accessible("formatCompliments", List.class);
    List<String> kudos = new ArrayList<>(List.of("GOOD, GOOD"));
    List<String> list = new ArrayList<>(List.of("GOOD, GOOD"));
    String expectedValue = list.toString()
            .replace("[", "")
            .replace("]", "");
    String currentValue = (String) method.invoke(kudosService, kudos);
    assertThat(currentValue).isEqualTo(expectedValue);
  }

  @Test
  public void showMessage() {
    String messageExpect = "Você recebeu vinte e cinco reais em retorno aos kudos SUPER!";
    KudosService service = new KudosService();
    assertThat(service.showMessage(100)).isEqualTo(messageExpect);
  }
}
