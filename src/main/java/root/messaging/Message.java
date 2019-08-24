package root.messaging;

import root.actors.Costumer;

import java.util.Date;

public class Message {

    private Integer messageId;
    private Integer reservationId ;
    private String roomNumber;
    private String messageContent ;
    private Date submittingTime ;
    private String messageStatus ;
    private Costumer costumer;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getSubmittingTime() {
        return submittingTime;
    }

    public void setSubmittingTime(Date submittingTime) {
        this.submittingTime = submittingTime;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}
