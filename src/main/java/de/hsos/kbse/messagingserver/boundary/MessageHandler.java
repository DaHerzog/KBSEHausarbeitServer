/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.messagingserver.boundary;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

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
    
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory myConnectionFactory;
    
    @Resource(lookup="jms/myTopic")
    private Topic myTopic;
    
    private Connection myCon;
    private Session mySession;
    private MessageProducer myProducer;
    
    public MessageHandler() {
        
    }
    
    public void onMessage(Message inMsg) {
        System.out.println("onMessage wurde aufgerufen!");
    }
    
}
