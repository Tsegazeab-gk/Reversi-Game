package controller;

import services.network.Client;
import services.network.ConnectedUser;
import services.network.IConnection;
import services.network.Server;
import ui.RemoteOptionScreen;
import util.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteOptionController implements IConnection {
    private RemoteOptionScreen remoteOptionScreen;
    private ConnectedUser connectedUser;

    public RemoteOptionController(RemoteOptionScreen remoteOptionScreen) {
        this.remoteOptionScreen = remoteOptionScreen;

        remoteOptionScreen.getBtnSendRequest().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                return;
            }
            String ipAddr = remoteOptionScreen.getiPAddressTextField().getText();
            connectedUser = new Server(ipAddr, 5000, this);
            connectedUser.startConnection();
        });

        remoteOptionScreen.getBtnAcceptRequest().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                return;
            }
            String ipAddr = remoteOptionScreen.getiPAddressTextField().getText();
            connectedUser = new Client(ipAddr, 5000, this);
            connectedUser.startConnection();
        });
        remoteOptionScreen.getBtnCancel().addActionListener((ActionEvent event) -> {
            connectedUser.cancelConnection();
            showForm();
        });
    }


    public ConnectedUser getConnectedUser() {
        return connectedUser;
    }

    @Override
    public void connected(boolean state, String msg) {
        if (state == true) {
            showOptions();
        } else {
            showForm();
            if (msg != null)
                remoteOptionScreen.getLblErrorMsg().setText(msg.toUpperCase());
            if (msg.contains("closed")) {
                remoteOptionScreen.getLblErrorMsg().setForeground(Color.WHITE);
            } else {
                remoteOptionScreen.getLblErrorMsg().setForeground(Color.RED);
            }
        }
    }

    private boolean canChangeUIState() {
        String ipAddr = remoteOptionScreen.getiPAddressTextField().getText();
        if (!Utils.validateIPAddress(ipAddr)) {
            remoteOptionScreen.getLblErrorMsg().setText("IP Address is not valid");
            return false;
        }
        remoteOptionScreen.getLblErrorMsg().setText("");
        //
        remoteOptionScreen.getLoading().setVisible(true);
        remoteOptionScreen.getBtnCancel().setVisible(true);
        remoteOptionScreen.getiPAddressTextField().setEnabled(false);
        //
        remoteOptionScreen.getBtnSendRequest().setVisible(false);
        remoteOptionScreen.getBtnAcceptRequest().setVisible(false);
        remoteOptionScreen.repaint();
        return true;
    }

    private void showForm() {
        remoteOptionScreen.getBtnSendRequest().setVisible(true);
        remoteOptionScreen.getBtnAcceptRequest().setVisible(true);
        remoteOptionScreen.getiPAddressTextField().setVisible(true);
        remoteOptionScreen.getLblErrorMsg().setVisible(true);
        remoteOptionScreen.getiPAddressTextField().setEnabled(true);
        //
        remoteOptionScreen.getLoading().setVisible(false);
        remoteOptionScreen.getBtnCancel().setVisible(false);
        remoteOptionScreen.getPlayerOptionPanel().setVisible(false);
        remoteOptionScreen.repaint();
    }

    private void showOptions() {
        remoteOptionScreen.getPlayerOptionPanel().setVisible(true);
        //
        remoteOptionScreen.getLoading().setVisible(false);
        remoteOptionScreen.getBtnCancel().setVisible(false);
        remoteOptionScreen.getiPAddressTextField().setEnabled(false);
        //
        remoteOptionScreen.getBtnSendRequest().setVisible(false);
        remoteOptionScreen.getBtnAcceptRequest().setVisible(false);
        remoteOptionScreen.repaint();
    }

}
