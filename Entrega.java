
import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb "// TODO". L'enunciat de
 * cada un d'ells és al comentari de la seva signatura i exemples del seu funcionament als mètodes
 * `Tema1.tests`, `Tema2.tests`, etc.
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà un 0.
 *
 * - Si heu fet cap modificació que no sigui afegir un mètode, afegir proves als mètodes "tests()" o
 *   implementar els mètodes annotats amb "// TODO", la nota del grup serà un 0.
 *
 * - Principalment, la nota dependrà del correcte funcionament dels mètodes implemnetats (provant
 *   amb diferents entrades).
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . Algunes
 *   consideracions importants:
 *    + Entregau amb la mateixa codificació (UTF-8) i finals de línia (LF, no CR+LF)
 *    + Indentació i espaiat consistent
 *    + Bona nomenclatura de variables
 *    + Declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *      declaracions).
 *    + Convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for (int i = 0; ...))
 *      sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni qualificar classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 10.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Martín Serra Rubio
 * - Nom 2: Javier Vivo Samaniego
 * - Nom 3: Cristian Morales García
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {

    /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * La majoria dels mètodes reben de paràmetre l'univers (representat com un array) i els predicats
   * adients (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un
   * element de l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si
   * `P(x)` és cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis (excepte el primer) us demanam que donat l'univers i els
   * predicats retorneu `true` o `false` segons si la proposició donada és certa (suposau que
   * l'univers és suficientment petit com per poder provar tots els casos que faci falta).
     */
    static class Tema1 {

        /*
     * Donat n > 1, en quants de casos (segons els valors de veritat de les proposicions p1,...,pn)
     * la proposició (...((p1 -> p2) -> p3) -> ...) -> pn és certa?
     *
     * Vegeu el mètode Tema1.tests() per exemples.
         */
        static int exercici1(int n) {
            //declaracion variables
      //variable entera que almacenara el número de resultados correctos
      int resultadoCorrecto=0; 
      //declaración valor2 instanciación de array booleano 
      boolean[]arrayBinario;
      arrayBinario=new boolean[n];
              

      //inicialización del array a 1 
      for (int i=0;i<arrayBinario.length;i++){
        arrayBinario[i]=true;
      }
       // Bucle principal para generar valor2 verificar combinaciones
      do {
              // Modificar el array binario
              modificarArrayBinario(arrayBinario);
               // Comprobar si la combinación es válida valor2 contarla si lo es
              resultadoCorrecto += combinacionValida(arrayBinario);
                
                
            } while (!todosVerdaderos(arrayBinario));

            //resultado
            return resultadoCorrecto;
        }

        // Modifica el array binario a la siguiente combinación
        public static void modificarArrayBinario(boolean[] arrayBinario) {
            arrayBinario[0] = !arrayBinario[0];
            for (int i = 1; i < arrayBinario.length; i++) {
                if (!arrayBinario[i - 1]) {
                    arrayBinario[i] = !arrayBinario[i];
                } else {
                    return;
                }
            }
        }

        //Verificamos si todos los valores son true para salir del bucle
        public static boolean todosVerdaderos(boolean[] arrayBinario) {
            for (boolean valor : arrayBinario) {
                if (!valor) {
                    return false;
                }
            }
            return true;
        }

        //Verificamos si se cumple la condición
        public static int combinacionValida(boolean[] arrayBinario) {
            boolean condicion = arrayBinario[0];   //utilizamos p1 -> p2 como !p1 || p2
            for (int i = 1; i < arrayBinario.length; i++) {
                condicion = (!condicion) || (arrayBinario[i]);
            }
            if (condicion) {
                return 1;
            }
            return 0;
        }


        /*
     * És cert que ∀x : P(x) -> ∃!y : Q(x,y) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            //comprobar los casos uno a uno 
        for (int valor : universe) {
            boolean encontrado = false;
            //Si P(valor) es 0 entonces ya es true, pasámos al siguiente caso
            if (!p.test(valor)) {
                continue;
            } else {
                //Comprobamos que sólo exista un único valor2
                for (int valor2 : universe) {
                    if (q.test(valor, valor2)) {
                        if (encontrado) {
                            return false;
                        } else {
                            encontrado = true;
                        }

                    }
                }
            }
            //Si un caso es negativo ya no se cumple
            if (!encontrado) {
                return false;
            }
        }
        return true; // TODO
        }

        /*
     * És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
         */
        static boolean exercici3(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            //comprobamos que exista alguna valor
            for (int valor : universe) {
                boolean control = true;
                //si algun caso de ∀valor2 da falso ya no se cumple valor2 ponemos control a false
                for (int valor2 : universe) {
                    if (!q.test(valor, valor2) && p.test(valor)) {
                        control = false;
                    }
                }
                //si encontramos alguna valor devolvemos true
                if (control) {
                    return true;
                }
            }

            return false; // TODO
        }

        /*
     * És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
         */
        static boolean exercici4(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
            int contadorY = 0;
            //Verificamos que exista alguna valor e incializamos
            for (int valor : universe) {
                contadorY = 0;
                
                //Comprobamos que exista una única valor2
                for (int valor2 : universe) {
                    boolean control = true;
                    
                    //Comprobamos que se cumpla para cada z la condición
                    for (int z : universe) {
                        if (p.test(valor, z) != q.test(valor2, z)) {
                            control = false;
                        }
                    }
                    if (control) {
                        contadorY++;
                    }
                }
                //si sólo se ha encontrado un valor2, se ha cumplido la condición, devolvemos 
                if (contadorY == 1) {
                    return true;
                }
            }

            return contadorY == 1; // TODO
        }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1

            // p1 -> p2 és cert exactament a 3 files
            // p1 p2
            // 0  0  <-
            // 0  1  <-
            // 1  0
            // 1  1  <-
            assertThat(exercici1(2) == 3);

            // (p1 -> p2) -> p3 és cert exactament a 5 files
            // p1 p2 p3
            // 0  0  0
            // 0  0  1  <-
            // 0  1  0
            // 0  1  1  <-
            // 1  0  0  <-
            // 1  0  1  <-
            // 1  1  0
            // 1  1  1  <-
            assertThat(exercici1(3) == 5);

            // Exercici 2
            // ∀x : P(x) -> ∃!y : Q(x,y)
            assertThat(
                    exercici2(
                            new int[]{1, 2, 3},
                            x -> x % 2 == 0,
                            (x, y) -> x + y >= 5
                    )
            );

            assertThat(
                    !exercici2(
                            new int[]{1, 2, 3},
                            x -> x < 3,
                            (x, y) -> x - y > 0
                    )
            );

            // Exercici 3
            // És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
            assertThat(
                    exercici3(
                            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            x -> x % 3 != 0,
                            (x, y) -> y % x == 0
                    )
            );

            assertThat(
                    exercici3(
                            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                            x -> x % 4 != 0,
                            (x, y) -> (x * y) % 4 != 0
                    )
            );

            // Exercici 4
            // És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
            assertThat(
                    exercici4(
                            new int[]{0, 1, 2, 3, 4, 5},
                            (x, z) -> x * z == 1,
                            (y, z) -> y * z == 2
                    )
            );

            assertThat(
                    !exercici4(
                            new int[]{2, 3, 4, 5},
                            (x, z) -> x * z == 1,
                            (y, z) -> y * z == 2
                    )
            );
        }
    }

    /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {0,1}, {1,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   * Als tests utilitzarem extensivament la funció generateRel definida al final (també la podeu
   * utilitzar si la necessitau).
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam o bé amb el seu
   * graf o amb un objecte de tipus Function<Integer, Integer>. Sempre donarem el domini int[] a, el
   * codomini int[] b. En el cas de tenir un objecte de tipus Function<Integer, Integer>, per aplicar
   * f a x, és a dir, "f(x)" on x és d'A i el resultat f.apply(x) és de B, s'escriu f.apply(x).
     */
    static class Tema2 {

        /*
     * Calculau el nombre d'elements del conjunt de parts de (a u b) × (a \ c)
     *
     * Podeu soposar que `a`, `b` i `c` estan ordenats de menor a major.
         */
        static int exercici1(int[] a, int[] b, int[] c) {
            //variable para controlar el número de elementos 
            int numeroElementos = 0;
            //declaración e instanciación array a través de subprograma 
            int[] aUnioB = unionArrays(a, b);
            //declaración e instanciación array a través de subprograma 
            int[] aDiferenciaC = diferenciaArrays(a, c);

            //Cálculo del numero de elementos del conjunto de partes    
            numeroElementos = aUnioB.length * aDiferenciaC.length;

            return numeroElementos; // TODO
    }
    
    //Método que permite unir arrays sin elementos repetidos 
        static int[] unionArrays(int[] array1, int[] array2) {
            HashSet<Integer> lista = new HashSet<>(); //Lista con todos los elementos de la unión

            //Añadimos elementos de array1
            for (int elemento : array1) {
                lista.add(elemento);
            }

            //Añadimos elementos de array2
            for (int elemento : array2) {
                lista.add(elemento);
            }

            int[] arrayUnion = new int[lista.size()]; //vector resultado
            int indice = 0;

            //Bucle para introducir la lista en nuestro array resultado
            for (int num : lista) {
                arrayUnion[indice++] = num;
            }
            return arrayUnion;
        }

        //Método que permite encontrar la diferencia de Arrays
        static int[] diferenciaArrays(int[] array1, int[] array2) {
            HashSet<Integer> lista = new HashSet<>();

            for (int elemento : array1) {
                lista.add(elemento);
            }

            //Eliminamos elementos del segundo array
            for (int elemento : array2) {
                lista.remove(elemento);
            }

            //Almacenamos valor2 devolvemos en un array llamado diferencia el resultado
            int[] diferencia = new int[lista.size()];
            int indice = 0;
            for (int num : lista) {
                diferencia[indice++] = num;
            }
            return diferencia;
        }

        /*
     * La clausura d'equivalència d'una relació és el resultat de fer-hi la clausura reflexiva, simètrica i
     * transitiva simultàniament, i, per tant, sempre és una relació d'equivalència.
     *
     * Trobau el cardinal d'aquesta clausura.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
         */
        static int exercici2(int[] a, int[][] rel) {
            //int num_elementos = a.length * a.length;
            HashSet<int[]> lista = new HashSet<>();

            //Clausula reflexiva  {(1,1),(2,2)}
            for (int elemento : a) {
                int[] l = {elemento, elemento};
                lista.add(l);
            }

            //Clausula simètrica  {(1,2),(2,1)}
            for (int[] elementos : rel) {
                if (elementos[0] == elementos[1]) {
                    continue;
                }
                int[] l = {elementos[1], elementos[0]};
                lista.add(elementos);
                lista.add(l);
            }

            //Clausula transitiva {(1,1),(1,2),(2,1),(1,3)(2,3)}
            for (int i = 0; i < a.length - 1; i++) {
                for (int[] y : rel) {
                    for (int[] x : rel) {
                        if (x[0] == y[1]) {
                            lista.add(new int[]{y[0], x[1]});
                        }

                    }
                }
            }

            //Mateix cardinal: son equipotents: existeix una bijecció A -> B
            int cardinal = lista.size();
            return cardinal; // TODO
        }

        /*
     * Comprovau si la relació `rel` és un ordre total sobre `a`. Si ho és retornau el nombre
     * d'arestes del seu diagrama de Hasse. Sino, retornau -2.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
         */
        static int exercici3(int[] a, int[][] rel) {
    //condicional con varios metodos para verificar si es un orden total, es decir, 
         //es reflexiva, antisimétrica valor2 transitiva
           if (!esReflexiva(a, rel) || !esAntisimetrica(a, rel) || !esTransitiva(a, rel)) {
               return -2; // No es un orden total
           }
           return contarAristas(rel);
       }

       //Método que cuenta las aristas de un Diagrama de Hasse 
       static int contarAristas(int[][] rel) {
           //Conjunto para almacenar aristas únicas
           HashSet<int[]> lista = new HashSet<>();
           int contador = 0;

           //Se filtran pares (valor1, valor2) donde valor1 != valor2 valor2 los agrega al conjunto
           for (int[] par : rel) {
               if (par[0] != par[1]) {
                   lista.add(new int[]{par[0], par[1]});
               }
           }

           int indice = 0;
           int[][] comp = new int[lista.size()][2];

           //Se agregan aristas no redundantes a comp
           for (int[] num : lista) {
               if (hayArista(comp, num)) {
                   comp[indice++] = num;
               }
           }

           //Número de aristas no redundantes
           return indice;
       }

       //Método que devuelve un valor boolean si hay arista en un par de vértices
       static boolean hayArista(int[][] arr, int[] comp) {
           //Como rel es bidimensional, sólo se pueden comprobar estos 
           for (int[] i : arr) {
               if (i[0] == 0 && (i[1] == comp[0] || i[1] == comp[1]) && (i[0] == comp[0] || i[0] == comp[1])) {
                   return true;
               }
               if (i[1] == 0 && (i[0] == comp[0] || i[0] == comp[1]) && (i[1] == comp[0] || i[1] == comp[1])) {
                   return true;
               }
           }
           return false;
       }

       //Reflexiva
       //∀elemento ∃comparacion : elemento.valor1 == comparacion.valor2 ^ elemento.valor2 == comparacion.valor1
       static boolean esReflexiva(int[] a, int[][] rel) {
           for (int elemento : a) {
               boolean existe = false;
               for (int[] comparacion : rel) {
                   //Este condicional está mal, pues lo que dice es que existe (valor1,valor2) tal que valor1 == valor2
                   if (elemento == comparacion[0] && elemento == comparacion[1]) {
                       existe = true;
                   }
               }
               if (!existe) {
                   return false;
               }
           }
           return true;
       }

       //Método que verifica si una relación es antisimétrica
       static boolean esAntisimetrica(int[] a, int[][] rel) {
           for (int[] par1 : rel) {
               for (int[] par2 : rel) {
                   if (par1[0] == par2[1] && par1[1] == par2[0] && par1 != par2) {
                       return false;
                   }
               }
           }
           return true;
       }

       //Método que verifica si una relación es transitiva
       static boolean esTransitiva(int[] a, int[][] rel) {
           for (int[] par1 : rel) {
               for (int[] par2 : rel) {
                   if (par1[1] == par2[0]) {
                       boolean existe = false;
                       for (int[] par3 : rel) {
                           if (par1[0] == par3[0] && par2[1] == par3[1]) {
                               existe = true;
                           }
                       }
                       return true;
                   }
               }
           }
           return false;
       }

        /*
     * Comprovau si les relacions `rel1` i `rel2` són els grafs de funcions amb domini i codomini
     * `a`. Si ho son, retornau el graf de la composició `rel2 ∘ rel1`. Sino, retornau null.
     *
     * Podeu soposar que `a`, `rel1` i `rel2` estan ordenats de menor a major (les relacions,
     * lexicogràficament).
         */
        // Método que combina dos relaciones 'rel1' y 'rel2' en una nueva relación
        static int[][] exercici4(int[] a, int[][] rel1, int[][] rel2) {
            HashSet<int[]> lista = new HashSet<>(); // Lista con todos los elementos de la unión
            //array bidimensional 
            int[][] comp;

            // Verifica si rel1 valor2 rel2 son funciones de grafos 
            if (!grafFuncions(a, rel1) || !grafFuncions(a, rel2)) {
                return null;
            }

            // Crea la nueva relación, unión de 'rel1' valor2 'rel2'
            for (int[] i : rel1) {
                for (int[] j : rel2) {
                    if (i[1] == j[0]) {
                        lista.add(new int[]{i[0], j[1]});
                    }
                }
            }
            
            
            //transformamos a array 
            int indice = 0;
            comp = new int[lista.size()][2];
            for (int[] num : lista) {
                if (!estaLista(comp, num)) {
                    comp[indice++] = num;
                }
            }
            return comp;//TODO
        }

        // Método para verificar si un par está en la lista
        static boolean estaLista(int[][] arr, int[] comp) {
            for (int[] i : arr) {
                if (comp[0] == i[0] && comp[1] == i[1]) {
                    return true;
                }
            }
            return false;
        }

        // Método que verifica si una relación es una función gráfica
        public static boolean grafFuncions(int[] a, int[][] rel) {
            // Para ver si son grafos comprobar 
            // que cada elemento de 'a' está presente como dominio valor2 codominio exactamente una vez
            boolean[] domRel = new boolean[a.length];
            boolean[] codomRel = new boolean[a.length];

            for (int[] var : rel) {
                int dominio = var[0];
                int codominio = var[1];

                for (int i = 0; i < a.length; i++) {
                    if (a[i] == dominio) {
                        if (domRel[i]) {
                            return false;
                        }
                        domRel[i] = true;
                    }

                    if (a[i] == codominio) {
                        if (codomRel[i]) {
                            return false;
                        }
                        codomRel[i] = true;
                    }
                }
            }

            for (int i = 0; i < a.length; i++) {
                if (!domRel[i] || !codomRel[i]) {
                    return false;
                }
            }
            return true;
        }

        /*
     * Comprovau si la funció `f` amb domini `dom` i codomini `codom` té inversa. Si la té, retornau
     * el seu graf (el de l'inversa). Sino, retornau null.
         */
        //Inyectiva: Para todo x1, x2 perteneciente a D, si x1 se relaciona con x2, entonces f(x1) = f(x2) -> a
        //Sobreyectiva: Para cada y perteneciente a C, Existe alguna x perteneciente a D tal que f(x)=y
        static int[][] exercici5(int[] dom, int[] codom, Function<Integer, Integer> f) {
                //declaración e instancación del array bidimensional que controlara el programa   
      int[][] inversa = new int[dom.length][2];
            //dom: valor1    
            //codom: valor2

            //Calcular si es inyectiva
            for (int valor1 : dom) {
                for (int valor2 : dom) {
                    if (f.apply(valor1) == f.apply(valor2) && valor1 != valor2) {
                        return null;
                    }
                }
            }

            //Calcular si es sobreyectiva
            for (int valor2 : codom) {
                boolean existeValor2 = false;
                for (int valor1 : dom) {
                    if (f.apply(valor1) == valor2) {
                        existeValor2 = true;
                    }
                }
                if (!existeValor2) {
                    return null;
                }
            }

            //Calcular inversa, girar los elementos de un subconjunto
            for (int i = 0; i < dom.length; i++) {
                inversa[i] = new int[]{f.apply(dom[i]), dom[i]};
            }

            return inversa; // TODO
        
        }
    

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // |(a u b) × (a \ c)|

            assertThat(
                    exercici1(
                            new int[]{0, 1, 2},
                            new int[]{1, 2, 3},
                            new int[]{0, 3}
                    )
                    == 8
            );

            assertThat(
                    exercici1(
                            new int[]{0, 1},
                            new int[]{0},
                            new int[]{0}
                    )
                    == 2
            );

            // Exercici 2
            // nombre d'elements de la clausura d'equivalència
            final int[] int08 = {0, 1, 2, 3, 4, 5, 6, 7, 8};

            assertThat(exercici2(int08, generateRel(int08, (x, y) -> y == x + 1)) == 81);

            final int[] int123 = {1, 2, 3};

            assertThat(exercici2(int123, new int[][]{{1, 3}}) == 5);

            // Exercici 3
            // Si rel és un ordre total, retornar les arestes del diagrama de Hasse
            final int[] int05 = {0, 1, 2, 3, 4, 5};

            assertThat(exercici3(int05, generateRel(int05, (x, y) -> x >= y)) == 5);
            assertThat(exercici3(int08, generateRel(int05, (x, y) -> x <= y)) == -2);

            // Exercici 4
            // Composició de grafs de funcions (null si no ho son)
            assertThat(
                    exercici4(
                            int05,
                            generateRel(int05, (x, y) -> x * x == y),
                            generateRel(int05, (x, y) -> x == y)
                    )
                    == null
            );

            var ex4test2 = exercici4(
                    int05,
                    generateRel(int05, (x, y) -> x + y == 5),
                    generateRel(int05, (x, y) -> y == (x + 1) % 6)
            );

            assertThat(
                    Arrays.deepEquals(
                            lexSorted(ex4test2),
                            generateRel(int05, (x, y) -> y == (5 - x + 1) % 6)
                    )
            );

            // Exercici 5
            // trobar l'inversa (null si no existeix)
            assertThat(exercici5(int05, int08, x -> x + 3) == null);

            assertThat(
                    Arrays.deepEquals(
                            lexSorted(exercici5(int08, int08, x -> 8 - x)),
                            generateRel(int08, (x, y) -> y == 8 - x)
                    )
            );
        }

        /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Els (di)grafs vendran donats com llistes d'adjacència (és a dir, tractau-los com diccionaris
   * d'adjacència on l'índex és la clau i els vèrtexos estan numerats de 0 a n-1). Per exemple,
   * podem donar el graf cicle d'ordre 3 com:
   *
   * int[][] c3dict = {
   *   {1, 2}, // veïns de 0
   *   {0, 2}, // veïns de 1
   *   {0, 1}  // veïns de 2
   * };
   *
   * **NOTA: Els exercicis d'aquest tema conten doble**
         */
    }

    /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Els (di)grafs vendran donats com llistes d'adjacència (és a dir, tractau-los com diccionaris
   * d'adjacència on l'índex és la clau i els vèrtexos estan numerats de 0 a n-1). Per exemple,
   * podem donar el graf cicle d'ordre 3 com:
   *
   * int[][] c3dict = {
   *   {1, 2}, // veïns de 0
   *   {0, 2}, // veïns de 1
   *   {0, 1}  // veïns de 2
   * };
   *
   * **NOTA: Els exercicis d'aquest tema conten doble**
     */
    static class Tema3 {

        /*
     * Determinau si el graf és connex. Podeu suposar que `g` no és dirigit.
         */
        static boolean exercici1(int[][] g) {
            int variable = 0;
            //Valor inicial para iniciar la verificación en false 
            boolean inicializado = false;
            for(int i = 0;i<g.length&&!inicializado;i++){
                try{
                    if(g[i].length>0){
                        variable = i;
                        inicializado = true;
                    }
                }
                //control de excepciones
                catch(Exception e){
                        
                }
                
            }
            //en caso de no haber ningún punto al que ir no será conexo
            if(!inicializado){
                return false;
            }

            //Itera sobre cada fila en la matriz de adyacencia g
            for (int[] fila : g) {
                int pases = 0;

                // Verifica si hay un camino desde el nodo inicial a través de fila
                if (!camino(g, fila, pases, variable)) {
                    return false; // Si no se encuentra el camino, devuelve false
                }
            }
            return true;
        }

        //Método que devuelve valor booleano si hay un camino entre vértices 
        static boolean camino(int[][] g, int[] fila, int pases, final int init) {
            //si se han recorrido más pasos que nodos hay, se devuelve true
            if (pases > g.length) {
                return false;
            }

            boolean encontrado = false;
            if (fila.length == 0) {
                return false;
            }
            //comprobar cada arista del nodo
            for (int elemento : fila) {
                if (elemento == init) {
                    return true;
                }
                encontrado = camino(g, g[elemento], pases + 1, init);
                //verificar si en ese camino se ha encontrado
                if (encontrado) {
                    return true;
                }
            }
            return false;
        }

        /*
     * Donat un tablero d'escacs d'amplada `w` fila alçada `h`, determinau quin és el mínim nombre de
     * moviments necessaris per moure un cavall de la casella `fila` a la casella `columna`.
     *
     * Les caselles estan numerades de `0` a `w*h-1` per files. Per exemple, si w=5 fila h=2, el tablero
     * és:
     *   0 1 2 3 4
     *   5 6 7 8 9
     *
     * Retornau el nombre mínim de moviments, o -1 si no és possible arribar-hi.
         */
        static int exercici2(int w, int h, int fila, int columna) {
            //Inicialización del tablero
            int[] vector1 = null;
            int[] vector2 = null;
            int[][] tablero = new int[w][h];
            int num = 0;

            //Rellenar el tablero y encontrar las posiciones de fila y columna
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    if (num == fila) {
                        vector1 = new int[]{x, y}; //Posición de fila
                    }
                    if (num == columna) {
                        vector2 = new int[]{x, y}; //Posición de columna
                    }
                    tablero[x][y] = num++;
                }
            }

            //Verificar si las posiciones de fila y columna fueron encontradas
            if (vector1 == null || vector2 == null) {
                return -1;
            }

            return caminoCaballo(tablero, 0, vector1, vector2, tablero.length + 1);
        }

        static int caminoCaballo(int[][] t, int pases, int[] v1, int[] v2, int distancia) {
            //Verifica si vector1 está fuera de los límites del tablero
            if (v1[0] >= t.length || v1[1] >= t[0].length || v1[0] < 0 || v1[1] < 0) {
                return -1;
            }

            //Verifica si la distancia actual es mayor que la distancia permitida
            if (distancia(v1, v2) > distancia) {
                return -1;
            }

            //Verifica si la posición actual es igual a la posición de destino
            if (t[v1[0]][v1[1]] == t[v2[0]][v2[1]]) {
                return pases;
            }

            //Verifica si el número de pases excede un límite razonable (tamaño del tablero + 5)
            if (pases > t.length + 5) {
                return -1;
            }

            //Movimientos posibles del caballo en ajedrez
            int[] x_p = {1, -1, 1, -1, 2, -2, -2, 2};
            int[] y_p = {2, 2, -2, -2, -1, 1, -1, 1};

            int pases_par = pases;
            pases = -1;

            //Intenta cada movimiento posible del caballo
            for (int i = 0; i < x_p.length; i++) {
                int variableAux = caminoCaballo(t, pases_par + 1, new int[]{v1[0] + x_p[i], v1[1] + y_p[i]}, v2, distancia);
                if (variableAux == -1) {
                    continue;
                }
                //Actualiza el número mínimo de pases
                if (variableAux < pases || pases == -1 || pases == 0) {
                    pases = variableAux;
                }
            }
            return pases;
        }

        //Método que devuelve el valor de la distancia entre dos vectores
        static int distancia(int[] v1, int[] v2) {
            int distanciax = v1[0] - v2[0];
            if (distanciax < 0) {
                distanciax = -distanciax;
            }

            int distanciay = v1[1] - v2[1];
            if (distanciay < 0) {
                distanciay = -distanciay;
            }

            if (distanciay < distanciax) {
                return distanciax;
            }
            return distanciay;
        }

        /*
     * Donat un arbre arrelat (graf dirigit `g`, amb arrel `r`), decidiu si el vèrtex `u` apareix
     * abans (o igual) que el vèrtex `v` al recorregut en preordre de l'arbre.
         */
        static boolean exercici3(int[][] g, int r, int u, int v) {
            //Recorremos el arbre en preorden
            int resultado = preorden(g, r, u, v, new boolean[1]);
            return resultado >= 0;
        }

        //Método que recorre un árbol en preorden
        static int preorden(int[][] g, int nodo, int u, int v, boolean[] VisitaArrel) {
            //Si encontramos u, lo marcamos
            if (nodo == u) {
                VisitaArrel[0] = true;
            }
            //Si encontramos v, verificamos si u ya fue encontrado
            if (nodo == v) {
                if (VisitaArrel[0]) {
                    return 0;
                } else {
                    return -1;
                }
            }
            //Recorremos los hijos del nodo actual
            for (int ad : g[nodo]) {
                int resultado = preorden(g, ad, u, v, VisitaArrel);
                if (resultado != 2) {
                    return resultado;
                }
            }
            return 2;
        }

        /*
     * Donat un recorregut en preordre (per exemple, el primer vèrtex que hi apareix és `preord[0]`)
     * i el grau de cada vèrtex (per exemple, el vèrtex `i` té grau `d[i]`), trobau l'altura de
     * l'arbre corresponent.
     *
     * L'altura d'un arbre arrelat és la major distància de l'arrel a les fulles.
         */
        static int exercici4(int[] preord, int[] d) {
            return altura(d);
        }

        //Método para el ejercicio 4
        static int altura(int[] d) {
            //Array para almacenar valores temporales
            int[] valores = new int[d.length]; 
            //altura máxima
            int alturaMax = 0; 
            int indice = -1;

            for (int el : d) {
                //Aumenta la profundidad si el índice actual es mayor que la profundidad máxima
                if (indice + 1 > alturaMax) {
                    alturaMax = indice + 1;
                }

                valores[++indice] = el;
                valores[indice] -= 1;

                //Ajusta el índice mientras el valor en la posición actual sea menor o igual a 0
                while (valores[indice] <= 0) {
                    indice -= 1;
                    //Si el índice es menor a 0, retorna la profundidad máxima encontrada
                    if (indice < 0) {
                        return alturaMax;
                    }
                }
            }
            return alturaMax;
        }

        /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // G connex?

            final int[][] B2 = {{}, {}};

            final int[][] C3 = {{1, 2}, {0, 2}, {0, 1}};

            final int[][] C3D = {{1}, {2}, {0}};

            assertThat(exercici1(C3));
            assertThat(!exercici1(B2));

            // Exercici 2
            // Moviments de cavall
            // Tauler 4x3. Moviments de 0 a 11: 3.
            // 0  1   2   3
            // 4  5   6   7
            // 8  9  10  11
            assertThat(exercici2(4, 3, 0, 11) == 3);

            // Tauler 3x2. Moviments de 0 a 2: (impossible).
            // 0 1 2
            // 3 4 5
            assertThat(exercici2(3, 2, 0, 2) == -1);

            // Exercici 3
            // u apareix abans que v al recorregut en preordre (o u=v)
            final int[][] T1 = {
                {1, 2, 3, 4},
                {5},
                {6, 7, 8},
                {},
                {9},
                {},
                {},
                {},
                {},
                {10, 11},
                {},
                {}
            };

            assertThat(exercici3(T1, 0, 5, 3));
            assertThat(!exercici3(T1, 0, 6, 2));

            // Exercici 4
            // Altura de l'arbre donat el recorregut en preordre
            final int[] P1 = {0, 1, 5, 2, 6, 7, 8, 3, 4, 9, 10, 11};
            final int[] D1 = {4, 1, 3, 0, 1, 0, 0, 0, 0, 2, 0, 0};

            final int[] P2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            final int[] D2 = {2, 0, 2, 0, 2, 0, 2, 0, 0};

            assertThat(exercici4(P1, D1) == 3);
            assertThat(exercici4(P2, D2) == 4);
        }
    }

    /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * En aquest tema no podeu:
   *  - Utilitzar la força bruta per resoldre equacions: és a dir, provar tots els nombres de 0 a n
   *    fins trobar el que funcioni.
   *  - Utilitzar long, float ni double.
   *
   * Si implementau algun dels exercicis així, tendreu un 0 d'aquell exercici.
     */
    static class Tema4 {

        /*
       * Calculau el mínim comú múltiple de `a` i `b`.
         */
        static int exercici1(int a, int b) {
            // Convertir a y b en valor absoluto
            if (a < 0) {
                a = -a;
            }
            if (b < 0) {
                b = -b;
            }

            // Calcular el mcd usando la función MCD
            int mcd = mcd(a, b);

            // Calcular y retorna el mcm usando la fórmula (a * b) / mcd
            return (a * b) / mcd;
        }


        /*
       * Trobau totes les solucions de l'equació
       *
       *   a·x ≡ b (mod n)
       *
       * donant els seus representants entre 0 i n-1.
       *
       * Podeu suposar que `n > 1`. Recordau que no no podeu utilitzar la força bruta.
         */
        static int[] exercici2(int a, int b, int n) {
            //Calculo del maximo común divisor de los dos números
            int d = mcd(a, n);

            //Si b no es divisible por d, significa que no hay ninguna solución 
            if (b % d != 0) {
                return new int[0];
            }

            //Reducimos los numeros para calculos más óptimos
            a /= d;
            b /= d;
            n /= d;

            //Encuentra una solución utilizando el inverso
            int x = Inverso(a, n) * b % n;

            //Todas las soluciones 
            List<Integer> solutions = new ArrayList<>();
            for (int i = 0; i < d; i++) {
                solutions.add((x + i * n) % (n * d));
            }
            //introducimos en un array
            int[] resultat = new int[solutions.size()];
            for (int i = 0; i < solutions.size(); i++) {
                resultat[i] = solutions.get(i);
            }
            return resultat;
        }

        //Metodo que calcula el Máximo común divisor
        static int mcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }

        //Calcula el inverso de a módulo n con Euclides
        static int Inverso(int a, int n) {
            int[] result = Euclides(a, n);
            int x = result[0];
            return (x % n + n) % n;
        }

        //Algoritmo de Euclides
        static int[] Euclides(int a, int b) {
            if (b == 0) {
                return new int[]{1, 0};
            }
            int[] resultado = Euclides(b, a % b);
            int x = resultado[1];
            int y = resultado[0] - (a / b) * resultado[1];
            return new int[]{x, y};
        }

        /*
       * Donats `a != 0`, `b != 0`, `c`, `d`, `m > 1`, `n > 1`, determinau si el sistema
       *
       *   a·x ≡ c (mod m)
       *   b·x ≡ d (mod n)
       *
       * té solució.
         */
        static boolean exercici3(int a, int b, int c, int d, int m, int n) {
            int mcdAM = mcd(a, m);
            int mcdBN = mcd(b, n);
            int residuoC = c % mcdAM;
            int residuoD = d % mcdBN;

            //Calcula la diferencia de residuos 
            int diferencia_residuos = (d / mcdBN - c / mcdAM) % mcd(m / mcdAM, n / mcdBN);

            //Verifica si los residuos son cero y si la diferencia de residuos es cero
            return residuoC == 0 && residuoD == 0 && diferencia_residuos == 0;
        }


        /*
       * Donats `n` un enter, `k > 0` enter, i `p` un nombre primer, retornau el residu de dividir n^k
       * entre p.
       *
       * Alerta perquè és possible que n^k sigui massa gran com per calcular-lo directament.
       * De fet, assegurau-vos de no utilitzar cap valor superior a max{n, p²}.
       *
       * Anau alerta també abusant de la força bruta, la vostra implementació hauria d'executar-se en
       * qüestió de segons independentment de l'entrada.
         */
        static int exercici4(int n, int k, int p) {
            int phiEuler = p - 1; //Calcula el valor de la función phiEuler de Euler para p

            k = k % phiEuler; //Reduce k módulo phiEuler para optimizar el cálculo
            n = ((n % p) + p) % p; //Asegura que n esté en el rango [0, p-1]

            int resultado = 1;

            //Calcular n^k mod p usando el algoritmo de exponenciación por cuadrados
            while (k > 0) {
                if (k % 2 == 1) {
                    resultado = (resultado * n) % p; //Multiplica resultado por n y aplica módulo p si k es impar
                }

                n = (n * n) % p; //Eleva n al cuadrado y aplica módulo p
                k = k / 2; //Divide k por 2 para reducir el problema a la mitad en cada iteración
            }

            resultado = ((resultado % p) + p) % p;
            return resultado;
        }


        /*
       * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
            // Exercici 1
            // mcm(a, b)

            assertThat(exercici1(35, 77) == 5 * 7 * 11);
            assertThat(exercici1(-8, 12) == 24);

            // Exercici 2
            // Solucions de a·x ≡ b (mod n)
            assertThat(Arrays.equals(exercici2(2, 2, 4), new int[]{1, 3}));
            assertThat(Arrays.equals(exercici2(3, 2, 4), new int[]{2}));

            // Exercici 3
            // El sistema a·x ≡ c (mod m), b·x ≡ d (mod n) té solució?
            assertThat(exercici3(3, 2, 2, 2, 5, 4));
            assertThat(!exercici3(3, 2, 2, 2, 10, 4));

            // Exercici 4
            // n^k mod p
            assertThat(exercici4(2018, 2018, 5) == 4);
            assertThat(exercici4(-2147483646, 2147483645, 46337) == 7435);
        }
    }

    /**
     * Ordena lexicogràficament un array de 2 dimensions Per exemple: arr =
     * {{1,0}, {2,2}, {0,1}} resultat = {{0,1}, {1,0}, {2,2}}
     */
    static int[][] lexSorted(int[][] arr) {
        if (arr == null) {
            return null;
        }

        var arr2 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr2, Arrays::compare);
        return arr2;
    }

    /**
     * Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que
     * satisfàn pred.test(a, b) Per exemple: as = {0, 1} bs = {0, 1, 2} pred =
     * (a, b) -> a == b resultat = {{0,0}, {1,1}}
     */
    static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
        var rel = new ArrayList<int[]>();

        for (int a : as) {
            for (int b : bs) {
                if (pred.test(a, b)) {
                    rel.add(new int[]{a, b});
                }
            }
        }

        return rel.toArray(new int[][]{});
    }

    /// Especialització de generateRel per a = b
    static int[][] generateRel(int[] as, BiPredicate<Integer, Integer> pred) {
        return generateRel(as, as, pred);
    }

    /**
     * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats
     * que haurien de donar els exercicis. Podeu utilitzar-los de guia i també
     * en podeu afegir d'altres (no els tendrem en compte, però és molt
     * recomanable).
     *
     * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un
     * valor sigui `true`.
     */
    public static void main(String[] args) {
        Tema1.tests();
        Tema2.tests();
        Tema3.tests();
        Tema4.tests();
    }

    /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
    static void assertThat(boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :

