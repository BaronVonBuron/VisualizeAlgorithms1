module visualizealgorithms.visualizealgorithms1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens visualizealgorithms to javafx.fxml;
    exports visualizealgorithms;
    opens visualizealgorithms.gui.controller to javafx.fxml;
    exports visualizealgorithms.gui.controller;
    opens visualizealgorithms.gui.model to javafx.base;
}