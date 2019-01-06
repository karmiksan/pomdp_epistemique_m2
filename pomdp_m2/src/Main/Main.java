package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

/**
 *
 * @author Guillaume Drouart
 * @author Hazar Zaoui
 */
public class Main {

    static Map<Integer, Map<Integer, Double>> mapAgentsMax = new HashMap<>();
    static Map<Integer, Map<Integer, Double>> mapAgentsMin = new HashMap<>();
    static List<List<Integer>> clusterList = new ArrayList<List<Integer>>();

    static String[] listOfAgent1 = {"Agent0", "Agent1", "Agent2", "Agent3"};
    static String[] listOfFeatures1 = {"Civile", "Militaire"};
    static double[][] listOfValue11 = {{0.6, 0.4}, {0.4, 0.6}, {0.85, 0.15}, {0.75, 0.25}};
    static double[][] listOfValue12 = {{0.8, 0.2}, {0.7, 0.3}, {0.95, 0.05}, {0.85, 0.15}};
    static double[][] listOfValue13 = {{0.98, 0.02}, {0.97, 0.03}, {0.99, 0.01}, {0.96, 0.04}};

    static String[] listOfAgent2 = {"Agent0", "Agent1", "Agent2", "Agent3", "Agent4"};
    static String[] listOfFeatures2 = {"Rouge", "Vert", "Bleu", "Blanc"};
    static double[][] listOfValue2 = {{0.5, 0.1, 0.3, 0.1}, {0.05, 0.5, 0.05, 0.4}, {0.1, 0.1, 0.5, 0.3}, {0.04, 0.01, 0.05, 0.95}, {0.1, 0.1, 0.1, 0.7}};

    static String[] listOfAgent3 = {"Agent0", "Agent1", "Agent2", "Agent3", "Agent4", "Agent5", "Agent6"};
    static String[] listOfFeatures3 = {"C1", "C2", "C3"};
    static double[][] listOfValue3 = {{0.98, 0.01, 0.01}, {0.01, 0.98, 0.01}, {0.01, 0.01, 0.98}, {0.34, 0.33, 0.33}, {0.33, 0.34, 0.33}, {0.33, 0.33, 0.34}, {0.25, 0.5, 0.25}};

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public static double BC(double[] p, double[] q) {
        double sum = 0;
        for (int i = 0; i < q.length; i++) {
            sum += Math.sqrt(Math.abs(q[i] * p[i]));
        }
        return sum;
    }

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public static double distanceBattacharya(double[] p, double[] q) {
        return Math.abs(-Math.log(BC(p, q)));
    }

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public static double distanceHellinger(double[] p, double[] q) {
        return Math.sqrt(1 - BC(p, q));
    }

    /**
     *
     * @param p
     * @param q
     * @return
     */
    public static double divergenceKBL(double[] p, double[] q) {
        double sum = 0;
        for (int i = 0; i < q.length; i++) {
            sum += p[i] * Math.log(p[i] / q[i]);
        }
        return Math.abs(sum);
    }

    /**
     *
     * @param liste
     * @param typeDistance
     * @return
     */
    public static double[][] matriceDistanceAgents(double[][] liste, String typeDistance) {
        int nbAgent = liste.length;
        double[][] distance = new double[nbAgent][nbAgent];
        for (int i = 0; i < liste.length; i++) {
            for (int j = 0; j < liste.length; j++) {
                if ("B".equals(typeDistance)) {
                    distance[i][j] = distanceBattacharya(liste[i], liste[j]);
                }
                if ("H".equals(typeDistance)) {
                    distance[i][j] = distanceHellinger(liste[i], liste[j]);
                }
                if ("K".equals(typeDistance)) {
                    distance[i][j] = divergenceKBL(liste[i], liste[j]);
                }
            }
        }
        return distance;
    }

    /**
     *
     * @param v
     * @param a
     * @return
     */
    public static Map<Integer, Double> getMax(double[] v, int a) {
        Map<Integer, Double> maxVal = new HashMap<>();
        double max = Double.MIN_VALUE;
        double temp;
        int tempAgent = 0;
        for (int i = 0; i < v.length; i++) {
            if (i != a) {
                temp = v[i];
                if (temp > max) {
                    max = temp;
                    tempAgent = i;
                    maxVal.clear();
                }
                maxVal.put(tempAgent, max);
            }
        }
        return maxVal;
    }

    /**
     *
     * @param v
     * @param a
     * @return
     */
    public static Map<Integer, Double> getMin(double[] v, int a) {
        Map<Integer, Double> minVal = new HashMap<>();
        double min = Double.MAX_VALUE;
        double temp;
        int tempAgent = 0;
        for (int i = 0; i < v.length; i++) {
            if (i != a) {
                temp = v[i];
                if (temp < min) {
                    min = temp;
                    tempAgent = i;
                    minVal.clear();
                }
                minVal.put(tempAgent, min);
            }
        }
        return minVal;
    }

