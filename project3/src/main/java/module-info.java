module com.dbms.project3_jfree {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfreechart;
    requires jcommon;



    opens com.dbms.project3_jfree to javafx.fxml;
    exports com.dbms.project3_jfree;
}