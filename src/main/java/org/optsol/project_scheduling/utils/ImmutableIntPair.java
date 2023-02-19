/*
 * @author Fath, Philipp
 * @author Sayah, David
 */

package org.optsol.project_scheduling.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImmutableIntPair {

  @JsonProperty
  private int first;
  @JsonProperty
  private int second;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImmutableIntPair immutableIntPair = (ImmutableIntPair) o;

    if (this.first != immutableIntPair.getFirst()) {
      return false;
    }
    return this.second == immutableIntPair.getSecond();
  }

  @Override
  public int hashCode() {
    int result = this.first;
    result = 31 * result + this.second;
    return result;
  }
}
