 
/*****************************************************************************************
 * @file  TestTupleGenerator.java
 *
 * @author   Sadiq Charaniya, John Miller
 */
import java.time.Instant;
import java.time.Duration;
import java.util.Random;

import static java.lang.System.out;
/*****************************************************************************************
 * This class tests the TupleGenerator on the Student Registration Database defined in the
 * Kifer, Bernstein and Lewis 2006 database textbook (see figure 3.6).  The primary keys
 * (see figure 3.6) and foreign keys (see example 3.2.2) are as given in the textbook.
 */
public class TestTupleGenerator
{
    /*************************************************************************************
     * The main method is the driver for TestGenerator.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        var test = new TupleGeneratorImpl ();
        Table StudentLM = new Table ("StudentLM", "id name address status",
                "Integer String String String", "id");
        test.addRelSchema ("Student", "id name address status", "Integer String String String", "id", null);
        Table ProfessorLM = new Table ("ProfessorLM", "id name deptId",
                "Integer String String", "id");
        test.addRelSchema ("Professor", "id name deptId", "Integer String String", "id", null);
        Table CourseLM = new Table ("CourseLM", "crsCode deptId crsName descr",
                "Integer String String String", "crsCode");
        test.addRelSchema ("Course", "crsCode deptId crsName descr", "Integer String String String", "crsCode", null);
        Table TeachingLM = new Table ("TeachingLM", "crsCode semester profId", "Integer Integer Integer", "crsCode semester");
        test.addRelSchema ("Teaching", "crsCode semester profId", "Integer Integer Integer", "crsCode semester",  new String [][] {{ "profId", "Professor", "id" }, { "crsCode", "Course", "crsCode" }});
        Table TranscriptLM = new Table ("TranscriptLM", "studId crsCode semester grade",
                "Integer Integer Integer String", "studId crsCode semester");
        test.addRelSchema ("Transcript", "studId crsCode semester grade", "Integer String String String", "studId crsCode semester", new String [][] {{ "studId", "Student", "id"}, { "crsCode", "Course", "crsCode" }, { "crsCode semester", "Teaching", "crsCode semester" }});

        var tables = new String [] { "Student", "Professor", "Course", "Teaching", "Transcript" };
        var tups   = new int [] { 10, 10, 20, 50, 50 };
//        var tups   = new int [] { 5000, 1000, 10000, 2000, 50000 };
    
        var resultTest = test.generate (tups);

//        for(var i = 0; i < resultTest.length; i++){
//            if(i == 0){ for(var j = 0; j < resultTest[i].length; j++) StudentLM.insert(resultTest [i][j]); StudentLM.save();}
//            if(i == 1){ for(var j = 0; j < resultTest[i].length; j++) ProfessorLM.insert(resultTest [i][j]); ProfessorLM.save();}
//            if(i == 2){ for(var j = 0; j < resultTest[i].length; j++) CourseLM.insert(resultTest [i][j]); CourseLM.save();}
//            if(i == 3){ for(var j = 0; j < resultTest[i].length; j++) TeachingLM.insert(resultTest [i][j]); TeachingLM.save();}
//            if(i == 4){ for(var j = 0; j < resultTest[i].length; j++) TranscriptLM.insert(resultTest [i][j]); TranscriptLM.save();}
//        }
//        StudentLM.printIndex();
//        ProfessorLM.printIndex();
//        CourseLM.printIndex();
//        TeachingLM.printIndex();
//        TranscriptLM.printIndex();
//        Instant startTime = Instant.now();
//        Table t_select = Student.join("id", "studId", Transcript);  //join using HashMap
//        Instant endTime = Instant.now();
//        out.println(Duration.between(startTime, endTime));
//        t_select.print ();
    } // main
} // TestTupleGenerator

