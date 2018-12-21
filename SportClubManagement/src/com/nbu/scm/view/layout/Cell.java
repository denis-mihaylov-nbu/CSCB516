package com.nbu.scm.view.layout;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends StackPane {

	Text text;
	Rectangle rectangle;

	public Cell(String textValue, Paint fill, Color stroke) {
		this(textValue, fill, stroke, null);
	}
	
	public Cell(String textValue, Paint fill, Paint stroke, EventHandler<MouseEvent> mouseEvent) {
		rectangle = new Rectangle(80, 30);
		rectangle.setFill(fill);
		rectangle.setStroke(stroke);
		text = new Text(textValue);
		getChildren().addAll(rectangle, text);
		if (mouseEvent != null) {
			addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent);
		}
	}


	@Override
	public String toString() {
		return "Cell [text=" + text + "]";
	}

}