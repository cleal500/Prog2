package Lacrima;

import java.util.Iterator;

/*
  javadoc g&eacute;n&eacute;r&eacute; avec : -locale fr -header '<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>'
 */

/**
 * Un VecteurCreux est une classe pour repr&eacute;senter les vecteurs de grande taille,
 * mais contenant plusieurs valeurs a z&eacute;ro.
 * Ils sont repr&eacute;sent&eacute;s &agrave; l'aide de liste chaine o&ugrave; chaque Maillon (case) contient seulement
 * un &eacute;l&eacute;ment qui n'est pas a z&eacute;ro.  Ces Maillon vont donc aussi contenir l'indice de la
 * case repr&eacute;sent&eacute;.  Par exemple, le vecteur (0, 0, 0, 1, 0, 2, 0, 0 ) serait repr&eacute;sent&eacute;
 * par une liste contenant deux Maillons : un pour la case '4' contenant la valeur '1' et
 * un pour la case '6' contenant la valeur '2'.  La premi&egrave;re case d'un vecteur creux est donc
 * repr&eacute;sente par l'indice '1'.  Les valeurs contenues dans le vecteur sont repr&eacute;sent&eacute;es par
 * des doubles.
 * <p>
 * Les VecteursCreux sont des structures de taille fixe : le nombre de cases ne peut pas varier
 * et est d&eacute;termin&eacute; lors de la construction du vecteur.  Par contre, le nombre de cases qui ne
 * contiennent pas z&eacute;ro peuvent varier, donc la taille de la liste chaine peut varier.
 * <p>
 * Cette structure est Iterable, elle donne acc&egrave;s au service de construction d'un Iterator.
 * L'it&eacute;rateur permet de parcourir chaque case du vecteur.  Autant les cases contenant z&eacute;ro
 * que les autres.
 * <p>
 * Services offerts :
 * <p>
 * 'get' et 'set' permettent de lire et &eacute;crire dans les cases du vecteur.
 * La m&eacute;thode 'set' peu donc modifier la taille de la liste chaine repr&eacute;sentant les
 * valeur non-z&eacute;ro.  Il y a quatre cas possibles :
 * <p>
 * 1- La case o&ugrave; nous voulons &eacute;crire ne contient pas z&eacute;ro et la valeur &agrave; &eacute;crire n'est pas
 * z&eacute;ro, dans ce cas, la valeur &agrave; &eacute;crire remplace la valeur dans la case de la liste chaine.
 * <p>
 * 2- La case o&ugrave; nous voulons &eacute;crire contient z&eacute;ro et la valeur &agrave; &eacute;crire n'est pas z&eacute;ro,
 * dans ce cas, une case est ins&eacute;r&eacute;e dans la liste chaine avec la valeur &eacute;crite.
 * <p>
 * 3- La case o&ugrave; nous voulons &eacute;crire ne contient pas z&eacute;ro et la valeur &agrave; &eacute;crire est z&eacute;ro,
 * dans ce cas, la case contenant la valeur dans la liste chaine est supprim&eacute;e.
 * <p>
 * 4- La case o&ugrave; nous voulons &eacute;crire contient z&eacute;ro et la valeur &agrave; &eacute;crire est z&eacute;ro, dans ce
 * cas, rien n'est fait.
 * <p>
 * <p>
 * Cinq de ces m&eacute;thodes (oppose, unitaire, addition, agrandir, soustraction ) construisent
 * des nouveaux vecteurs comme &eacute;l&eacute;ments de retour.  Assurez-vous que les vecteurs construits
 * reste des VecteurCreux : les cases dans la liste chaine ne contiennent pas de z&eacute;ro.
 *
 * @author vos noms
 */
public class VecteurCreux implements Iterable <Double> {


    private class Maillon<Double> {
        private Double interne;
        private int indice;
        private int emplacement;
        private Maillon <Double> suivant;
        private Maillon <Double> precedent;

        private Maillon( Double interne, int indice, int emplacement ) {
            this.interne = interne;
            this.indice = indice;
            this.emplacement = emplacement;
            this.suivant = null;
            this.precedent = null;
        }

        public Maillon() {

        }
    }

    /**
     * Contenant utilis&eacute; pour les it&eacute;rateurs qui permettront de parcourir
     * le VecteurCreux.
     *
     * @author vos noms
     */
    public class IterateurVecteurCreux implements Iterator <Double> {
        Maillon <Double> courrant;


        @Override
        public boolean hasNext() {
            return null != courrant;
        }

