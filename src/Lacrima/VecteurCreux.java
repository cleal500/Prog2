package Lacrima;

import java.util.Iterator;

/*
  javadoc généré avec : -locale fr -header '<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>'
 */

/**
 * Un VecteurCreux est une classe pour représenter les vecteurs de grande taille,
 * mais contenant plusieurs valeurs a zéro.
 * Ils sont représentés à l'aide de liste chaine où chaque Maillon (case) contient seulement
 * un élément qui n'est pas a zéro.  Ces Maillon vont donc aussi contenir l'indice de la
 * case représenté.  Par exemple, le vecteur (0, 0, 0, 1, 0, 2, 0, 0 ) serait représenté
 * par une liste contenant deux Maillons : un pour la case '4' contenant la valeur '1' et
 * un pour la case '6' contenant la valeur '2'.  La première case d'un vecteur creux est donc
 * représente par l'indice '1'.  Les valeurs contenues dans le vecteur sont représentées par
 * des doubles.
 * <p>
 * Les VecteursCreux sont des structures de taille fixe : le nombre de cases ne peut pas varier
 * et est déterminé lors de la construction du vecteur.  Par contre, le nombre de cases qui ne
 * contiennent pas zéro peuvent varier, donc la taille de la liste chaine peut varier.
 * <p>
 * Cette structure est Iterable, elle donne accès au service de construction d'un Iterator.
 * L'itérateur permet de parcourir chaque case du vecteur.  Autant les cases contenant zéro
 * que les autres.
 * <p>
 * Services offerts :
 * <p>
 * 'get' et 'set' permettent de lire et écrire dans les cases du vecteur.
 * La méthode 'set' peu donc modifier la taille de la liste chaine représentant les
 * valeur non-zéro.  Il y a quatre cas possibles :
 * <p>
 * 1- La case où nous voulons écrire ne contient pas zéro et la valeur à écrire n'est pas
 * zéro, dans ce cas, la valeur à écrire remplace la valeur dans la case de la liste chaine.
 * <p>
 * 2- La case où nous voulons écrire contient zéro et la valeur à écrire n'est pas zéro,
 * dans ce cas, une case est insérée dans la liste chaine avec la valeur écrite.
 * <p>
 * 3- La case où nous voulons écrire ne contient pas zéro et la valeur à écrire est zéro,
 * dans ce cas, la case contenant la valeur dans la liste chaine est supprimée.
 * <p>
 * 4- La case où nous voulons écrire contient zéro et la valeur à écrire est zéro, dans ce
 * cas, rien n'est fait.
 * <p>
 * <p>
 * Cinq de ces méthodes (oppose, unitaire, addition, agrandir, soustraction ) construisent
 * des nouveaux vecteurs comme éléments de retour.  Assurez-vous que les vecteurs construits
 * reste des VecteurCreux : les cases dans la liste chaine ne contiennent pas de zéro.
 *
 * @author vos noms
 */
public class VecteurCreux implements Iterable <Double> {


    private Maillon <Double> debut = null;
    private Maillon <Double> next = null;
    /**
     * Construis un VecteurCreux où toutes les valeurs sont à 0.
     *
     * @param indiceMax le nombre de valeur dans le vecteur.
     */
    public VecteurCreux( int indiceMax ) {
        this.taille = indiceMax;
    }


    public int taille;
    public int nbElement = 0;
    /**
     * Construis un VecteurCreux à partir d'un tableau.
     * Le vecteur construit aura une taille égale à celle du tableau en
     * argument.
     *
     * @param vecteurFixe tableau dont les éléments sont placés dans le vecteur
     *                    construit.
     */
    public VecteurCreux( double[] vecteurFixe ) {
        this.taille = vecteurFixe.length;

        for (int i = 0; i < vecteurFixe.length; i++) {
            if (vecteurFixe[i] != 0) {
                enfiler (vecteurFixe[i], i + 1);
            }
        }
    }



    /**
     * @param element
     * @param emplacement
     */

    public void enfiler( double element, int emplacement ) {

        Maillon <Double> nouveau = new Maillon <> (element, emplacement);

        if (null != next) {
            next.suivant = nouveau;
        }

        next = nouveau;

        if (null == debut) {
            debut = nouveau;
        }
        nbElement += 1;
    }


