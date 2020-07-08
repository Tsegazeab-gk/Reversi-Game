package controller;

import ui.RemoteOptionScreen;
import util.Utils;

import java.awt.event.ActionEvent;

public class RemoteOptionController {
    private RemoteOptionScreen remoteOptionScreen;

    public RemoteOptionController(RemoteOptionScreen remoteOptionScreen) {
        this.remoteOptionScreen = remoteOptionScreen;

        remoteOptionScreen.getBtnSendRequest().addActionListener((ActionEvent event) -> {
            String ipAddr = remoteOptionScreen.getiPAddressTextField().getText();
            if (!Utils.validateIPAddress(ipAddr)) {

                return;
            }
            remoteOptionScreen.getLoading().setVisible(true);
            remoteOptionScreen.getBtnCancel().setVisible(true);
            //
            remoteOptionScreen.getBtnSendRequest().setVisible(false);
            remoteOptionScreen.getBtnAcceptRequest().setVisible(false);
            remoteOptionScreen.repaint();
        });

        remoteOptionScreen.getBtnAcceptRequest().addActionListener((ActionEvent event) -> {
            changeUIState();
        });
        remoteOptionScreen.getBtnCancel().addActionListener((ActionEvent event) -> {
            changeUIState();
        });
    }

    private void changeUIState() {
        String ipAddr = remoteOptionScreen.getiPAddressTextField().getText();
        System.out.println("Ip address is not valid");
        if (!Utils.validateIPAddress(ipAddr)) {
            return;
        }

        remoteOptionScreen.getLoading().setVisible(true);
        remoteOptionScreen.getBtnCancel().setVisible(true);
        //
        remoteOptionScreen.getBtnSendRequest().setVisible(false);
        remoteOptionScreen.getBtnAcceptRequest().setVisible(false);
        remoteOptionScreen.repaint();
    }
}
