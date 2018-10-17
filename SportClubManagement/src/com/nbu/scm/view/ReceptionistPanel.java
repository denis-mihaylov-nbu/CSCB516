package com.nbu.scm.view;

import java.util.Calendar;

import com.nbu.scm.bean.Court;
import com.nbu.scm.bean.CourtType;
import com.nbu.scm.bean.Reservation;
import com.nbu.scm.bean.Timestamp;
import com.nbu.scm.bean.User;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ReceptionistPanel {

	public void start(User user) {
		Stage stage = new Stage();

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
				
		Tab calendarTab = new Tab("Calendar");
		GridPane calendarGrid = new GridPane();
		

		/* TODO Paginated calendar view of the next 7 days
		 *	Squares will represent all possible reservations
		 *	ROWS are courts
		 *	COLUMNS are days
		 *	Green background if reservation is possible
		 *	Red background if the court is already taken at that hour
		 *
		 *
		 */		
		
		/*Just for view of the reservation panel*/
		
		Button reserve = new Button("Reserve");
		reserve.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	Court court =  new Court(1, 1, new CourtType(1, "Hard"));
		    	new ReservationPanel().start(new Reservation(-1, new Timestamp(Calendar.getInstance()), user.getClub(), court, ""));
		    }
		});
		
		// TODO Show all club information and courts - read only
		
		calendarGrid.add(reserve, 0, 0);
		calendarTab.setContent(calendarGrid);
		tabPane.getTabs().add(calendarTab);	
		
				
		
		Tab clubTab = new Tab("Club information");
		GridPane clubGrid = new GridPane();
		
		// TODO Show all club information and courts - read only
				
		clubTab.setContent(clubGrid);
		tabPane.getTabs().add(clubTab);	
		
		
		
		Tab personalTab = new Tab("Personal information");
		GridPane gridPersonal = new GridPane();
		
		// TODO Personal information edit page
		
		personalTab.setContent(gridPersonal);
		tabPane.getTabs().add(personalTab);

		Scene scene = new Scene(tabPane, 800, 500);
		stage.setScene(scene);
		stage.show();
	}
}
