package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import resource.*;

import javax.swing.text.View;

public class GridController {

    // MapGrid mapGrid;

    public static GridPane getGrid() {
        MapGrid mapGrid = new MapGrid(10, 10);
        CellState[][] map = mapGrid.map;
        GridPane gridPane = new GridPane();

        //Test Default level
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = new CellState();
            }
        }

        for(int i = 6; i <= 8; i++) {
            for(int j = 3; j <= 5; j++) {
                map[i][j].setState(State.OBSTACLE);
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                switch (map[i][j].getState()) {
                    case VACANT: map[i][j].setImageCode("GrassSample.jpg");
                        break;
                     case OBSTACLE: map[i][j].setImageCode("WaterSample.png");
                     //case OBSTACLE: map[i][j].setImageCode("WaterSample.jpg");
                        break;
                    default:
                }
            }
        }

        //Occupying this
        map[2][4].occupy(new Player("Boi", "bitch", ""));
        map[7][8].occupy(new Monster("Mon", "bitchshah", "monster.png"));
        //Test END


        class DataStackPane extends StackPane{
            private int x, y;

            public DataStackPane(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                Image image = new Image("./resource/media/"+map[i][j].getImageCode());
                ImageView iv1 = new ImageView();
                iv1.setImage(image);
                iv1.setFitHeight(50);
                iv1.setFitWidth(50);

                DataStackPane sp = new DataStackPane(i,j);
                if(map[i][j].isOccupied) {
                    System.out.println("Bitch "+i+" "+j+" "+map[i][j].getOccupier().getImageCode());
                    Image imP = new Image("./resource/media/"+map[i][j].getOccupier().getImageCode());
                    ImageView iv2 = new ImageView();
                    iv2.setFitHeight(25);
                    iv2.setFitWidth(25);
                    iv2.setImage(imP);
                    //TEST END
                    sp.getChildren().addAll(iv1, iv2);
                    gridPane.add(sp, j, i, 1, 1);
                } else  {
                   sp.getChildren().addAll(iv1);
                    gridPane.add(sp, j, i, 1, 1);
                }
               
               sp.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            	    @Override
            	    public void handle(MouseEvent mouseEvent) {
            	    	switch (map[sp.x][sp.y].getState()) {
            	    	 case VACANT:
            	    	 //TODO: move player
               	    	 break;
            	    	 case OBSTACLE:
            	    	 //nothing for now
                   	     break;
                       	 default:
                       	//nothing for now
                       	 break;
            	    	}
            	    }
            	}); 
            }
        }


        return gridPane;
    }
}