    /**
     * Addition de 2 vecteurs.
     * <p>
     * Mathematique : l'addition de deux vecteurs est faite élément à élément :
     * L'addition d'un vecteur \(\vec{u}\) avec un vecteur \(\vec{v}\) nous donne un
     * vecteur \(\vec{r}\) où : \(\forall i \in [1..taille()], r_i = u_i + v_i\).
     *
     * @param v2 le vecteur additionne a 'this'.
     * @return le vecteur résultant de l'addition du vecteur 'this' avec
     * le vecteur 'v2'.  Le vecteur retourne est un nouveau vecteur.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public VecteurCreux addition( VecteurCreux v2 ) throws IndexOutOfBoundsException {

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }
        double[] nouveauVecteur = new double[taille];

        for (int i = 0; i < taille; ++i) {
            nouveauVecteur[i] = this.get (i + 1) + v2.get (i + 1);
        }
        return new VecteurCreux (nouveauVecteur);
    }

    /**
     * Construit un nouveau VecteurCreux parallèle (ou anti-parallèle) à 'this' avec une
     * norme multipliée par 's'.
     * <p>
     * Mathematique : La multiplication d'un vecteur \(\vec{u}\) par un scalaire \(s\) nous
     * donne le vecteur \(\vec{r}\) suivant :
     * \(\forall i \in [1..taille()], r_i = s \cdot u_i\)
     *
     * @param s Le facteur d'agrandissement.
     * @return Un vecteur parallèle (ou anti-parallèle) au vecteur 'this' où chaque valeur a
     * été multiplie par 's'.
     */
    public VecteurCreux agrandir( double s ) {
        double[] nouveauVecteur = new double[taille];
        for (int i = 0; i < taille; ++i) {
            nouveauVecteur[i] = this.get (i + 1) * s;
        }
        return new VecteurCreux (nouveauVecteur);
    }

    /**
     * Compare deux vecteurs : 'this' et 'objet'.
     * <p>
     * Mathematique : \(r = \forall i \in [1..taille()], u_i = v_i\)
     *
     * @param objet le deuxième vecteur à comparé.
     * @return True si les vecteurs ont les mêmes valeur
     * indice à indice.  False s'ils n'ont pas la même
     * taille ou s'ils n'ont pas les mêmes éléments ou
     * si 'objet' est null ou n'est pas un VecteurCreux.
     */
    public boolean equals( Object objet ) {
        Boolean testEgal = true;
        int testValeurInterne = 0;

        VecteurCreux v3 = (VecteurCreux) objet;
        if (v3 == null) {
            testEgal = false;
        } else if (this.taille != v3.taille ()) {
            testEgal = false;
        } else if (this.taille () == v3.taille ()) {
            for (int i = 0; i < this.taille (); ++i) {
                if (this.get (i + 1) == v3.get (i + 1)) {
                    ++testValeurInterne;
                }
            }
        }
        if (testValeurInterne != this.taille ()) {
            testEgal = false;
        }

        return testEgal;
    }

