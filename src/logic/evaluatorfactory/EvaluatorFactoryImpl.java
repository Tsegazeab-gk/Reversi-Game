package logic.evaluatorfactory;

import logic.StatePattern.EarlyPhase;
import logic.StatePattern.Evaluator;

public class EvaluatorFactoryImpl  {

    private static EvaluatorFactoryImpl factory=new EvaluatorFactoryImpl();

    private EvaluatorFactoryImpl() {
    }

    public static EvaluatorFactoryImpl getFactory() {
        return factory;
    }

    public Evaluator createDynamicEvaluator( int mark) {
  return new EarlyPhase.DynamicEvaluator();
    }

    public Evaluator createKillerEvaluator( int mark) {
        Evaluator evaluator=null;

            if(mark==1) {
                evaluator = new RealtimeEvaluator(new int[][] {
                        {8, 85, -40, 10, 210, 520},
                        {8, 85, -40, 10, 210, 520},
                        {33, -50, -15, 4, 416, 2153},
                        {46, -50, -1, 3, 612, 4141},
                        {51, -50, 62, 3, 595, 3184},
                        {33, -5,  66, 2, 384, 2777},
                        {44, 50, 163, 0, 443, 2568},
                        {13, 50, 66, 0, 121, 986},
                        {4, 50, 31, 0, 27, 192},
                        {8, 500, 77, 0, 36, 299}},
                        new int[] {0, 55, 56, 57, 58, 59, 60, 61, 62, 63});
            }else{
                evaluator = new RealtimeEvaluator(new int[][] {
                        {8, 85, -40, 10, 210, 520},
                        {8, 85, -40, 10, 210, 520},
                        {33, -50, -15, 4, 416, 2153},
                        {46, -50, -1, 3, 612, 4141},
                        {51, -50, 62, 3, 595, 3184},
                        {33, -5,  66, 2, 384, 2777},
                        {44, 50, 163, 0, 443, 2568},
                        {13, 50, 66, 0, 121, 986},
                        {4, 50, 31, 0, 27, 192},
                        {8, 500, 77, 0, 36, 299}},
                        new int[] {0, 55, 56, 57, 58, 59, 60, 61, 62, 63});
            }

        return evaluator;
    }
}
