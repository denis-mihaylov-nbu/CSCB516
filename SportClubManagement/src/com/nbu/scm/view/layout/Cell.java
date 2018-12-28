package com.nbu.scm.view.layout;

import java.util.Calendar;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends StackPane {

	Text text;
	Rectangle rectangle;
	Calendar cal;

	public Cell(String textValue, Paint fill, Paint stroke) {
		this(textValue, fill, stroke, null, null);
	}

	public Cell(Integer textValue, Paint fill, Paint stroke, Calendar cal, EventHandler<MouseEvent> mouseEvent) {
		this(String.valueOf(textValue), fill, stroke, cal, mouseEvent);
	}

	public Cell(String textValue, Paint fill, Paint stroke, Calendar cal, EventHandler<MouseEvent> mouseEvent) {
		rectangle = new Rectangle(80, 30);
		rectangle.setFill(fill);
		rectangle.setStroke(stroke);
		text = new Text(textValue);
		this.cal = cal;
		getChildren().addAll(rectangle, text);
		if (mouseEvent != null) {
			addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent);
		}
	}

	public Calendar getCal() {
		return cal;
	}

	@Override
	public String toString() {
		return "Cell [text=" + text + "]";
	}

}