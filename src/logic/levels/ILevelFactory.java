package logic.levels;

public interface ILevelFactory {

    ILevelStrategy  createEasyLevel(int mark);
    ILevelStrategy  createMediumLevel(int mark, int depth);
    ILevelStrategy  createDifficultLevel(int mark, int depth,boolean isFirstUser);

}
