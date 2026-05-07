import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class PathSearcher{
    static Map<Integer, Map<Integer, Double>> dic = new HashMap<>();
    static double maxDist = 0.0;
    static List<Integer> longestPath = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            input = input.replace(" ", "");
            String[] spl = input.split(",");
            int from = Integer.parseInt(spl[0]);
            int to = Integer.parseInt(spl[1]);
            double dist = Double.parseDouble(spl[2]);
        
            if (!dic.containsKey(from)) {
                dic.put(from, new HashMap<>());
            }
            if (!dic.containsKey(to)) {
                dic.put(to, new HashMap<>());
            }

            dic.get(from).put(to, dist);
            dic.get(to).put(from, dist);
        }      
        for (int startID : dic.keySet()) {
            List<Integer> path = new ArrayList<>();
            search(startID, 0.0, path);
        }
        for (int id : longestPath) {
            System.out.print(id + "\r\n");
        }
    }
    
    static void search(int current, double totalDist, List<Integer> path) {
        path.add(current);
        if (maxDist < totalDist) {
            longestPath = new ArrayList<>(path);
            maxDist = totalDist;
        }
        if (current == path.get(0) && path.size() > 1) {
            path.remove(path.size() - 1);
            return;
        }
        for (Map.Entry<Integer, Double> entry : dic.get(current).entrySet()) {
            int neighbor = entry.getKey();
            double betweenDist = entry.getValue();
            if (!path.contains(neighbor) || neighbor == path.get(0)) {
                search(neighbor, totalDist + betweenDist, path);
            }
        }
        path.remove(path.size() - 1);
    }
}
