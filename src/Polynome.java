
/**
 * Classe représentant un polynôme par liste doublement chaînée
 * Les monômes sont ordonnés par degré croissant
 */
public class Polynome {
    private Monome tete;  // Premier monôme (degré le plus faible)
    private Monome queue; // Dernier monôme (degré le plus élevé)
    private int taille;   // Nombre de monômes
    
    /**
     * Constructeur - Crée un polynôme vide
     * Complexité: O(1)
     */
    public Polynome() {
        this.tete = null;
        this.queue = null;
        this.taille = 0;
    }
    
    /**
     * Insertion d'un monôme dans le polynôme
     * Les monômes sont insérés en ordre croissant de degré
     * Si un monôme du même degré existe, on additionne les coefficients
     * 
     * Complexité: O(n) dans le pire cas (parcours de toute la liste)
     *             O(1) meilleur cas (insertion en début ou fin)
     * 
     * @param coefficient Le coefficient du monôme
     * @param degre Le degré du monôme
     */
    public void insererMonome(double coefficient, int degre) {
        // Ignorer les monômes avec coefficient nul
        if (coefficient == 0) {
            return;
        }
        
        Monome nouveau = new Monome(coefficient, degre);
        
        // Cas 1: Liste vide
        if (tete == null) {
            tete = nouveau;
            queue = nouveau;
            taille++;
            return;
        }
        
        // Cas 2: Insertion en tête (degré plus petit que le premier)
        if (degre < tete.degre) {
            nouveau.suivant = tete;
            tete.precedent = nouveau;
            tete = nouveau;
            taille++;
            return;
        }
        
        // Cas 3: Insertion en queue (degré plus grand que le dernier)
        if (degre > queue.degre) {
            nouveau.precedent = queue;
            queue.suivant = nouveau;
            queue = nouveau;
            taille++;
            return;
        }
        
        // Cas 4: Recherche de la position d'insertion ou du même degré
        Monome courant = tete;
        while (courant != null) {
            if (courant.degre == degre) {
                // Même degré: addition des coefficients
                courant.coefficient += coefficient;
                // Si le coefficient devient nul, supprimer le monôme
                if (courant.coefficient == 0) {
                    supprimerMonome(courant);
                }
                return;
            }
            
            if (courant.degre > degre) {
                // Insérer avant courant
                nouveau.suivant = courant;
                nouveau.precedent = courant.precedent;
                courant.precedent.suivant = nouveau;
                courant.precedent = nouveau;
                taille++;
                return;
            }
            
            courant = courant.suivant;
        }
    }
    
    /**
     * Suppression d'un monôme de la liste
     * Complexité: O(1) - accès direct au noeud
     * 
     * @param monome Le monôme à supprimer
     */
    private void supprimerMonome(Monome monome) {
        if (monome == null) return;
        
        // Cas: monôme unique
        if (tete == queue && tete == monome) {
            tete = null;
            queue = null;
            taille--;
            return;
        }
        
        // Cas: suppression de la tête
        if (monome == tete) {
            tete = tete.suivant;
            tete.precedent = null;
            taille--;
            return;
        }
        
        // Cas: suppression de la queue
        if (monome == queue) {
            queue = queue.precedent;
            queue.suivant = null;
            taille--;
            return;
        }
        
        // Cas général: monôme au milieu
        monome.precedent.suivant = monome.suivant;
        monome.suivant.precedent = monome.precedent;
        taille--;
    }
    
    /**
     * Addition de deux polynômes
     * Complexité: O(n + m) où n et m sont les tailles des deux polynômes
     * 
     * @param p2 Le polynôme à additionner
     * @return Un nouveau polynôme résultat de l'addition
     */
    public Polynome addition(Polynome p2) {
        Polynome resultat = new Polynome();
        Monome m1 = this.tete;
        Monome m2 = p2.tete;
        
        // Parcours simultané des deux polynômes
        while (m1 != null || m2 != null) {
            if (m1 == null) {
                // Plus de monômes dans p1, copier m2
                resultat.insererMonome(m2.coefficient, m2.degre);
                m2 = m2.suivant;
            } else if (m2 == null) {
                // Plus de monômes dans p2, copier m1
                resultat.insererMonome(m1.coefficient, m1.degre);
                m1 = m1.suivant;
            } else if (m1.degre < m2.degre) {
                // m1 a un degré plus petit
                resultat.insererMonome(m1.coefficient, m1.degre);
                m1 = m1.suivant;
            } else if (m1.degre > m2.degre) {
                // m2 a un degré plus petit
                resultat.insererMonome(m2.coefficient, m2.degre);
                m2 = m2.suivant;
            } else {
                // Même degré: additionner les coefficients
                double somme = m1.coefficient + m2.coefficient;
                if (somme != 0) {
                    resultat.insererMonome(somme, m1.degre);
                }
                m1 = m1.suivant;
                m2 = m2.suivant;
            }
        }
        
        return resultat;
    }
    
