package logic.factory;


import logic.StatePattern.Evaluator;

public interface EvaluatorFactory {

    Evaluator createEvaluator(String name, int mark);
}
