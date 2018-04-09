public class Event{
    private int id, eventTypeId;
    private Date date;

    public Event(int id, int eventTypeId, Data date){
        this.id = id;
        this.eventTypeId = eventTypeId;
        this.date = date;
    }
}