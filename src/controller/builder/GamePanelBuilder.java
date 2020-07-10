package controller.builder;

import game.GamePanel;
import player.GamePlayer;
import services.network.ConnectedUser;

public class GamePanelBuilder {
    public static class Builder {
        private GamePlayer playerOne;
        private GamePlayer playerTwo;
        private ConnectedUser connectedUser;

        public Builder() {

        }

        public Builder setPlayerOne(GamePlayer playerOne) {
            this.playerOne = playerOne;
            return this;
        }

        public Builder setPlayerTwo(GamePlayer playerTwo) {
            this.playerTwo = playerTwo;
            return this;
        }

        public Builder setConnectedUser(ConnectedUser connectedUser) {
            this.connectedUser = connectedUser;
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
