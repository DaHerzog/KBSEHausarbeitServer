/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.messagingserver.boundary;


import de.hsos.kbse.messagingserver.controller.MyMessageController;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Message;

/**
 *
 * @author davidherzog
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
@ApplicationScoped
public class MessageHandler {
    
    @Inject
    MyMessageController msgCtrl;
    
    public MessageHandler() {
        
    }
    
    public void onMessage(Message inMsg) {
        msgCtrl.processIncomingMessage(inMsg);
    }
    
}
