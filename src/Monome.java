
/**
 * Classe représentant un monôme (terme) du polynôme
 * Un monôme a la forme: coefficient * x^degré
 */
class Monome {
    double coefficient;
    int degre;
    Monome suivant;
    Monome precedent;
    
    public Monome(double coefficient, int degre) {
        this.coefficient = coefficient;
        this.degre = degre;
        this.suivant = null;
        this.precedent = null;
    }
}
