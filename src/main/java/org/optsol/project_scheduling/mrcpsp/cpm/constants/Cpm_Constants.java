package org.optsol.project_scheduling.mrcpsp.cpm.constants;

import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.optsol.project_scheduling.utils.Precedence;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cpm_Constants implements ICpm_Constants {

  private int _card_A;
  private Map<Integer, Set<Integer>> _M_i;
  private Set<Precedence> _P_bar;
  private Map<Integer, Map<Integer, Integer>> _p_im;
}