    /**
     *
     * @param mat
     */
    public static void averageDistanceBelieves(double[][] croyance) {
        for (int i = 0; i < croyance.length; i++) {
            for (int j = 0; j < croyance[i].length; j++) {

            }
        }
    }

    /**
     *
     * @param mat
     */
    public static void minMaxAgentsBelieves(double[][] mat) {
        int nbAgent = mat.length;
        for (int i = 0; i < nbAgent; i++) {
            mapAgentsMax.put(i, getMax(mat[i], i));
            mapAgentsMin.put(i, getMin(mat[i], i));
        }
    }

    /**
     *
     * @param maxv
     */
    public static void compareMax(Map<Integer, Map<Integer, Double>> maxv) {

        maxv.entrySet().forEach(new Consumer<Entry<Integer, Map<Integer, Double>>>() {
            @Override
            public void accept(Entry<Integer, Map<Integer, Double>> entry) {
                //            System.out.println(entry.getKey() + " " + entry.getValue().keySet() + " " + (entry.getValue().values()));
                System.out.println(entry.getKey() );//+ " " + entry.getKey().getClass().getName()
                for (int i = 0; i < entry.getValue().size(); i++) {
                    System.out.println(entry.getValue().get(i));
                }
                
            }
        });
    }

    /**
     *
     * @param mat
     * @param croyance
     */
    public static void initCluster(double[][] mat, double[][] croyance) {
//        averageDistanceBelieves(croyance);

        miseAJourCluster(mat, croyance);
        minMaxAgentsBelieves(mat);

    }

    /**
     *
     * @param matx
     * @param croyance
     */
    public static void miseAJourCluster(double[][] matx, double[][] croyance) {

        int nbAgent = matx[0].length;
        int nbCroyance = croyance[0].length;
        double max = Double.MIN_VALUE;
        double tempMax = 0;
        double min = Double.MAX_VALUE;
        double tempMin = 0;
        int agent1min = 0;
        int agent2min = 0;
        int agent1max = 0;
        int agent2max = 0;

        List<Integer>[] cluster = new List[nbCroyance];
        for (int i = 0; i < nbCroyance; i++) {
            cluster[i] = new ArrayList<>();
        }

        for (int i = 0; i < matx.length; i++) {
            for (int j = 0; j < matx[i].length; j++) {

                if (i != j) {
                    tempMin = matx[i][j];
                    tempMax = matx[i][j];
                    if (tempMin < min) {
                        min = tempMin;
                        agent1min = i;
                        agent2min = j;
                    }
                    if (tempMax > max) {
                        max = tempMax;
                        agent1max = i;
                        agent2max = j;

                    }
                }
            }
        }

        cluster[0].add(agent1max);
        cluster[1].add(agent2max);
        nbAgent -= 2;
        if (agent1min == agent1max) {
            cluster[0].add(agent2min);
            nbAgent -= 1;
        } else if (agent2min == agent1max) {
            cluster[0].add(agent1min);
            nbAgent -= 1;
        } else if (agent1min == agent2max) {
            cluster[1].add(agent2min);
            nbAgent -= 1;
        } else if (agent2min == agent2max) {
            cluster[1].add(agent1min);
            nbAgent -= 1;
        } else {
            if (nbCroyance > 2) {
                cluster[2].add(agent1min);
                cluster[2].add(agent2min);
                nbAgent -= 2;
            }
        }

//        if (agent1min != agent1max
//                || agent1min != agent2max
//                || agent2min != agent1max
//                || agent2min != agent2max) {
//            if (nbCroyance > 2) {
//                cluster[2].add(agent1min);
//                cluster[2].add(agent2min);
//                nbAgent -= 2;
//            }
//            else {
//                cluster[0].add(agent1min);
//                cluster[0].add(agent2min);
//                nbAgent -= 2;
//                
//            }
//        }


//        for (int i = 0; i < matx.length; i++) {
//            for (int j = 0; j < matx[i].length; j++) {
//
//                if (i != j &&
//                        i!= agent1max && j!= agent2max &&
//                        i!= agent2max && j!= agent1max &&
//                        i!= agent1min && j!= agent2min &&
//                        i!= agent2min && j!= agent1min) {
//                    tempMin = matx[i][j];
//                    tempMax = matx[i][j];
//                    if (tempMin < min) {
//                        min = tempMin;
//                        agent1min = i;
//                        agent2min = j;
//                    }
//                    if (tempMax > max) {
//                        max = tempMax;
//                        agent1max = i;
//                        agent2max = j;
//
//                    }
//                }
//            }
//        }
//        System.out.println("Min : "+agent1min+" "+agent2min+" Max : "+agent1max+" "+agent2max);
        System.out.println("");
        for (List<Integer> cluster1 : cluster) {
            System.out.println(cluster1);

        }
        System.out.println("");

    }

