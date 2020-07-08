package logic.factory;

import logic.Evaluator;

public interface EvaluatorFactory {

    Evaluator createEvaluator(String name,int mark);
}
