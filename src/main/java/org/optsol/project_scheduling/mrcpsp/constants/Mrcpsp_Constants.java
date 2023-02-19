package org.optsol.project_scheduling.mrcpsp.constants;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.optsol.project_scheduling.utils.Precedence;

@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@JsonAutoDetect(
    fieldVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE,
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    creatorVisibility = Visibility.NONE
)
public class Mrcpsp_Constants implements IMrcpsp_Constants {

  @JsonProperty
  private int _card_A;
  @JsonProperty
  private Map<Integer, Map<Integer, Map<Integer, Integer>>> _b_imk;
  @JsonProperty
  private Map<Integer, Map<Integer, Map<Integer, Integer>>> _w_imk;
  @JsonProperty
  private Map<Integer, Integer> _B_k;
  @JsonProperty
  private Map<Integer, Integer> _W_k;
  @JsonProperty
  private Map<Integer, Set<Integer>> _M_i;
  @JsonProperty
  private Set<Precedence> _P_bar;
  @JsonProperty
  private Map<Integer, Map<Integer, Integer>> _p_im;
  @JsonProperty
  private int _card_R;
  @JsonProperty
  private int _card_N;
}
