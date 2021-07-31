module WidgetDemoSceneBuilder {
	requires javafx.controls;
	requires javafx.fxml;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
