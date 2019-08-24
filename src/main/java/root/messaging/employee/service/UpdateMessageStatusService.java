package root.messaging.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.messaging.dao.MessageDAO;

public class UpdateMessageStatusService {

    @Autowired
    MessageDAO messageDAO;


    public void updateStatus(Integer messageId, String status){

        messageDAO.updateStatus(messageId, status);

    }
}
