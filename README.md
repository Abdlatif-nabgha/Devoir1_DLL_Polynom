# 📊 Analyse de Complexité - Polynômes par Liste Doublement Chaînée

## Réalisé par : Abdlatif SIDI MED NABGHA - GLSID2

## Opérations de Base

| Opération | Complexité | Explication |
|-----------|-----------|-------------|
| **Création** | **O(1)** | Initialisation simple des pointeurs tête et queue à null |
| **Insertion d'un monôme** | **O(n)** | Parcours de la liste pour trouver la position d'insertion en ordre croissant |
| **Suppression d'un monôme** | **O(1)** | Accès direct au nœud grâce aux pointeurs précédent/suivant de la liste doublement chaînée |

## Opérations Polynomiales

| Opération | Complexité | Explication |
|-----------|-----------|-------------|
| **Addition** | **O(n + m)** | Parcours simultané des deux listes (n et m sont les tailles des deux polynômes) |
| **Produit** | **O(n × m)** | Chaque terme du premier polynôme est multiplié par chaque terme du second |
| **Dérivée** | **O(n)** | Un seul parcours de la liste pour calculer n×coefficient×x^(n-1) |
| **Primitive** | **O(n)** | Un seul parcours de la liste pour calculer (coefficient/(n+1))×x^(n+1) |
| **Affichage** | **O(n)** | Parcours de tous les monômes pour construire la chaîne de caractères |
| **getDegre()** | **O(1)** | Accès direct au degré du monôme en queue (degré maximal) |
| **estVide()** | **O(1)** | Vérification simple si tête == null |
| **getTaille()** | **O(1)** | Retour direct de l'attribut taille |

**Légende :**
- `n` = nombre de monômes dans le premier polynôme
- `m` = nombre de monômes dans le deuxième polynôme

## 🎯 Complexité Spatiale

- **Stockage** : O(n) - un nœud par monôme non-nul
- **Addition** : O(n + m) - création d'un nouveau polynôme résultat
- **Produit** : O(n × m) - au pire cas, tous les degrés sont différents
- **Dérivée/Primitive** : O(n) - nouveau polynôme de taille similaire
