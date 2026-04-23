package Main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import DSA.Graphs.GTUGraph;
import DSA.Graphs.MatrixGraph.AdjacencyVect;
import DSA.Graphs.MatrixGraph.MatrixGraph;
import DSA.Graphs.GCA.GCASolution;
import DSA.Graphs.GCA.GreedyGCA;
import DSA.Sorting.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Error: Too few arguments. (Expected: <int:input_file> <String:output_path>)");
            return;
        }

        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
    
            Integer graph_size = Integer.parseInt(scanner.nextLine().strip());
            ArrayList<Integer> arr = new ArrayList<>();
    
            while (scanner.hasNextLine()) {
                String line[] = scanner.nextLine().strip().split(" ");
                arr.add(Integer.parseInt(line[0]) + Integer.parseInt(line[1]));
            }
    
            scanner.close();
    
            ArrayList<GTUSorter> sorters = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
    
             sorters.add(new GTUInsertSort());                         names.add("MYInsertSort");
             sorters.add(new GTUSelectSort());                         names.add("MYSelectSort");
             sorters.add(new GTUQuickSort());                          names.add("MYQuickSort");
             sorters.add(new GTUQuickSort(new GTUInsertSort(), 10));    names.add("MYQuickSort_MYInsertSort");
             sorters.add(new GTUQuickSort(new GTUSelectSort(), 10));    names.add("MYQuickSort_MYSelectSort");
    
            Integer[] tempArr = new Integer[arr.size()];
            for (int i = 0; i < sorters.size(); i++) {
                GTUSorter sorter = sorters.get(i);
                String name = names.get(i);
    
                arr.toArray(tempArr);
                sorter.sort(tempArr, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer arg0, Integer arg1) {
                        return arg1.compareTo(arg0);
                    }
                });
                
                File result = new File(args[1] + name + ".txt");
                FileWriter writer = new FileWriter(result);
                for (Integer integer : tempArr) {
                    writer.write(integer.toString() + "\n");
                }
                writer.close();
            }

            GTUGraph graph = new MatrixGraph();
            GTUGraph.readGraph(args[0], graph);
            GTUGraph.writeGraph(args[1] + "graph.txt", graph);
    
            for (int i = 0; i < sorters.size(); i++) {
                GTUSorter sorter = sorters.get(i);
                String name = names.get(i);
                
                GCASolution solution = GreedyGCA.solve(graph, sorter);
    
                solution.writeSolution(args[1] + name + "_color.txt");
            }

        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
            return;
        }
    }
}