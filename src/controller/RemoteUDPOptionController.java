package controller;

import services.network.ConnectedUser;
import services.network.IConnection;
import services.network.UDPConnection;
import ui.RemoteUDPOptionScreen;
import util.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteUDPOptionController implements IConnection {
    private RemoteUDPOptionScreen remoteUDPOptionScreen;
    private ConnectedUser connectedUser;

    public RemoteUDPOptionController(RemoteUDPOptionScreen remoteUDPOptionScreen) {
        this.remoteUDPOptionScreen = remoteUDPOptionScreen;

        this.remoteUDPOptionScreen.getAddressTextField().setText("localhost");

        this.remoteUDPOptionScreen.getBtnConnection().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                remoteUDPOptionScreen.repaint();
                return;
            }

            String addr = remoteUDPOptionScreen.getAddressTextField().getText();
            String sourcePort = remoteUDPOptionScreen.getSourcePortTextField().getText();
            String destPort = remoteUDPOptionScreen.getDestPortTextField().getText();
            int sourcePortInt = 0, destPortInt = 0;
            try {
                sourcePortInt = Integer.parseInt(sourcePort);
                destPortInt = Integer.parseInt(destPort);
            } catch (NumberFormatException e) {
                remoteUDPOptionScreen.getLblErrorMsg().setText("Invalid Port Format");
                return;
            }
            connectedUser = new UDPConnection(addr, sourcePortInt, destPortInt, this);
            connectedUser.startConnection();

        });
    }

    public ConnectedUser getConnectedUser() {
        return connectedUser;
    }

    private boolean canChangeUIState() {
        String ipAddr = remoteUDPOptionScreen.getAddressTextField().getText();
        String sourcePort = remoteUDPOptionScreen.getSourcePortTextField().getText();
        String destPort = remoteUDPOptionScreen.getDestPortTextField().getText();
        int sourcePortInt = 0, destPortInt = 0;

        if (!Utils.validateIPAddress(ipAddr)) {
            remoteUDPOptionScreen.getLblErrorMsg().setText("IP Address is not valid");
            return false;
        }

        try {
            sourcePortInt = Integer.parseInt(sourcePort);
            destPortInt = Integer.parseInt(destPort);
        } catch (NumberFormatException e) {
            remoteUDPOptionScreen.getLblErrorMsg().setText("Invalid Port Format");
            return false;
        }

//        if (portInt <= 0 || portInt > 65535) {
//            remoteUDPOptionScreen.getLblErrorMsg().setText("Port Number is not valid");
//            return false;
//        }

        remoteUDPOptionScreen.getLblErrorMsg().setText("");
        Utils.setEnabledJPanel(remoteUDPOptionScreen.getFormOptionPanel(), false);
        remoteUDPOptionScreen.repaint();

        return true;
    }

    private void showForm() {
        remoteUDPOptionScreen.getLblErrorMsg().setText("");
        Utils.setEnabledJPanel(remoteUDPOptionScreen.getFormOptionPanel(), true);
        //
        remoteUDPOptionScreen.getPlayerOptionPanel().setVisible(false);
        remoteUDPOptionScreen.repaint();
    }

    private void showOptions() {
        remoteUDPOptionScreen.getLblErrorMsg().setText("");
        remoteUDPOptionScreen.getPlayerOptionPanel().setVisible(true);
        //
        Utils.setEnabledJPanel(remoteUDPOptionScreen.getFormOptionPanel(), false);
        //
        remoteUDPOptionScreen.repaint();
    }

    @Override
    public void connected(boolean state, String msg) {
        if (state == true) {
            showOptions();
        } else {
            showForm();
            if (msg != null)
                remoteUDPOptionScreen.getLblErrorMsg().setText(msg.toUpperCase());
            if (msg.contains("closed")) {
                remoteUDPOptionScreen.getLblErrorMsg().setForeground(Color.WHITE);
            } else {
                remoteUDPOptionScreen.getLblErrorMsg().setForeground(Color.RED);
            }
        }
    }
}
