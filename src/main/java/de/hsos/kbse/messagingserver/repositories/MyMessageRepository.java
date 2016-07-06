/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsos.kbse.messagingserver.repositories;

import de.hsos.kbse.messaging.dtos.MyMessageDTO;
import de.hsos.kbse.messaging.entities.MyMessage;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author davidherzog
 */
@ApplicationScoped
@Transactional
public class MyMessageRepository {
    
    @PersistenceContext
    EntityManager em;
    
    
    public MyMessageRepository() {
        
    }
    
    public void persistMessage(MyMessageDTO msgDto) {
        
        MyMessage perMsg = new MyMessage();
        
        perMsg.setAuthor(msgDto.getAuthor());
        perMsg.setDateSent(msgDto.getDateSent());
        perMsg.setMessage(msgDto.getMessage());
        
        em.persist(perMsg);
        
    }
    
}