        @Override
        public Double next() {
            Double resultat = courrant.interne;
            courrant = courrant.suivant;
            return resultat;
        }
    }


    public int taille;
    public int nbElement = 0;
    private Maillon <Double> prev = null;
    private Maillon <Double> next = null;

    /**
     * Construis un VecteurCreux o&ugrave; toutes les valeurs sont &agrave; 0.
     *
     * @param indiceMax le nombre de valeur dans le vecteur.
     */
    public VecteurCreux( int indiceMax ) {
        this.taille = indiceMax;
        prev = new Maillon <> ();
        next = new Maillon <> ();
        prev.suivant = next;
        next.precedent = prev;
    }

    /**
     * Construis un VecteurCreux &agrave; partir d'un tableau.
     * Le vecteur construit aura une taille &eacute;gale &agrave; celle du tableau en
     * argument.
     *
     * @param vecteurFixe tableau dont les &eacute;l&eacute;ments sont plac&eacute;s dans le vecteur
     *                    construit.
     */
    public VecteurCreux( double[] vecteurFixe ) {
        this.taille = vecteurFixe.length;

        for (int i = 0; i < vecteurFixe.length; i++) {
            if (vecteurFixe[i] > 0) {
                nbElement += 1;
                Maillon <Double> nouveau = new Maillon <> (vecteurFixe[i], nbElement, i + 1);

                if (null != next) {
                    next.suivant = nouveau;
                    next.precedent = prev;
                }
                if (null == prev) {
                    prev = nouveau;
                }
                next = nouveau;
            }
        }
    }

    /**
     * Addition de 2 vecteurs.
     * <p>
     * Mathematique : l'addition de deux vecteurs est faite &eacute;l&eacute;ment &agrave; &eacute;l&eacute;ment :
     * L'addition d'un vecteur \(\vec{u}\) avec un vecteur \(\vec{v}\) nous donne un
     * vecteur \(\vec{r}\) o&ugrave; : \(\forall i \in [1..taille()], r_i = u_i + v_i\).
     *
     * @param v2 le vecteur additionne a 'this'.
     * @return le vecteur r&eacute;sultant de l'addition du vecteur 'this' avec
     * le vecteur 'v2'.  Le vecteur retourne est un nouveau vecteur.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public VecteurCreux addition( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        VecteurCreux vecteurR = new VecteurCreux (taille);

        Maillon <Double> leMaillon = prev;

        if (v2.taille () != this.taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        while (leMaillon != null) {
            if (leMaillon.emplacement == v2.prev.emplacement) {
                double att = 0;

                att = leMaillon.interne + v2.prev.interne;

                Maillon <Double> nouveau = new Maillon <> (att, nbElement, 1);

                if (null != vecteurR.next) {
                    vecteurR.next.suivant = nouveau;
                    vecteurR.next.precedent = prev;
                }
                if (null == vecteurR.prev) {
                    vecteurR.prev = nouveau;
                }
                vecteurR.next = nouveau;
            }
            leMaillon = leMaillon.suivant;
        }return vecteurR;
    }

    /**
     * Construit un nouveau VecteurCreux parall&egrave;le (ou anti-parall&egrave;le) &agrave; 'this' avec une
     * norme multipli&eacute;e par 's'.
     * <p>
     * Mathematique : La multiplication d'un vecteur \(\vec{u}\) par un scalaire \(s\) nous
     * donne le vecteur \(\vec{r}\) suivant :
     * \(\forall i \in [1..taille()], r_i = s \cdot u_i\)
     *
     * @param s Le facteur d'agrandissement.
     * @return Un vecteur parall&egrave;le (ou anti-parall&egrave;le) au vecteur 'this' o&ugrave; chaque valeur a
     * &eacute;t&eacute; multiplie par 's'.
     */
    public VecteurCreux agrandir( double s ) {
        return null;
    }

    /**
     * Compare deux vecteurs : 'this' et 'objet'.
     * <p>
     * Mathematique : \(r = \forall i \in [1..taille()], u_i = v_i\)
     *
     * @param objet le deuxi&egrave;me vecteur &agrave; compar&eacute;.
     * @return True si les vecteurs ont les m&ecirc;mes valeur
     * indice &agrave; indice.  False s'ils n'ont pas la m&ecirc;me
     * taille ou s'ils n'ont pas les m&ecirc;mes &eacute;l&eacute;ments ou
     * si 'objet' est null ou n'est pas un VecteurCreux.
     */
    public boolean equals( Object objet ) {
        return false;
    }

