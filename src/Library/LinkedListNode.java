package Library;

public class LinkedListNode {
    LinkedListNode next;
    LinkedListNode prev;
    Transaksi data;
    
    public LinkedListNode(Transaksi new_data){
        this.data = new_data;
        this.prev = null;
        this.next = null;
    }
    public void set_prev (LinkedListNode other){
        this.prev = other;
        if (other != null) {
            other.next = this;
        }
    }
    public void set_next (LinkedListNode other){
        this.next = other;
        if (other != null) {
            other.prev = this;
        }
    }
}