package logic.levels;

public class LevelFactory {


	static LevelFactory factory=new LevelFactory();
	
	private LevelFactory() {
		
	}
	
	public static LevelFactory getFactory() {
		
		 return factory;
	}
	
	public  ILevelStrategy  createEasyLevel(int mark) {
		return  new EasyLevel(mark);
	}
	
	public  ILevelStrategy  createMediumLevel(int mark, int depth) {
		return  new MediumLevel(mark,depth);
	}
	
	public  ILevelStrategy  createDifficultLevel(int mark, int depth,boolean isFirstUser) {
		
	return new DifficultLevel(mark,depth,isFirstUser);
	}

	public  ILevelStrategy  createSuperDifficultLevel(int mark, int depth) {

		return new SuperDifficultLevel(mark,depth);
	}
}
