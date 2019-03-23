import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static ArrayList<List<Integer>> permutationsA;
    private static Vector<String> permutations;
    private static int pointsCount;
    private static Point[] points;
    private static HashMap<String, Double> pathValue;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int commandCode = 1;
        System.out.println("1 : Read File Again");
        System.out.println("2 : Permutation");
        System.out.println("3 : Nearest Neighbour");
        System.out.println("0 : Exit");
        while (commandCode != 0) {
            commandCode = reader.nextInt();
            switch (commandCode) {
                case 1:
                    readFile("src//nemone2.txt");
                    System.out.println("DONE!");
                    break;
                case 2:

                    Long startTimer = System.currentTimeMillis();
                    permutationFinder();
                    Long finishTimer = System.currentTimeMillis();
                    System.out.println();
                    System.out.println("TIMER = " + (finishTimer.doubleValue() - startTimer.doubleValue()));
                    break;
                case 3:
                    Long startTimer1 = System.currentTimeMillis();
                    findNearestNeighbor();
                    Long finishTimer1 = System.currentTimeMillis();
                    System.out.println();
                    System.out.println("TIMER = " + (finishTimer1.doubleValue() - startTimer1.doubleValue()));
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void findPathValues() {
        for (int i = 0; i < pointsCount - 1; i++) {
            for (int j = i + 1; j < pointsCount; j++) {
                int dX = points[i].getX() - points[j].getX(), dY = points[i].getY() - points[j].getY();
                double distVal = Math.sqrt(dX * dX + dY * dY);
                String firstKey = Integer.toString(i) + Integer.toString(j);
                String secondKey = Integer.toString(j) + Integer.toString(i);
                pathValue.put(firstKey, distVal);
                pathValue.put(secondKey, distVal);
            }
        }
    }

    private static void permutationFinder() {
        String basePermutation = "";
        for (int i = 1; i < pointsCount; i++)
            basePermutation += Integer.toString(i);
//        permutations = new Vector<>();
//        findPermutations("", basePermutation);
        permutationsA = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        for (int i = 1; i < pointsCount; i++)
            first.add(i);

        permute(first, 0);
        System.out.println("finding permutation finished!");
        for (int i = 0; i < permutationsA.size(); i++) {
            permutationsA.get(i).add(0, 0);
        }
        pathValue = new HashMap<>();
        findPathValues();
        System.out.println("calculate points distance finished!!");
        double minDistance = Double.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < permutationsA.size(); i++) {
            double dist = 0d;
            for (int j = 0; j < permutationsA.get(i).size() - 1; j++) {
                String key = Integer.toString(permutationsA.get(i).get(j)) + Integer.toString(permutationsA.get(i).get(j + 1));
                dist += pathValue.get(key);
            }
            String key = Integer.toString(permutationsA.get(i).get(permutationsA.get(i).size() - 1)) + "0";
            if (pathValue.get(key) == null)
                System.out.println(key);
            else
                dist += pathValue.get(key);
            if (dist < minDistance) {
                minDistance = dist;
                minIndex = i;
            }
        }
        System.out.println("Min Path Value : " + minDistance);
        System.out.println("Path : " + permutationsA.get(minIndex) + "0");
    }

    private static void readFile(String fileAddress) {
        try {
            FileInputStream fi = new FileInputStream(fileAddress);
            Scanner reader = new Scanner(fi);
            String firstLine = reader.nextLine();
            pointsCount = Integer.parseInt(firstLine);
            points = new Point[pointsCount];
            for (int i = 0; i < pointsCount; i++) {
                String line = reader.nextLine();
                String[] items = line.split(" ");
                points[i] = new Point(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
            }
            fi.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double findNearestNeighbor() {
        int minDistanceIndex = 0;
        double minDistanceValue = 0d;
        int root = 0;
        double path = 0d;
        System.out.println("ROOT : " + root);
        for (int i = 0; i < pointsCount - 1; i++) {
            points[root].setVisited(true);
            minDistanceIndex = -1;
            minDistanceValue = -1d;
            for (int j = 0; j < pointsCount; j++) {
                if (!points[j].isVisited()) {
                    if (minDistanceValue == -1 || points[root].distance(points[j]) < minDistanceValue) {
                        minDistanceValue = points[root].distance(points[j]);
                        minDistanceIndex = j;
                    }
                }
            }
            path += minDistanceValue;
            root = minDistanceIndex;
            System.out.println("ROOT : " + root);
        }
        path += points[root].distance(points[0]);
        System.out.println("ROOT : 0");
        System.out.println("Path Value : " + path);
        return path;
    }

    private static void permute(List<Integer> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                t.add(arr.get(i));
            }
            permutationsA.add(t);
        }
    }


}
