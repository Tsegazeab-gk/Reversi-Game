package controller.builder;

import game.GamePanel;
import player.Player;
import services.network.ConnectedUser;
import services.network.Message;

public class GamePanelBuilder {
    public static class Builder {
        private Player playerOne;
        private Player playerTwo;
        private ConnectedUser connectedUser;

        public Builder() {

        }

        public Builder setPlayerOne(Player playerOne) {
            this.playerOne = playerOne;
            return this;
        }

        public Builder setPlayerTwo(Player playerTwo) {
            this.playerTwo = playerTwo;
            return this;
        }

        public Builder setConnectedUser(ConnectedUser connectedUser) {
            this.connectedUser = connectedUser;
            return this;
        }

        public Builder setFormat(String row, String column){
            Message.setMessageFormat(row, column);
            return this;
        }

        public GamePanel build() {
            GamePanel gamePanel = new GamePanel();
            gamePanel.getController().setPlayer1(playerOne);
            gamePanel.getController().setPlayer2(playerTwo);
            if (connectedUser != null) {
                connectedUser.setGameConnection(gamePanel.getController());
                gamePanel.getController().setConnectedUser(connectedUser);
                connectedUser.startListining();
            }
            return gamePanel;
        }

    }

    public GamePanelBuilder() {

    }
}
