//import javax.swing.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;
//import java.time.Instant;
//import java.time.Duration;

public class PerformanceEval {
    public static void appendStrToFile(String mType, String fileName, List lst)
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
                List item = Collections.singletonList(lst.get(i));
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
        var tuple_cnt   = new int [][] { {100, 1, 1, 1, 100}};
//        var tuple_cnt   = new int [][] { {100, 1, 1, 1, 100}, {1000, 1, 1, 1, 1000}, {2500, 1, 1, 1, 2500}, {5000, 1, 1, 1, 5000}, {10000, 1, 1, 1, 10000}, {100000, 1, 1, 1, 100000} };
        int[] noOfTuples = new int[tuple_cnt.length];
        int[] selectITime = new int[tuple_cnt.length];
        int[] selectNITime = new int[tuple_cnt.length];
        int[] joinITime = new int[tuple_cnt.length];
        int[] joinNITime = new int[tuple_cnt.length];
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
            noOfTuples[item] = tuple_cnt[item][0];
            int startTime = (int) System.currentTimeMillis();
            Student.select(new KeyType (select_key));
            int endTime = (int) System.currentTimeMillis();
            selectITime[item] = endTime - startTime;
            int joinStart = (int) System.currentTimeMillis();
            Student.join("id", "studId", Transcript);
            int joinEnd = (int) System.currentTimeMillis();
            joinITime[item] = joinEnd - joinStart;
//            out.println(endTime - startTime);

            int syssTime = (int) System.currentTimeMillis();
            Student.NonIndexSelect(new KeyType (select_key));
            int syseTime = (int) System.currentTimeMillis();
            selectNITime[item] = syseTime - syssTime;
            int joinNIStart = (int) System.currentTimeMillis();
            Student.nonindexjoin("id", "studId", Transcript);
            int joinNIEnd = (int) System.currentTimeMillis();
            joinNITime[item] = joinNIEnd - joinNIStart;
        }
//        out.println("No_of_Tuples \n" + noOfTuples);
//        out.println("indexed_select \n" + selectITime);
//        out.println("non_index_select \n" + selectNITime);
//        out.println("index join \n" + joinITime);
        out.println("non index join " + joinNITime);
        List<int[]> print_list = new ArrayList<>();
//        ArrayList print_list = new ArrayList();
        print_list.add(noOfTuples);
        print_list.add(selectITime);
        print_list.add(selectNITime);
        print_list.add(joinITime);
        print_list.add(joinNITime);
//        BarPanel barGraph = new BarPanel(joinNITime);
//        JFrame f=new JFrame();
//        f.add(barGraph);
//        f.setSize(1000,1000);
//        //f.setLayout(null);
//        f.setVisible(true);


        appendStrToFile(String.valueOf(Student.mTypePublic), filename, print_list);

    }
}
