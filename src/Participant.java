public class Participant {
    String name;
    Participant recipient;

    public Participant(String name){
        this.name = name;
    }

    public void setRecipient (Participant recipient){
        this.recipient = recipient;
    }

    public String toString(){
        return (name + " ---> " + recipient.name);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Participant){
            Participant other = (Participant) o;
            return other.name.equals(this.name);
        } else {
            return false;
        }
    }
}
