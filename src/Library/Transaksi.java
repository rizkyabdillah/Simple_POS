package Library;

public class Transaksi {
    private String barcode, nama;
    private int jmlBarang, hrgJual, subTotal, diskon;

    public Transaksi(String bar, String nm, int jml, int hrg, int sub, int dis) {
        this.barcode = bar;
        this.nama = nm;
        this.jmlBarang = jml;
        this.hrgJual = hrg;
        this.subTotal = sub;
        this.diskon = dis;
    }

    public Transaksi() {

    }

    public void addBarcode(String kd) {
        this.barcode = kd;
    }

    public void addNama(String jdl) {
        this.nama = jdl;
    }

    public void addJumlah(int bnd) {
        this.jmlBarang = bnd;
    }

    public void addHarga(int gnre) {
        this.hrgJual = gnre;
    }
    
    public void addSubTotal(int gnre) {
        this.subTotal = gnre;
    }
    
    public void addDiskon(int gnre) {
        this.diskon = gnre;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getNama() {
        return this.nama;
    }

    public int getJumlah() {
        return this.jmlBarang;
    }

    public int getHarga() {
        return this.hrgJual;
    }
    
    public int getSubTotal() {
        return this.subTotal;
    }
    
    public int getDiskon() {
        return this.diskon;
    }
}
