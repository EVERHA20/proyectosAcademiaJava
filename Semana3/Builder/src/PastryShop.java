
public class PastryShop {

    public static void main(String[] args) {
        Builder<Pastry> pastryBuilder = new PastryBuilder();
        Kitchen kitchen = new Kitchen();
        
        kitchen.preparePastry(pastryBuilder, PastryType.CINNAMON_ROLL);
        Pastry cr = pastryBuilder.getResult();
        System.out.println(cr);
        
        System.out.println("////////////////////////////////");

        kitchen.preparePastry(pastryBuilder, PastryType.CONCHA);
        Pastry concha = pastryBuilder.getResult();
        System.out.println(concha);
        
        System.out.println("////////////////////////////////");

        kitchen.preparePastry(pastryBuilder, PastryType.MUFFIN);
        Pastry muffin = pastryBuilder.getResult();
        System.out.println(muffin);
        
        System.out.println("////////////////////////////////");

        kitchen.preparePastry(pastryBuilder, PastryType.BROWNIE);
        Pastry brownie = pastryBuilder.getResult();
        System.out.println(brownie);
    }
}