    /**
     *
     * @param tableau
     * @param index
     * @return
     */
    public static double[][] supprimerIndiceMat(double[][] tableau, int index) {
        int nRows = tableau.length;
        int nColumns = tableau[0].length;

        if (index >= nRows || index >= nColumns) {
            // Return exception ?
            return new double[0][0];
        }

        double[][] newTab = new double[nRows - 1][nColumns - 1];
        int newTabRow = 0;
        int newTabCol = 0;

        for (int i = 0; i < nRows; ++i) {
            if (i != index) {
                for (int j = 0; j < nColumns; ++j) {
                    if (j != index) {
                        newTab[newTabRow][newTabCol] = tableau[i][j];
                        ++newTabCol;
                    }
                }
                ++newTabRow;
                newTabCol = 0;
            }
        }
        return newTab;
    }

    @FunctionalInterface
    public interface InterfaceDistance {

        double distance(double[] p, double[] q);
    }

    /**
     *
     * @param X
     * @param distanceFun
     * @param maxIter
     * @param eps
     * @param k
     */
    public void kmeans(double[][] X, InterfaceDistance distanceFun, int maxIter, double eps, int k) {
        // distanceFun.distance(a, b);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public double euclidianDistance(double[] a, double[] b) {
        // kmeans(X, (a, b) -> euclidianDistance(a, b), 1000, 1e-3)
        return 0.;
    }

    public static void affichage(String[] listAgent, String[] listFeatures, double[][] listValues) {

        System.out.println("Nouveau Test");
        System.out.println("");
        System.out.println("Matrice de croyance :");
        System.out.println(Arrays.toString(listFeatures));
        for (int i = 0; i < listAgent.length; i++) {
            System.out.println("Agent" + String.valueOf(i) + " " + Arrays.toString(listValues[i]));
        }
        System.out.println("");
        double resBa[][] = matriceDistanceAgents(listValues, "B");
        System.out.println("Matrice de distance de Battacharia " + Arrays.toString(listFeatures));
        System.out.println(Arrays.toString(listAgent));
        for (double[] agent : resBa) {
            System.out.println(Arrays.toString(agent));
        }

        System.out.println("");
        System.out.println("Cluster Battacharia : ");
        initCluster(resBa, listValues);
        System.out.println("Liste max ");
        System.out.println(mapAgentsMax.entrySet());
        System.out.println("Liste min ");
        System.out.println(mapAgentsMin.entrySet());
        System.out.println("");

        System.out.println("");
        double resHe[][] = matriceDistanceAgents(listValues, "H");
        System.out.println("Matrice de distance de Hellinger " + Arrays.toString(listFeatures) + " :");
        System.out.println(Arrays.toString(listAgent));
        for (double[] agent : resHe) {
            System.out.println(Arrays.toString(agent));
        }

        System.out.println("");
        System.out.println("Cluster Hellinger : ");
        initCluster(resHe, listValues);
        System.out.println("Liste max ");
        System.out.println(mapAgentsMax.entrySet());
        System.out.println("Liste min ");
        System.out.println(mapAgentsMin.entrySet());
        System.out.println("");

        System.out.println("");
        double resK[][] = matriceDistanceAgents(listValues, "K");
        System.out.println("Matrice de divergence de KBL " + Arrays.toString(listFeatures) + " :");
        System.out.println(Arrays.toString(listAgent));
        for (double[] agent : resK) {
            System.out.println(Arrays.toString(agent));
        }

        System.out.println("");
        System.out.println("Cluster divergence de KBL : ");
        initCluster(resK, listValues);
        System.out.println("Liste max ");
        System.out.println(mapAgentsMax.entrySet());
        System.out.println("Liste min ");
        System.out.println(mapAgentsMin.entrySet());
        System.out.println("");

//        System.out.println("mapAgentsMax");
//        System.out.println("");
//        compareMax(mapAgentsMax);
        compareMax(mapAgentsMax);

    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        affichage(listOfAgent1, listOfFeatures1, listOfValue11);

        affichage(listOfAgent1, listOfFeatures1, listOfValue12);

        affichage(listOfAgent1, listOfFeatures1, listOfValue13);

        affichage(listOfAgent2, listOfFeatures2, listOfValue2);

        affichage(listOfAgent3, listOfFeatures3, listOfValue3);

    }
}
