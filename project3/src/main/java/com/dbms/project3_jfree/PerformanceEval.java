package com.dbms.project3_jfree;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.*;
import static java.lang.System.out;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import static javafx.application.Application.launch;

public class PerformanceEval extends Application {
    final static String sr1 = "100";
    final static String sr2 = "1000";
    final static String sr3 = "10000";
    final static String sr4 = "25000";
    final static String sr5 = "50000";
    static List<List<Integer>> print_list = new ArrayList<>();
    static String chtDisplay;
    public void start(Stage stage) {
        List<Integer> chartDetails = new ArrayList<>();
//        try read file
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "performance.txt"));
            String line = reader.readLine();
            while (line != null) {
                if(line.contains("MAP")){
                    // read next line
                    line = reader.readLine();
                    for(var i=0 ; i < line.split(" : ").length; i++){
                        for(var j=0; j<line.split(" : ")[i].split(" ").length; j++){
                            chartDetails.add(Integer.parseInt(line.split(" : ")[i].split(" ")[j]));
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        read file end
        stage.setTitle("Performance Graph");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("No of Tuples");
        yAxis.setLabel("time in milli seconds");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("NO_MAP");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("TREE_MAP");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("LINHASH_MAP");
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("HASH_MAP");
        if(chtDisplay == "Select") {
            bc.setTitle("'SELECT' Performance with different Indexing Technique");
            series1.getData().add(new XYChart.Data(sr1, chartDetails.get(0)));
            series1.getData().add(new XYChart.Data(sr2, chartDetails.get(1)));
            series1.getData().add(new XYChart.Data(sr3, chartDetails.get(2)));
            series1.getData().add(new XYChart.Data(sr4, chartDetails.get(3)));
            series1.getData().add(new XYChart.Data(sr5, chartDetails.get(4)));
            series2.getData().add(new XYChart.Data(sr1, chartDetails.get(10)));
            series2.getData().add(new XYChart.Data(sr2, chartDetails.get(11)));
            series2.getData().add(new XYChart.Data(sr3, chartDetails.get(12)));
            series2.getData().add(new XYChart.Data(sr4, chartDetails.get(13)));
            series2.getData().add(new XYChart.Data(sr5, chartDetails.get(14)));
            series3.getData().add(new XYChart.Data(sr1, chartDetails.get(20)));
            series3.getData().add(new XYChart.Data(sr2, chartDetails.get(21)));
            series3.getData().add(new XYChart.Data(sr3, chartDetails.get(22)));
            series3.getData().add(new XYChart.Data(sr4, chartDetails.get(23)));
            series3.getData().add(new XYChart.Data(sr5, chartDetails.get(24)));
            series4.getData().add(new XYChart.Data(sr1, chartDetails.get(30)));
            series4.getData().add(new XYChart.Data(sr2, chartDetails.get(31)));
            series4.getData().add(new XYChart.Data(sr3, chartDetails.get(32)));
            series4.getData().add(new XYChart.Data(sr4, chartDetails.get(33)));
            series4.getData().add(new XYChart.Data(sr5, chartDetails.get(34)));
        } else if (chtDisplay == "Join") {
            bc.setTitle("'JOIN' Performance with different Indexing Technique");
            series1.getData().add(new XYChart.Data(sr1, chartDetails.get(5)));
            series1.getData().add(new XYChart.Data(sr2, chartDetails.get(6)));
            series1.getData().add(new XYChart.Data(sr3, chartDetails.get(7)));
            series1.getData().add(new XYChart.Data(sr4, chartDetails.get(8)));
            series1.getData().add(new XYChart.Data(sr5, chartDetails.get(9)));
            series2.getData().add(new XYChart.Data(sr1, chartDetails.get(15)));
            series2.getData().add(new XYChart.Data(sr2, chartDetails.get(16)));
            series2.getData().add(new XYChart.Data(sr3, chartDetails.get(17)));
            series2.getData().add(new XYChart.Data(sr4, chartDetails.get(18)));
            series2.getData().add(new XYChart.Data(sr5, chartDetails.get(19)));
            series3.getData().add(new XYChart.Data(sr1, chartDetails.get(25)));
            series3.getData().add(new XYChart.Data(sr2, chartDetails.get(26)));
            series3.getData().add(new XYChart.Data(sr3, chartDetails.get(27)));
            series3.getData().add(new XYChart.Data(sr4, chartDetails.get(28)));
            series3.getData().add(new XYChart.Data(sr5, chartDetails.get(29)));
            series4.getData().add(new XYChart.Data(sr1, chartDetails.get(35)));
            series4.getData().add(new XYChart.Data(sr2, chartDetails.get(36)));
            series4.getData().add(new XYChart.Data(sr3, chartDetails.get(37)));
            series4.getData().add(new XYChart.Data(sr4, chartDetails.get(38)));
            series4.getData().add(new XYChart.Data(sr5, chartDetails.get(39)));
        }
        Scene scene  = new Scene(bc,700,800);
        bc.getData().addAll(series1, series2, series3, series4);
        stage.setScene(scene);
        stage.show();
        stage.setScene(scene);
        stage.show();
    }
    public static void appendStrToFile(String mType, String fileName, List<List<Integer>> lst)
    {
        // Try block to check for exceptions
        try {
            // Open given file in append mode by creating an
            // object of BufferedWriter class
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));
            // Writing on output stream
            out.write(mType);
            out.newLine();
            for( var i = 0; i < lst.size(); i++){
//                System.out.println(lst.get(i));
                var item = lst.get(i);
                for(var j = 0; j < item.size(); j++){
                    out.write(item.get(j).toString() + " ");
                }
                if(i != lst.size()-1) out.write(": ");
            }
            out.newLine();
            // Closing the connection
            out.close();
        }
        // Catch block to handle the exceptions
        catch (IOException e) {
            // Display message when exception occurs
            System.out.println("exception occurred" + e);
        }
    }
    public static void main (String [] args){
        var filename = "performance.txt";
        var reset = false;
        File myObj = new File(filename);
        if(reset) myObj.delete();
        var random = new Random();
        var collate_db = new TupleGeneratorImpl ();
        collate_db.addRelSchema ("Student", "id name address status", "Integer String String String", "id", null);
        Table Student = new Table ("Student", "id name address status", "Integer String String String", "id");
        collate_db.addRelSchema ("Professor", "id name deptId", "Integer String String", "id", null);
        Table Professor = new Table ("Professor", "id name deptId", "Integer String String", "id");
        collate_db.addRelSchema ("Course", "crsCode deptId crsName descr", "Integer String String String", "crsCode", null);
        Table Course = new Table ("Course", "crsCode deptId crsName descr", "Integer String String String", "crsCode");
        collate_db.addRelSchema ("Teaching", "crsCode semester profId", "Integer Integer Integer", "crsCode semester",  new String [][] {{ "profId", "Professor", "id" }, { "crsCode", "Course", "crsCode" }});
        Table Teaching = new Table ("Teaching", "crsCode semester profId", "Integer Integer Integer", "crsCode semester");
        collate_db.addRelSchema ("Transcript", "studId crsCode semester grade", "Integer String String String", "studId crsCode semester", new String [][] {{ "studId", "Student", "id"}, { "crsCode", "Course", "crsCode" }, { "crsCode semester", "Teaching", "crsCode semester" }});
        Table Transcript = new Table ("Transcript", "studId crsCode semester grade", "Integer Integer Integer String", "studId crsCode semester");
        var tables = new String [] { "Student", "Professor", "Course", "Teaching", "Transcript" };
//        var tuple_cnt   = new int [][] { {100, 1, 1, 1, 100}, {1000, 1, 1, 1, 1000}, {2500, 1, 1, 1, 2500}, {10000, 1, 1, 1, 10000}};
        var tuple_cnt   = new int [][] { {100, 1, 1, 1, 100}, {1000, 1, 1, 1, 1000}, {10000, 1, 1, 1, 10000}, {25000, 1, 1, 1, 25000}, {50000, 1, 1, 1, 50000} };
//        List<Integer> noOfTuples = new ArrayList<>();dont add tuples count in graph
        List<Integer> selectITime = new ArrayList<>();
        List<Integer> selectNITime = new ArrayList<>();
        List<Integer> joinITime = new ArrayList<>();
        List<Integer> joinNITime = new ArrayList<>();

        chtDisplay = Student.chartDisplay;
        if(chtDisplay == "None"){
            for(var item = 0; item < tuple_cnt.length; item++){
                var tup_data = collate_db.generate (tuple_cnt[item]);
                for(var i = 0; i < tup_data.length; i++) {
                    if (i == 0) { for (var j = 0; j < tup_data[i].length; j++) Student.insert(tup_data[i][j]);}
//            if(i == 1){ for(var j = 0; j < tup_data[i].length; j++) Professor.insert(tup_data [i][j]); }
//            if(i == 2){ for(var j = 0; j < tup_data[i].length; j++) Course.insert(tup_data [i][j]); }
//            if(i == 3){ for(var j = 0; j < tup_data[i].length; j++) Teaching.insert(tup_data [i][j]); }
                    if (i == 4) {
                        for (var j = 0; j < tup_data[i].length; j++) Transcript.insert(tup_data[i][j]);
                    }
                }
                var select_key = tup_data [0][tup_data[0].length - 1 - random.nextInt(tuple_cnt[item][0])][0];
//            noOfTuples.add(tuple_cnt[item][0]);
                if(String.valueOf(Student.mTypePublic) != "NO_MAP") {
                    int startTime = (int) System.currentTimeMillis();
                    Table select_op = Student.select(new KeyType(select_key));
                    int endTime = (int) System.currentTimeMillis();
//                select_op.print();
                    selectITime.add(endTime - startTime);
                    //            selectITime[item] = endTime - startTime;
                    int joinStart = (int) System.currentTimeMillis();
                    out.println("for no of tupples: " + tuple_cnt[item][0]);
                    Table join_op = Student.join("id", "studId", Transcript);
                    int joinEnd = (int) System.currentTimeMillis();
//                join_op.print();
                    joinITime.add(joinEnd - joinStart);
                }
                else{
                    int syssTime = (int) System.currentTimeMillis();
                    Student.NonIndexSelect(new KeyType(select_key));
                    int syseTime = (int) System.currentTimeMillis();
                    selectNITime.add(syseTime - syssTime);
                    int joinNIStart = (int) System.currentTimeMillis();
                    out.println("for no of tupples: " + tuple_cnt[item][0]);
                    Student.nonindexjoin("id", "studId", Transcript);
                    int joinNIEnd = (int) System.currentTimeMillis();
                    joinNITime.add(joinNIEnd - joinNIStart);
                }
            }
            if(String.valueOf(Student.mTypePublic) != "NO_MAP") {
                print_list.add(selectITime);
                print_list.add(joinITime);
            }
            else{
                print_list.add(selectNITime);
                print_list.add(joinNITime);
            }
            appendStrToFile(String.valueOf(Student.mTypePublic), filename, print_list);
        }
        else{
            launch();
        }
    }
}
