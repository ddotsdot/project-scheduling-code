package org.optsol.project_scheduling.mrcpsp.see.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.sub.IConstants_G_bar;
import org.optsol.project_scheduling.mrcpsp.see.variables.DecisionVars_See;

public class SEE_VFa_EliminateAssignments<CONST extends IConstants_E & IConstants_A & IConstants_M_i & IConstants_G_bar>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public SEE_VFa_EliminateAssignments() {
    super("i", "m", "e");
  }

  @Override
  protected Collection<ConstraintKey> createKeys(CONST constants) {
    Set<ConstraintKey> indexes = new HashSet<>();
    for (int i : constants.get_A()) {
      int cardAncestors = constants.get_A_bar(i).size();
      int cardDescendants = constants.get_D_bar(i).size();
      for (int m : constants.get_M_i(i)) {
        for (int e : constants.get_E()) {
          if (e != constants.get_card_A()) {
            if (e < cardAncestors || e >= constants.get_card_A() - cardDescendants) {
              indexes.add(new ConstraintKey(i, m, e));
            }
          }
        }
      }
    }
    return indexes;
  }

  @Override
  protected void configureConstraint(
      MPConstraint constraint,
      CONST constants,
      AbstractVariableManager<MPSolver, MPVariable> variables,
      ConstraintKey index)
      throws Exception {
    int i = index.get("i");
    int m = index.get("m");
    int e = index.get("e");

    MPVariable var = variables.getVar(DecisionVars_See.x, i, m, e);
    var.setLb(0.);
    var.setUb(0.);
  }
}