    /**
     * Verifie si deux vecteurs ('this' et 'v2') sont anti-parall&egrave;le (pointe en direction oppos&eacute;).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont anti-parall&egrave;le si
     * la condition suivante est respect&eacute;e :
     * \(r = \exists s \lt 0, \forall i \in [1..taille()], s \cdot u_i = v_i\)
     *
     * @param v2 le vecteur qui est compare &agrave; 'this'.
     * @return True si les vecteurs sont anti-parall&egrave;le.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public boolean estAntiParallelA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        return false;
    }

    /**
     * Verifie si deux vecteurs ('this' et 'v2') sont oppos&eacute;s (pointes en
     * direction oppos&eacute;e, mais ont la m&ecirc;me taille).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont oppos&eacute;s si
     * la condition suivante est respect&eacute;e :
     * \(r = \forall i \in [1..taille()], -u_i = v_i\)
     *
     * @param v2 le vecteur qui est compar&eacute; &agrave; 'this'.
     * @return True si les vecteurs sont opposes.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public boolean estOpposeA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        return false;
    }

    /**
     * V&eacute;rifie si deux vecteurs ('this' et 'v2') sont parall&egrave;le (pointe dans la m&ecirc;me direction).
     * <p>
     * Mathematique : deux vecteurs \(\vec{u}\) et \(\vec{v}\) sont parall&egrave;le si
     * la condition suivante est respect&eacute;e :
     * \(r = \exists s \gt 0, \forall i \in [1..taille()], s \cdot u_i = v_i\)
     *
     * @param v2 le vecteur qui est compar&eacute; &agrave; 'this'.
     * @return True si les vecteurs sont parall&egrave;le.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public boolean estParallelA( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        return false;
    }

    /**
     * Lis et retourne une valeur du vecteur.  La case '1' est la premi&egrave;re case du vecteur
     * et la case 'taille()' est la derni&egrave;re case du vecteur.
     *
     * @param indice une valeur entre 1 et taille() indiquant la case lue.
     * @return la valeur contenue dans le vecteur &agrave; la case 'indice'.
     * @throws IndexOutOfBoundsException lancer si l'indice est plus petit que 1 ou
     *                                   plus grand que taille().
     */
    public double get( int indice ) throws IndexOutOfBoundsException {
        double contenu = 0.0;

        Maillon <Double> leMaillon = prev;

        if (indice < 1 || indice > taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        while (leMaillon != null) {
            for (int i = 0; i < indice; i++) {
                if (leMaillon.emplacement == indice) {
                    contenu = leMaillon.interne;
                }
            }
            leMaillon = leMaillon.suivant;
        }

        return contenu;
    }
    /**
     * Construis un it&eacute;rateur sur les &eacute;l&eacute;ments du vecteur, y compris les z&eacute;ros.
     *
     * @return un Iterator sur les Doubles contenus dans le vecteur.
     */
    @Override
    public Iterator <Double> iterator() {
        return new IterateurVecteurCreux ();
    }

    /**
     * Multiplication scalaire entre 2 vecteurs.
     * <p>
     * Mathematique : la multiplication scalaire de deux vecteurs est faite &eacute;l&eacute;ment &agrave; &eacute;l&eacute;ment :
     * La multiplication d'un vecteur \(\vec{u}\) avec un vecteur \(\vec{v}\) nous donne un
     * scalaire \(r\) o&ugrave; : \(r = \sum^{taille()}_{i = 1} u_i \cdot v_i\)
     *
     * @param v2 le vecteur multiplie par 'this'.
     * @return le scalaire (double) r&eacute;sultant de la multiplication du vecteur 'this' avec
     * le vecteur 'v2'.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public double multiplicationScalaire( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        return 0.0;
    }

    /**
     * Calcule le nombre d'&eacute;l&eacute;lements qui ne sont pas &agrave; z&eacute;ro.
     *
     * @return le nombre d'&eacute;l&eacute;lements qui ne sont pas &agrave; z&eacute;ro.
     */
    public int nbrElementNonZero() {
        return this.nbElement;
    }

    /**
     * Calcule la norme euclidienne du vecteur 'this'.
     * <p>
     * Mathematique : La norme euclidienne d'un vecteur \(\vec{u}\) est calcul&eacute; comme suit :
     * \(r = \sqrt{ \sum^{taille()}_{i = 1} u_i^2 }\)
     *
     * @return la norme du vecteur.
     */
    public double normeEucledienne() {
        return 0.0;
    }

    /**
     * Construis un vecteur oppos&eacute; au vecteur 'this'.
     * <p>
     * Mathematique : Le vecteur \(\vec{r}\) oppos&eacute; &agrave; \(\vec{u}\) contient les m&ecirc;mes valeurs
     * avec des signes oppos&eacute;s.
     * \(\forall i \in [1..taille()], r_i = -u_i\).
     *
     * @return Un nouveau VecteurCreux oppos&eacute; au vecteur 'this'.
     */
    public VecteurCreux oppose() {
        return null;
    }

    /**
     * Modifi une valeur du vecteur.  La case '1' est la premi&egrave;re case du vecteur
     * et la case 'taille()' est la derni&egrave;re case du vecteur.
     *
     * @param indice une valeur entre 1 et taille() indiquant la case modifi&eacute;.
     * @param valeur La valeur place dans la case modifi&eacute;.
     * @throws IndexOutOfBoundsException lancer si l'indice est plus petit que 1 ou
     *                                   plus grand que taille().
     */
    public void set( int indice, double valeur ) throws IndexOutOfBoundsException {
        Maillon <Double> leMaillon = prev;

        if (indice < 1 || indice > taille ()) {
            throw new IndexOutOfBoundsException ();
        }

        if (nbElement != 0) {
            while (leMaillon != null) {

                if (leMaillon.emplacement == indice && valeur != 0) {
                    leMaillon.interne = valeur;
                } else if (leMaillon.emplacement == indice) {
                    leMaillon.emplacement = -1;
                } else if (valeur != 0) {
                    nbElement += 1;


                    Maillon <Double> nouveau = new Maillon <> (valeur, nbElement, indice);

                    if (null != next) {
                        next.suivant = nouveau;
                        next.precedent = prev;
                    }
                    if (null == prev) {
                        prev = nouveau;
                    }
                    next = nouveau;

                }
                leMaillon = leMaillon.suivant;
            }
        } else if (nbElement == 0 && valeur != 0) {
            nbElement += 1;


            Maillon <Double> nouveau = new Maillon <> (valeur, nbElement, indice);

            if (null != next) {
                next.suivant = nouveau;
                next.precedent = prev;
            }
            if (null == prev) {
                prev = nouveau;


            }
            next = nouveau;
        }


    }

    /**
     * Soustraction de 2 vecteurs.
     * <p>
     * Mathematique : la soustraction de deux vecteurs est faite &eacute;l&eacute;ment &agrave; &eacute;l&eacute;ment :
     * La soustraction du vecteur \(\vec{v}\) au vecteur \(\vec{u}\) nous donne un
     * vecteur \(\vec{r}\) o&ugrave; : \(\forall i \in [1..taille()], r_i = u_i - v_i\).
     *
     * @param v2 le vecteur soustrait de 'this'.
     * @return le vecteur r&eacute;sultant de la soustraction du vecteur 'v2' au vecteur 'this'
     * Le vecteur retourn&eacute; est un nouveau vecteur.
     * @throws IndexOutOfBoundsException lancer si la taille() des deux vecteurs
     *                                   est diff&eacute;rent.
     */
    public VecteurCreux soustraction( VecteurCreux v2 ) throws IndexOutOfBoundsException {
        return null;
    }

    /**
     * Retourne le nombre d'&eacute;l&eacute;ments que le vecteur contient.
     * Cela comprend les z&eacute;ros et les non-z&eacute;ros.
     *
     * @return le nombre d'&eacute;l&eacute;ment du vecteur.
     */
    public int taille() {
        return this.taille;
    }

    /**
     * Construis un VecteurCreux unitaire parall&egrave;le &agrave; 'this'.
     * <p>
     * Mathematique : Un vecteur unitaire est un vecteur de norme '1.0'.
     * Soit \(n\) la norme euclidienne du vecteur \(\vec{v}\), alors le
     * vecteur \(\vec{r}\) unitaire et parall&egrave;le &agrave; \(\vec{v}\) est calcul&eacute; comme suit :
     * \(\forall i \in [1..taille()], r_i = \frac{v_i}{n}\)
     *
     * @return le vecteur unitaire parall&egrave;le &agrave; 'this'.
     */
    public VecteurCreux unitaire() {
        return null;
    }

    /**
     * Contenant utilise pour les Maillons de la chaine qui
     * repr&eacute;sentera le VecteurCreux.
     *
     * @author vos noms
     */


}