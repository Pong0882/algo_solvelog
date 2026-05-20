import java.util.*;

class Solution {
    static class Edge {
        int to;
        int ticketIdx;

        Edge(int to, int ticketIdx) {
            this.to = to;
            this.ticketIdx = ticketIdx;
        }
    }

    static HashMap<String, Integer> map;
    static String[] names;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static ArrayList<String> route;
    static String[] answer;

    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        int idx = 0;

        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], idx++);
            }

            if (!map.containsKey(ticket[1])) {
                map.put(ticket[1], idx++);
            }
        }

        names = new String[map.size()];

        for (String key : map.keySet()) {
            names[map.get(key)] = key;
        }

        graph = new ArrayList[map.size()];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < tickets.length; i++) {
            int from = map.get(tickets[i][0]);
            int to = map.get(tickets[i][1]);

            graph[from].add(new Edge(to, i));
        }

        for (int i = 0; i < graph.length; i++) {
            Collections.sort(graph[i], (a, b) -> {
                return names[a.to].compareTo(names[b.to]);
            });
        }

        visited = new boolean[tickets.length];
        route = new ArrayList<>();

        int start = map.get("ICN");
        route.add("ICN");

        dfs(start, 0, tickets.length);

        return answer;
    }

    static boolean dfs(int now, int count, int total) {
        if (count == total) {
            answer = route.toArray(new String[0]);
            return true;
        }

        for (Edge edge : graph[now]) {
            if (!visited[edge.ticketIdx]) {
                visited[edge.ticketIdx] = true;
                route.add(names[edge.to]);

                if (dfs(edge.to, count + 1, total)) {
                    return true;
                }

                route.remove(route.size() - 1);
                visited[edge.ticketIdx] = false;
            }
        }

        return false;
    }
}