    /**
     * Verifie si deux vecteurs ('this' et 'v2') sont anti-parallèle (pointe en direction opposé).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont anti-parallèle si
     * la condition suivante est respectée :
     * \(r = \exists s \lt 0, \forall i \in [1..taille()], s \cdot u_i = v_i\)
     *
     * @param v2 le vecteur qui est compare à 'this'.
     * @return True si les vecteurs sont anti-parallèle.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public boolean estAntiParallelA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        boolean testAntiParallel = false;
        double[] testValeur = new double[taille];
        double s = 0;
        double test = 0;
        int indiceTest = 0;

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }
        for (int i = 0; i < this.taille (); ++i) {
            if (this.get (i + 1) != 0 && v2.get (i + 1) != 0 && this.nbElement == v2.nbElement) {
                testValeur[i] = this.get (i + 1) / v2.get (i + 1);
                s = testValeur[i];
            }
        }

        for (int y = 0; y < testValeur.length; ++y) {
            if (testValeur[y] != 0) {
                test += testValeur[y];
                ++indiceTest;
            }
        }
        if (test != 0 && s < 0) {
            testAntiParallel = test / indiceTest == s;
        }
        return testAntiParallel;
    }

    /**
     * Verifie si deux vecteurs ('this' et 'v2') sont opposés (pointes en
     * direction opposée, mais ont la même taille).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont opposés si
     * la condition suivante est respectée :
     * \(r = \forall i \in [1..taille()], -u_i = v_i\)
     *
     * @param v2 le vecteur qui est comparé à 'this'.
     * @return True si les vecteurs sont opposes.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public boolean estOpposeA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        boolean testOppose = false;
        double v1ContenuTotal = 0;
        double v2ContenuTotal = 0;

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        if (this.nbElement == v2.nbElement) {
            for (int i = 0; i < this.taille (); ++i) {
                v1ContenuTotal += this.get (i + 1);
                v2ContenuTotal += v2.get (i + 1);
            }
            testOppose = v1ContenuTotal + v2ContenuTotal == 0;
        }
        return testOppose;
    }

    /**
     * Vérifie si deux vecteurs ('this' et 'v2') sont parallèle (pointe dans la même direction).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont parallèle si
     * la condition suivante est respectée :
     * \(r = \exists s \gt 0, \forall i \in [1..taille()], s \cdot u_i = v_i\)
     *
     * @param v2 le vecteur qui est comparé à 'this'.
     * @return True si les vecteurs sont parallèle.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public boolean estParallelA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        boolean testParallel = false;

        double[] testValeur = new double[taille];
        double s = 0;
        double test = 0;
        int indiceTest = 0;

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }
        for (int i = 0; i < this.taille (); ++i) {
            if (this.get (i + 1) != 0 && v2.get (i + 1) != 0 && this.nbElement == v2.nbElement) {
                testValeur[i] = this.get (i + 1) / v2.get (i + 1);
                s = testValeur[i];
            }
        }

        for (int y = 0; y < testValeur.length; ++y) {
            if (testValeur[y] != 0) {
                test += testValeur[y];
                ++indiceTest;
            }
        }
        if (test != 0 && s > 0) {
            testParallel = test / indiceTest == s;
        }


        return testParallel;
    }

    /**
     * Lis et retourne une valeur du vecteur.  La case '1' est la première case du vecteur
     * et la case 'taille()' est la dernière case du vecteur.
     *
     * @param indice une valeur entre 1 et taille() indiquant la case lue.
     * @return la valeur contenue dans le vecteur à la case 'indice'.
     * @throws IndexOutOfBoundsException lancer si l'indice est plus petit que 1 ou
     *                                   plus grand que taille().
     */
    public double get( int indice ) throws IndexOutOfBoundsException {
        double contenu = 0;

        Maillon <Double> leMaillon = debut;

        if (indice < 1 || indice > taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        while (leMaillon != null) {

                if (leMaillon.emplacement == indice) {
                    contenu = leMaillon.interne;
                }

            leMaillon = leMaillon.suivant;
        }

        return contenu;
    }

    /**
     * Construis un itérateur sur les éléments du vecteur, y compris les zéros.
     *
     * @return un Iterator sur les Doubles contenus dans le vecteur.
     */
    @Override
    public Iterator <Double> iterator() {
        return new IterateurVecteurCreux (this);
    }

    /**
     * Multiplication scalaire entre 2 vecteurs.
     * <p>
     * Mathematique : la multiplication scalaire de deux vecteurs est faite élément à élément :
     * La multiplication d'un vecteur \(\vec{u}\) avec un vecteur \(\vec{v}\) nous donne un
     * scalaire \(r\) où : \(r = \sum^{taille()}_{i = 1} u_i \cdot v_i\)
     *
     * @param v2 le vecteur multiplie par 'this'.
     * @return le scalaire (double) résultant de la multiplication du vecteur 'this' avec
     * le vecteur 'v2'.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public double multiplicationScalaire( VecteurCreux v2 ) throws IndexOutOfBoundsException {

        double resultat = 0;
        if (this.taille () != v2.taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        for (int i = 0; i < this.taille (); ++i) {
            resultat += this.get (i + 1) * v2.get (i + 1);
        }

        return resultat;
    }

    /**
     * Calcule le nombre d'élélements qui ne sont pas à zéro.
     *
     * @return le nombre d'élélements qui ne sont pas à zéro.
     */
    public int nbrElementNonZero() {
        return this.nbElement;
    }

    /**
     * Calcule la norme euclidienne du vecteur 'this'.
     * <p>
     * Mathematique : La norme euclidienne d'un vecteur \(\vec{u}\) est calculé comme suit :
     * \(r = \sqrt{ \sum^{taille()}_{i = 1} u_i^2 }\)
     *
     * @return la norme du vecteur.
     */
    public double normeEucledienne() {
        double resultat = 0;

        for (int i = 0; i < this.taille (); ++i) {
            resultat += this.get (i + 1) * this.get (i + 1);
        }

        return Math.sqrt (resultat);
    }

    /**
     * Construis un vecteur opposé au vecteur 'this'.
     * <p>
     * Mathematique : Le vecteur \(\vec{r}\) opposé à \(\vec{u}\) contient les mêmes valeurs
     * avec des signes opposés.
     * \(\forall i \in [1..taille()], r_i = -u_i\).
     *
     * @return Un nouveau VecteurCreux opposé au vecteur 'this'.
     */
    public VecteurCreux oppose() {
        double[] nouveauVecteur = new double[taille];

        for (int i = 0; i < this.taille (); ++i) {
            nouveauVecteur[i] = this.get (i + 1) * -1;
        }

        return new VecteurCreux (nouveauVecteur);
    }

    /**
     * Modifi une valeur du vecteur.  La case '1' est la première case du vecteur
     * et la case 'taille()' est la dernière case du vecteur.
     *
     * @param indice une valeur entre 1 et taille() indiquant la case modifié.
     * @param valeur La valeur place dans la case modifié.
     * @throws IndexOutOfBoundsException lancer si l'indice est plus petit que 1 ou
     *                                   plus grand que taille().
     */
    public void set( int indice, double valeur ) throws IndexOutOfBoundsException {
        Maillon <Double> leMaillon = debut;
        Boolean existe = false;
        if (indice < 1 || indice > taille ()) {
            throw new IndexOutOfBoundsException ();
        }

            while (leMaillon != null) {

                if (leMaillon.emplacement == indice) {
                    existe = true;
                    if (valeur != 0) {
                        leMaillon.interne = valeur;

                    } else {
                        leMaillon.emplacement = -1;
                        --nbElement;
                    }
                }
                leMaillon = leMaillon.suivant;
            }
        if (!existe && valeur != 0) {
            enfiler (valeur, indice);
        }
    }

    /**
     * Soustraction de 2 vecteurs.
     * <p>
     * Mathematique : la soustraction de deux vecteurs est faite élément à élément :
     * La soustraction du vecteur \(\vec{v}\) au vecteur \(\vec{u}\) nous donne un
     * vecteur \(\vec{r}\) où : \(\forall i \in [1..taille()], r_i = u_i - v_i\).
     *
     * @param v2 le vecteur soustrait de 'this'.
     * @return le vecteur résultant de la soustraction du vecteur 'v2' au vecteur 'this'
     * Le vecteur retourné est un nouveau vecteur.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est différent.
     */
    public VecteurCreux soustraction( VecteurCreux v2 ) throws IndexOutOfBoundsException {

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }
        double[] nouveauVecteur = new double[taille];

        for (int i = 0; i < taille; ++i) {
            nouveauVecteur[i] = this.get (i + 1) - v2.get (i + 1);
        }
        return new VecteurCreux (nouveauVecteur);
    }


