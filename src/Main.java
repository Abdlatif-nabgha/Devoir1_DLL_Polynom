public class Main {
    

    // ============= MÉTHODE MAIN POUR LES TESTS =============
    
    public static void main(String[] args) {
        System.out.println("=== TEST DES OPÉRATIONS SUR LES POLYNÔMES ===\n");
        
        // Test 1: Création et insertion de monômes
        System.out.println("1. CRÉATION ET INSERTION:");
        Polynome p1 = new Polynome();
        p1.insererMonome(3, 0);    // 3
        p1.insererMonome(2, 1);    // 2x
        p1.insererMonome(-5, 2);   // -5x^2
        p1.insererMonome(1, 4);    // x^4
        System.out.println(p1.afficher());
        System.out.println("Degré: " + p1.getDegre());
        System.out.println("Nombre de termes: " + p1.getTaille() + "\n");
        
        // Test 2: Deuxième polynôme
        Polynome p2 = new Polynome();
        p2.insererMonome(1, 0);    // 1
        p2.insererMonome(3, 1);    // 3x
        p2.insererMonome(2, 2);    // 2x^2
        p2.insererMonome(-1, 3);   // -x^3
        System.out.println("2. DEUXIÈME POLYNÔME:");
        System.out.println(p2.afficher() + "\n");
        
        // Test 3: Addition
        System.out.println("3. ADDITION:");
        Polynome somme = p1.addition(p2);
        System.out.println("P1 + P2 = " + somme.afficher() + "\n");
        
        // Test 4: Produit
        System.out.println("4. PRODUIT:");
        Polynome produit = p1.produit(p2);
        System.out.println("P1 * P2 = " + produit.afficher() + "\n");
        
        // Test 5: Dérivée
        System.out.println("5. DÉRIVÉE:");
        Polynome deriv1 = p1.derivee();
        System.out.println("P1' = " + deriv1.afficher());
        Polynome deriv2 = p2.derivee();
        System.out.println("P2' = " + deriv2.afficher() + "\n");
        
        // Test 6: Primitive
        System.out.println("6. PRIMITIVE:");
        Polynome prim1 = p1.primitive();
        System.out.println("∫P1 dx = " + prim1.afficher());
        Polynome prim2 = p2.primitive();
        System.out.println("∫P2 dx = " + prim2.afficher() + "\n");
        
        // Test 7: Cas particuliers
        System.out.println("7. CAS PARTICULIERS:");
        Polynome p3 = new Polynome();
        p3.insererMonome(5, 2);
        p3.insererMonome(-5, 2);  // Doit annuler le terme précédent
        System.out.println("Polynôme avec termes qui s'annulent: " + p3.afficher());
        System.out.println("Est vide? " + p3.estVide());
    }
}
