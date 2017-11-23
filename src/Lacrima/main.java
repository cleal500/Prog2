package Lacrima;

import java.util.Arrays;

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


        double[] t4 = {2, 0, 0, 0, 10, 0, 0, 0, 0, 20};
        VecteurCreux v = new VecteurCreux (t4);

        System.out.println (t4.toString ());
        System.out.println ();
        System.out.println (Arrays.toString (t4));

    }
}
