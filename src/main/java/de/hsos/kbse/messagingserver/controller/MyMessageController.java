/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.messagingserver.controller;

import de.hsos.kbse.messaging.dtos.MyMessageDTO;
import de.hsos.kbse.messagingserver.repositories.MyMessageRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author davidherzog
 */
@ApplicationScoped
public class MyMessageController {
    
    @Inject
    MyMessageRepository msgRepo;
    
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory myConnectionFactory;
    
    @Resource(lookup="jms/myTopic")
    private Topic myTopic;
    
    private Connection myCon;
    private Session mySession;
    private MessageProducer myProducer;
    
    public MyMessageController() {
        
    }
    
        @PostConstruct
    public void init() {
        try {
            myCon = myConnectionFactory.createConnection();
            mySession = myCon.createSession(false, Session.AUTO_ACKNOWLEDGE);
            myProducer = mySession.createProducer(myTopic);
        } catch (JMSException ex) {
            Logger.getLogger(MyMessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void processIncomingMessage(Message incMsg) {
        if (incMsg instanceof ObjectMessage) {
            try {
                ObjectMessage objectMsg =(ObjectMessage)incMsg;
                MyMessageDTO myMsg = (MyMessageDTO)objectMsg.getObject();
                
                msgRepo.persistMessage(myMsg);
                
                TextMessage txtMsg = mySession.createTextMessage(myMsg.getAuthor() + ": " + myMsg.getMessage());
                myProducer.send(txtMsg);
                
                
            } catch (JMSException ex) {
                Logger.getLogger(MyMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
