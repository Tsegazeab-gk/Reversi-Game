package logic.evaluatorfactory;

import logic.StatePattern.Evaluator;

public interface IEvaluatorFactory {

    Evaluator createDynamicEvaluator(int mark);
    Evaluator createKillerEvaluator( int mark);

}
