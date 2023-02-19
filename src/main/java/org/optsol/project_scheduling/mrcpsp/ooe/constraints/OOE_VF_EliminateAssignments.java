package org.optsol.project_scheduling.mrcpsp.ooe.constraints;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.optsol.jdecor.core.AbstractConstraintManager;
import org.optsol.jdecor.core.AbstractVariableManager;
import org.optsol.jdecor.ortools.AbstractOrtoolsConstraintManager;
import org.optsol.project_scheduling.mrcpsp.constants.shared.events.IConstants_E;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_A;
import org.optsol.project_scheduling.mrcpsp.constants.sub.IConstants_M_i;
import org.optsol.project_scheduling.mrcpsp.ooe.constants.sub.IConstants_G_bar;
import org.optsol.project_scheduling.mrcpsp.ooe.variables.DecisionVars_Ooe;

public class OOE_VF_EliminateAssignments<CONST extends
    IConstants_E & IConstants_A & IConstants_M_i & IConstants_G_bar>
    extends AbstractOrtoolsConstraintManager<CONST> {

  public OOE_VF_EliminateAssignments() {
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
      AbstractConstraintManager<MPSolver, CONST, MPVariable, MPConstraint>.ConstraintKey constraintKey)
      throws Exception {

    int i = constraintKey.get("i");
    int m = constraintKey.get("m");
    int e = constraintKey.get("e");

    MPVariable var = variables.getVar(DecisionVars_Ooe.z, i, m, e);
    var.setLb(0.);
    var.setUb(0.);
  }
}
