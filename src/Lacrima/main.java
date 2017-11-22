package Lacrima;

public class main {

    public static void main( String[] args ) {

    VecteurCreux t = new VecteurCreux (5);





        System.out.println(t.taille);
        System.out.println(t.nbElement);
        System.out.println(t.get (1));
        System.out.println(t.get (2));
        System.out.println(t.get (3));
        System.out.println(t.get (4));
        System.out.println(t.get (5));
        System.out.println();
        t.set(3,33);



        System.out.println(t.get (1));
        System.out.println(t.get (2));
        System.out.println(t.get (3));
        System.out.println(t.get (4));
        System.out.println(t.get (5));


        System.out.println(t.taille);
        System.out.println(t.nbElement);




        double [] t1 = {1};
        double [] t2 = {1};
        VecteurCreux v1 = new VecteurCreux( t1 );
        VecteurCreux v2 = new VecteurCreux( t2 );

        VecteurCreux r = v1.addition( v2 );






    }
}
