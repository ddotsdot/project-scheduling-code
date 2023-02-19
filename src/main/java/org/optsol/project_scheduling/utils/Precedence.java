package org.optsol.project_scheduling.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonAutoDetect(
    fieldVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE,
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    creatorVisibility = Visibility.NONE
)
public class Precedence extends ImmutableIntPair {

  public Precedence(int i, int j) {
    super(i, j);
  }

  public int get_i() {
    return super.getFirst();
  }

  public int get_j() {
    return super.getSecond();
  }

}