    /**
     * Produit de deux polynômes
     * Complexité: O(n * m) où n et m sont les tailles des deux polynômes
     * 
     * @param p2 Le polynôme à multiplier
     * @return Un nouveau polynôme résultat du produit
     */
    public Polynome produit(Polynome p2) {
        Polynome resultat = new Polynome();
        
        // Pour chaque monôme du premier polynôme
        Monome m1 = this.tete;
        while (m1 != null) {
            // Multiplier par chaque monôme du second polynôme
            Monome m2 = p2.tete;
            while (m2 != null) {
                double coeff = m1.coefficient * m2.coefficient;
                int deg = m1.degre + m2.degre;
                resultat.insererMonome(coeff, deg);
                m2 = m2.suivant;
            }
            m1 = m1.suivant;
        }
        
        return resultat;
    }
    
    /**
     * Dérivée du polynôme
     * Complexité: O(n) où n est le nombre de monômes
     * 
     * @return Un nouveau polynôme représentant la dérivée
     */
    public Polynome derivee() {
        Polynome resultat = new Polynome();
        Monome courant = tete;
        
        while (courant != null) {
            // La dérivée de a*x^n est n*a*x^(n-1)
            if (courant.degre > 0) {
                double nouveauCoeff = courant.coefficient * courant.degre;
                int nouveauDegre = courant.degre - 1;
                resultat.insererMonome(nouveauCoeff, nouveauDegre);
            }
            // Les constantes (degré 0) disparaissent lors de la dérivation
            courant = courant.suivant;
        }
        
        return resultat;
    }
    
    /**
     * Primitive du polynôme (avec constante = 0)
     * Complexité: O(n) où n est le nombre de monômes
     * 
     * @return Un nouveau polynôme représentant la primitive
     */
    public Polynome primitive() {
        Polynome resultat = new Polynome();
        Monome courant = tete;
        
        while (courant != null) {
            // La primitive de a*x^n est (a/(n+1))*x^(n+1)
            double nouveauCoeff = courant.coefficient / (courant.degre + 1);
            int nouveauDegre = courant.degre + 1;
            resultat.insererMonome(nouveauCoeff, nouveauDegre);
            courant = courant.suivant;
        }
        
        return resultat;
    }
    
    /*
     * Affichage du polynôme sous la forme standard
     * Complexité: O(n) où n est le nombre de monômes
     * 
     * @return La représentation en chaîne du polynôme
     */
    public String afficher() {
        if (tete == null) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("P(x) = ");
        
        Monome courant = tete;
        boolean premier = true;
        
        while (courant != null) {
            // Ajouter le signe
            if (!premier) {
                if (courant.coefficient > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            } else if (courant.coefficient < 0) {
                sb.append("-");
            }
            
            // Ajouter le coefficient (en valeur absolue si pas premier)
            double coeff = premier ? courant.coefficient : Math.abs(courant.coefficient);
            
            if (courant.degre == 0) {
                // Terme constant
                sb.append(coeff);
            } else if (courant.degre == 1) {
                // Terme en x
                if (coeff == 1) {
                    sb.append("x");
                } else {
                    sb.append(coeff).append(" x");
                }
            } else {
                // Terme en x^n
                if (coeff == 1) {
                    sb.append("x ^ ").append(courant.degre);
                } else {
                    sb.append(coeff).append(" x ^ ").append(courant.degre);
                }
            }
            
            premier = false;
            courant = courant.suivant;
        }
        
        return sb.toString();
    }
    
    /*
     * Obtenir le degré du polynôme
     * Complexité: O(1) - accès direct à la queue
     * 
     * @return Le degré du polynôme (degré du dernier monôme)
     */
    public int getDegre() {
        return (queue != null) ? queue.degre : -1;
    }
    
    /**
     * Vérifier si le polynôme est vide
     * Complexité: O(1)
     * 
     * @return true si le polynôme est vide
     */
    public boolean estVide() {
        return tete == null;
    }
    
    /**
     * Obtenir la taille (nombre de monômes)
     * Complexité: O(1)
     * 
     * @return Le nombre de monômes
     */
    public int getTaille() {
        return taille;
    }
}