package controller;

import services.network.*;
import ui.RemoteTCPOptionScreen;
import util.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteTCPOptionController implements IConnection {
    private RemoteTCPOptionScreen remoteTCPOptionScreen;
    private ConnectedUser connectedUser;

    public RemoteTCPOptionController(RemoteTCPOptionScreen remoteTCPOptionScreen) {
        this.remoteTCPOptionScreen = remoteTCPOptionScreen;

        remoteTCPOptionScreen.getBtnSendRequest().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                remoteTCPOptionScreen.repaint();
                return;
            }
            String ipAddr = remoteTCPOptionScreen.getiPAddressTextField().getText();
            connectedUser = FactoryConnection.createTCPServerConnection(ipAddr, 5000, this);
            connectedUser.startConnection();
        });

        remoteTCPOptionScreen.getBtnAcceptRequest().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                remoteTCPOptionScreen.repaint();
                return;
            }
            String ipAddr = remoteTCPOptionScreen.getiPAddressTextField().getText();
            connectedUser = FactoryConnection.createTCPClientConnection(ipAddr, 5000, this);
            connectedUser.startConnection();
        });
        remoteTCPOptionScreen.getBtnCancel().addActionListener((ActionEvent event) -> {
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
                remoteTCPOptionScreen.getLblErrorMsg().setText(msg.toUpperCase());
            if (msg.contains("closed")) {
                remoteTCPOptionScreen.getLblErrorMsg().setForeground(Color.WHITE);
            } else {
                remoteTCPOptionScreen.getLblErrorMsg().setForeground(Color.RED);
            }
        }
    }

    private boolean canChangeUIState() {
        String ipAddr = remoteTCPOptionScreen.getiPAddressTextField().getText();
        if (!Utils.validateIPAddress(ipAddr)) {
            remoteTCPOptionScreen.getLblErrorMsg().setText("IP Address is not valid");
            return false;
        }
        remoteTCPOptionScreen.getLblErrorMsg().setText("");
        //
        remoteTCPOptionScreen.getLoading().setVisible(true);
        remoteTCPOptionScreen.getBtnCancel().setVisible(true);
        remoteTCPOptionScreen.getiPAddressTextField().setEnabled(false);
        //
        remoteTCPOptionScreen.getBtnSendRequest().setVisible(false);
        remoteTCPOptionScreen.getBtnAcceptRequest().setVisible(false);
        remoteTCPOptionScreen.repaint();
        return true;
    }

    private void showForm() {
        remoteTCPOptionScreen.getBtnSendRequest().setVisible(true);
        remoteTCPOptionScreen.getBtnAcceptRequest().setVisible(true);
        remoteTCPOptionScreen.getiPAddressTextField().setVisible(true);
        remoteTCPOptionScreen.getLblErrorMsg().setVisible(true);
        //
        remoteTCPOptionScreen.getLoading().setVisible(false);
        remoteTCPOptionScreen.getBtnCancel().setVisible(false);
        remoteTCPOptionScreen.getPlayerOptionPanel().setVisible(false);
        remoteTCPOptionScreen.repaint();
    }

    private void showOptions() {
        remoteTCPOptionScreen.getPlayerOptionPanel().setVisible(true);
        //
        remoteTCPOptionScreen.getLoading().setVisible(false);
        remoteTCPOptionScreen.getBtnCancel().setVisible(false);
        remoteTCPOptionScreen.getiPAddressTextField().setEnabled(false);
        //
        remoteTCPOptionScreen.getBtnSendRequest().setVisible(false);
        remoteTCPOptionScreen.getBtnAcceptRequest().setVisible(false);
        remoteTCPOptionScreen.repaint();
    }

}
