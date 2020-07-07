package AplikacjaBD;

public class Row {
    private  String telefon;
    private String  imie;

    public Row(String telefon, String imie, String nazwisko, int id) {
        this.telefon = telefon;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
    }

    private String nazwisko;
    private int id;

    public String getTelefon() { return telefon; }

    public String getImie() { return imie; }

    public String getNazwisko() { return nazwisko; }

    public int getId() { return id; }


}
