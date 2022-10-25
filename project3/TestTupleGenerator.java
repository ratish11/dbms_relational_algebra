 
/*****************************************************************************************
 * @file  TestTupleGenerator.java
 *
 * @author   Sadiq Charaniya, John Miller
 */

import javax.xml.crypto.dsig.TransformService;

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
        Table Student = new Table ("Student", "id name address status",
                "Integer String String String", "id");
        test.addRelSchema ("Student",
                           "id name address status",
                           "Integer String String String",
                           "id",
                           null);
        Table Professor = new Table ("Professor", "id name deptId",
                "Integer String String", "id");
        test.addRelSchema ("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id",
                           null);
        Table Course = new Table ("Course", "crsCode deptId crsName descr",
                "String String String String", "crsCode");
        test.addRelSchema ("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode",
                           null);
        Table Teaching = new Table ("Teaching", "crsCode semester profId",
                "String String Integer", "crsCode semester");
        test.addRelSchema ("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crsCode semester",
                           new String [][] {{ "profId", "Professor", "id" },
                                            { "crsCode", "Course", "crsCode" }});
        Table Transcript = new Table ("Transcript", "studId crsCode semester grade",
                "Integer String String String", "studId crsCode semester");
        test.addRelSchema ("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester",
                           new String [][] {{ "studId", "Student", "id"},
                                            { "crsCode", "Course", "crsCode" },
                                            { "crsCode semester", "Teaching", "crsCode semester" }});

        var tables = new String [] { "Student", "Professor", "Course", "Teaching", "Transcript" };
        var tups   = new int [] { 10, 5, 10, 20, 10 };
    
        var resultTest = test.generate (tups);

        for(var i = 0; i < resultTest.length; i++){
            if(i == 0){ for(var j = 0; j < resultTest[i].length; j++) Student.insert(resultTest [i][j]); Student.save();}
            if(i == 1){ for(var j = 0; j < resultTest[i].length; j++) Professor.insert(resultTest [i][j]); Professor.save();}
            if(i == 2){ for(var j = 0; j < resultTest[i].length; j++) Course.insert(resultTest [i][j]); Course.save();}
            if(i == 3){ for(var j = 0; j < resultTest[i].length; j++) Teaching.insert(resultTest [i][j]); Teaching.save();}
            if(i == 4){ for(var j = 0; j < resultTest[i].length; j++) Transcript.insert(resultTest [i][j]); Transcript.save();}
        }
//        for (var i = 0; i < resultTest.length; i++) {
//            out.println (tables [i]);
//            for (var j = 0; j < resultTest [i].length; j++) {
//                for (var k = 0; k < resultTest [i][j].length; k++) {
//                    out.print (resultTest [i][j][k] + ",");
//                } // for
//                out.println ();
//            } // for
//            out.println ();
//        } // for
    } // main

} // TestTupleGenerator

