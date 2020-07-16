//package controller.command;
//
//import controller.command.Command;
//import player.ai.AIPlayerDynamic;
//
//public class PlayDynamicCommand implements Command {
//    private int[][] board;
//    private AIPlayerDynamic playerDynamic;
//
//    public PlayDynamicCommand(int mark, int depth){
//        this.board=board;
//        this.playerDynamic=new AIPlayerDynamic(mark,depth);
//    }
//
//
//    @Override
//    public void execute() {
//        playerDynamic.play(board);
//    }
//
//    @Override
//    public boolean undo() {
//        return false;
//    }
//}
