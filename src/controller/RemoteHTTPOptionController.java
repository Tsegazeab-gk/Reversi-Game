package controller;

import services.network.ConnectedUser;
import services.network.HttpConnection;
import services.network.IConnection;
import services.network.UDPConnection;
import ui.RemoteHTTPOptionScreen;
import ui.RemoteTCPOptionScreen;
import util.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RemoteHTTPOptionController implements IConnection {

    private RemoteHTTPOptionScreen remoteHTTPOptionScreen;
    private ConnectedUser connectedUser;

    public RemoteHTTPOptionController(RemoteHTTPOptionScreen remoteHTTPOptionScreen) {
        this.remoteHTTPOptionScreen = remoteHTTPOptionScreen;

        this.remoteHTTPOptionScreen.getBtnConnection().addActionListener((ActionEvent event) -> {
            if (!canChangeUIState()) {
                remoteHTTPOptionScreen.repaint();
                return;
            }

            String addr = remoteHTTPOptionScreen.getAddressTextField().getText();

            connectedUser = new HttpConnection(addr, this);
            connectedUser.startConnection();

        });

    }

    public ConnectedUser getConnectedUser() {
        return connectedUser;
    }

    private boolean canChangeUIState() {
        String url = remoteHTTPOptionScreen.getAddressTextField().getText();

        if (!validateHTTPUrl(url)) {
            return false;
        }

        remoteHTTPOptionScreen.getLblErrorMsg().setText("");
        Utils.setEnabledJPanel(remoteHTTPOptionScreen.getFormOptionPanel(), false);
        remoteHTTPOptionScreen.repaint();
        return true;
    }

    private void showForm() {
        remoteHTTPOptionScreen.getLblErrorMsg().setText("");
        Utils.setEnabledJPanel(remoteHTTPOptionScreen.getFormOptionPanel(), true);
        //
        remoteHTTPOptionScreen.getPlayerOptionPanel().setVisible(false);
        remoteHTTPOptionScreen.repaint();
    }

    private void showOptions() {
        remoteHTTPOptionScreen.getLblErrorMsg().setText("");
        remoteHTTPOptionScreen.getPlayerOptionPanel().setVisible(true);
        //
        Utils.setEnabledJPanel(remoteHTTPOptionScreen.getFormOptionPanel(), false);
        //
        remoteHTTPOptionScreen.repaint();
    }

    @Override
    public void connected(boolean state, String msg) {
        if (state == true) {
            showOptions();
        } else {
            showForm();
            if (msg != null)
                remoteHTTPOptionScreen.getLblErrorMsg().setText(msg.toUpperCase());
            if (msg.contains("closed")) {
                remoteHTTPOptionScreen.getLblErrorMsg().setForeground(Color.WHITE);
            } else {
                remoteHTTPOptionScreen.getLblErrorMsg().setForeground(Color.RED);
            }
        }
    }

   private boolean validateHTTPUrl(final String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
            remoteHTTPOptionScreen.getLblErrorMsg().setText(e.getMessage());
            return false;
        } catch (IOException e) {
            remoteHTTPOptionScreen.getLblErrorMsg().setText(e.getMessage());
            return false;
        }

        return true;
    }
}
