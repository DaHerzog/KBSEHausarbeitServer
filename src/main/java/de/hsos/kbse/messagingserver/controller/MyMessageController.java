/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.messagingserver.controller;

import de.hsos.kbse.messaging.entities.MyMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author davidherzog
 */
@ApplicationScoped
public class MyMessageController {
    
    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory myConnectionFactory;
    
    @Resource(lookup="jms/myTopic")
    private Topic myTopic;
    
    private Connection myCon;
    private Session mySession;
    private MessageProducer myProducer;
    
    public MyMessageController() {
        
    }
    
    public void processIncomingMessage(Message incMsg) {
        if (incMsg instanceof ObjectMessage) {
            try {
                ObjectMessage objectMsg =(ObjectMessage)incMsg;
                MyMessage myMsg = (MyMessage)objectMsg.getObject();
                
                System.out.println(myMsg.getMessage());
                
            } catch (JMSException ex) {
                Logger.getLogger(MyMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("jap");
        }
    }
    
}
