package Lacrima;

public class main {

    public static void main( String[] args ) {
        double[] k = {1, 2, 3, 4, 5};
        VecteurCreux t = new VecteurCreux (k);





        System.out.println(t.taille);
        System.out.println(t.nbElement);
        System.out.println(t.get (1));
        System.out.println(t.get (2));
        System.out.println(t.get (3));
        System.out.println(t.get (4));
        System.out.println(t.get (5));
        System.out.println();
        t.set (3, 0);



        System.out.println(t.get (1));
        System.out.println(t.get (2));
        System.out.println(t.get (3));
        System.out.println(t.get (4));
        System.out.println(t.get (5));
        System.out.println (t.taille);
        System.out.println (t.nbElement);

        System.out.println ();
        t.set (3, 99);

        System.out.println (t.get (1));
        System.out.println (t.get (2));
        System.out.println (t.get (3));
        System.out.println (t.get (4));
        System.out.println (t.get (5));
        System.out.println(t.taille);
        System.out.println(t.nbElement);




        double [] t1 = {1};
        double [] t2 = {1};
        VecteurCreux v1 = new VecteurCreux( t1 );
        VecteurCreux v2 = new VecteurCreux( t2 );

        VecteurCreux r = v1.addition( v2 );






    }
}
