package Library;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LinkedList {

    LinkedListNode head, tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void showTable(JTable table) {
        try {
            int index = 0;
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            LinkedListNode current = head;
            while (current != null) {
                String[] kolom = new String[6];
                kolom[0] = (current.data.getBarcode());
                kolom[1] = (current.data.getNama());
                kolom[2] = (String.valueOf(current.data.getJumlah()));
                kolom[3] = (String.valueOf(current.data.getHarga()));
                kolom[4] = (String.valueOf(current.data.getSubTotal()));
                kolom[5] = (String.valueOf(current.data.getDiskon()));
                model.insertRow(index, kolom);
                current = current.next;
                index++;
            }
        } catch (Exception e) {
            System.err.println("Error addTable : " + e.getMessage());
        }
    }

    public int getTotal() {
        int total = 0;
        LinkedListNode current = head;
        while (current != null) {
            int diskon = ((current.data.getHarga() * current.data.getJumlah()) * current.data.getDiskon() / 100);
            total += ((current.data.getHarga() * current.data.getJumlah()) - diskon);
            current = current.next;
        }
        return total;
    }

    public void push(LinkedListNode new_node) {
        if (this.head == null) {
            this.head = new_node;
            this.tail = new_node;
        } else {
            this.tail.set_next(new_node);
            new_node.set_prev(this.tail);
            this.tail = new_node;
        }
    }

    public void delete(LinkedListNode deleted) {
        if (deleted != null && this.head != null) {
            if (this.head == this.tail && deleted == this.head) {
                this.head = null;
                this.tail = null;
            } else if (deleted == this.head) {
                LinkedListNode new_head = this.head.next;
                this.head.set_next(null);
                new_head.set_prev(null);
                this.head = new_head;
            } else if (deleted == this.tail) {
                LinkedListNode new_tail = this.tail.prev;
                this.tail.set_prev(null);
                new_tail.set_next(null);
                this.tail = new_tail;
            } else {
                LinkedListNode deleted_prev = deleted.prev;
                LinkedListNode deleted_next = deleted.next;
                deleted.set_prev(null);
                deleted.set_next(null);
                deleted_prev.set_next(deleted_next);
            }
        }
    }

    public LinkedListNode findFromIndex(int index) {
        LinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    public void deleteBarang(String value) {
        int in = 0;
        LinkedListNode temp = head;
        while (temp != null) {
            if (temp.data.getBarcode().equals(value)) {
                break;
            }
            temp = temp.next;
        }
        delete(findFromIndex(in));
    }

    public int getQty(String value) {
        int in = 0;
        boolean ketemu = false;
        LinkedListNode temp = head;
        while (temp != null) {
            if (temp.data.getBarcode().equals(value)) {
                ketemu = true;
                break;
            }
            temp = temp.next;
            in++;
        }

        if (ketemu) {
            return findFromIndex(in).data.getJumlah();
        } else {
            return 0;
        }
    }

    public boolean filterBarang(String value, int qty, int sub) {
        int in = 0;
        boolean ketemu = false;
        LinkedListNode temp = head;
        while (temp != null) {
            if (temp.data.getBarcode().equals(value)) {
                ketemu = true;
                break;
            }
            temp = temp.next;
            in++;
        }

        if (ketemu) {
            findFromIndex(in).data.addJumlah(getQty(value) + qty);
            findFromIndex(in).data.addSubTotal(findFromIndex(in).data.getSubTotal() + sub);
        }

        return ketemu;
    }

    public void editBarang(String value, int qty, int sub) {
        int in = 0;
        LinkedListNode temp = head;
        while (temp != null) {
            if (temp.data.getBarcode().equals(value)) {
                break;
            }
            temp = temp.next;
            in++;
        }
        findFromIndex(in).data.addJumlah(qty);
        findFromIndex(in).data.addSubTotal(sub);

    }
    
    LinkedListNode qpop() {
        LinkedListNode taken;
        if(this.head == null) {
            taken = null;
        } else if(this.head == this.tail) {
            taken = this.head;
            this.head = null;
            this.tail = null;
        } else {
            taken = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            taken.next = null;
        }
        return taken;
    }
    
    public void popAll(){
        LinkedListNode current = head;
        while(current != null) {
            current = current.next;
            qpop();
        }
    }

}
