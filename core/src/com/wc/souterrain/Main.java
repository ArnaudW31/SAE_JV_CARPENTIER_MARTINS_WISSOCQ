import com.wc.souterrain.*;

class Main {
  public static void main(String[] args) {
    Player arnaud = new Player();

    Consommable oeil_cyclope = new Consommable();
    oeil_cyclope.setName("Oeil de Cyclope");
    oeil_cyclope.setDescription("Une chance sur deux de gagner 15% de degats ou en perdre 5%");

    Consommable saucisson_de_centaure = new Consommable();
    saucisson_de_centaure.setName("Saucisson de Centaure");
    saucisson_de_centaure.setDescription("Rend 75 pv");

    Consommable pain = new Consommable();
    pain.setName("Pain");
    pain.setDescription("Rend 25 pv");

    Consommable vin = new Consommable();
    vin.setName("Vin");
    vin.setDescription("Rend 50 pv");

    Consommable excrément_de_minotaure = new Consommable();
    excrément_de_minotaure.setName("Excrément de Minotaure");
    excrément_de_minotaure.setDescription("Réduit la précision de 10%");

    Consommable tête_de_méduse = new Consommable();
    tête_de_méduse.setName("Tête de méduse");
    tête_de_méduse.setDescription("Réduit la défense de la cible de 20%");


    arnaud.setName("arnaud");
    arnaud.setDescription("C'est un mec cool");
    arnaud.setHp(1000);
    arnaud.setDmg(100);
    arnaud.setDef(50);
    arnaud.setCrit(50);
    arnaud.setSpeed(100);
    arnaud.setPrecision(100);

    System.out.println("\n");

    Player simon = new Player();
    simon.setName("simon");


    arnaud.addToInventory(tête_de_méduse);
    arnaud.addToInventory(saucisson_de_centaure);
    arnaud.showInventory();
    simon.showDef();
    arnaud.useInventory(tête_de_méduse,arnaud,simon);
    arnaud.showInventory();

  }
}