    /**
     * Retourne le nombre d'éléments que le vecteur contient.
     * Cela comprend les zéros et les non-zéros.
     *
     * @return le nombre d'élément du vecteur.
     */
    public int taille() {
        return this.taille;
    }

    @Override
    public String toString() {
        return super.toString ();
    }

    /**
     * Construis un VecteurCreux unitaire parallèle à 'this'.
     * <p>
     * Mathematique : Un vecteur unitaire est un vecteur de norme '1.0'.
     * Soit \(n\) la norme euclidienne du vecteur \(\vec{v}\), alors le
     * vecteur \(\vec{r}\) unitaire et parallèle à \(\vec{v}\) est calculé comme suit :
     * \(\forall i \in [1..taille()], r_i = \frac{v_i}{n}\)
     *
     * @return le vecteur unitaire parallèle à 'this'.
     */


    public VecteurCreux unitaire() {

        double n = normeEucledienne ();
        double[] nouveauVecteur = new double[taille];

        for (int i = 0; i < taille; ++i) {
            nouveauVecteur[i] = this.get (i + 1) / n;
        }
        return new VecteurCreux (nouveauVecteur);

    }


    private class Maillon<Double> {
        private double interne;
        private int emplacement;

        private Maillon <Double> suivant;

        private Maillon( double interne, int emplacement ) {
            this.interne = interne;
            this.emplacement = emplacement;
            this.suivant = null;


        }


    }

    /**
     * Contenant utilisé pour les itérateurs qui permettront de parcourir
     * le VecteurCreux.
     *
     * @author vos noms
     */
    public class IterateurVecteurCreux implements Iterator <Double> {
        Maillon <Double> courrant;
        int indice = 1;
        int indiceTaille = 0;

        public IterateurVecteurCreux( VecteurCreux origine ) {
            courrant = origine.debut;
        }


        @Override
        public boolean hasNext() {
            boolean has = true;
            if (indiceTaille == taille ()) {
                has = false;
            }
            ++indiceTaille;
            return has;
        }

        @Override
        public Double next() {
            Double resultat = 0.0;

            if (null != courrant) {
                if (indice == courrant.emplacement) {
                    resultat = courrant.interne;
                    courrant = courrant.suivant;
                }
            }
            ++indice;

            return resultat;
        }
    }

    /**
     * Contenant utilise pour les Maillons de la chaine qui
     * représentera le VecteurCreux.
     *
     * @author vos noms
     */


}