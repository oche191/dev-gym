package com.oche.gym.graph;


import java.util.*;

public class Bfs {
    static Set<Integer> visited = new HashSet<>();
    static Map<Integer, Set<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for(int i=0; i<q; i++){
            //one query loop
            int nodes = sc.nextInt();

            int[] distances = new int[nodes+1];
            Arrays.fill(distances, -1);

            int edges = sc.nextInt();
            makeGraph(sc, edges);

            printGraph(nodes);
            int startNode = sc.nextInt();
            distances[startNode] = 0;

            traverseAndCount(startNode, distances);

            printDistances(distances, startNode);

            visited.clear();
            graph.clear();
            System.out.println();
        }
    }

    static void makeGraph(Scanner sc, int edges) {
        for(int j=0; j<edges; j++){
            int firstNode = sc.nextInt();
            int secondNode = sc.nextInt();

            addNode(firstNode, secondNode);
            addNode(secondNode, firstNode);
        }
    }

    static void traverseAndCount(int startNode, int[] distances){
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(startNode);
        visited.add(startNode);
        while (!bfsQueue.isEmpty()){
            Integer current = bfsQueue.poll();
            final Set<Integer> adjacent = graph.get(current);
            if(adjacent != null) {
                adjacent.forEach(elem -> {
                    if(!visited.contains(elem)) {
                        bfsQueue.offer(elem);
                        visited.add(elem);
                        distances[elem] = distances[current] + 6;
                    }
                });
            }

        }
    }

    static void printDistances(int[] distances, int startNode){
        for(int i=1; i<distances.length; i++) {
            if(i != startNode){
                System.out.print(distances[i] + " ");
            }
        }
    }

    static void addNode(int firstNode, int secondNode){
        if(graph.containsKey(firstNode)){
            graph.get(firstNode).add(secondNode);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(secondNode);
            graph.put(firstNode, set);
        }
    }

    static void printGraph(int nodeNum) {
        System.out.println("*** *** ***");
        for(int o=1; o<nodeNum+1;o++) {
            if(!graph.containsKey(o)) {
                System.out.print(o + " -> null" );
            } else {
                System.out.print(o + " -> ");
                graph.get(o).forEach(elem -> System.out.print(elem + " "));
            }
            System.out.println();
        }
        System.out.println("*** *** ***");
    }
}