package Objetos;

public class Insumos {
    private int id;
    private String[] insumos={"mancuernas","pesas 5kg","barras","pesas 10kg"};
    private int[] precios={12000,3500,1000,7000};
    private int[] stock={15,20,16,20};

    public Insumos (){

    }

    public Insumos(int id, String[] insumos, int[] precios, int[] stock) {
        this.id = id;
        this.insumos = insumos;
        this.precios = precios;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getInsumos() {
        return insumos;
    }

    public void setInsumos(String[] insumos) {
        this.insumos = insumos;
    }

    public int[] getPrecios() {
        return precios;
    }

    public void setPrecios(int[] precios) {
        this.precios = precios;
    }

    public int[] getStock() {
        return stock;
    }

    public void setStock(int[] stock) {
        this.stock = stock;
    }
    //añade un adicional al valor del producto
    public int anadirAdicional( int valor,int adicional){
        return valor+adicional;}
}
