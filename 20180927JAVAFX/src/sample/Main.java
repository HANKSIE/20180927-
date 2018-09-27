package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    Button $btnArr[] = new Button[10];
    Button $reflash, $exit;
    Label $show;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        setComps(root);
        primaryStage.setTitle("亂數鍵盤");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void setComps(Parent r){

        for (int i=0; i<$btnArr.length; i++){
            $btnArr[i] = (Button) r.lookup("#btn"+Integer.toString(i));
            int finalI = i;
            $btnArr[i].setText(Integer.toString(i));
            $btnArr[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    $show.setText($show.getText()+$btnArr[finalI].getText());
                }
            });
        }

        $reflash = (Button) r.lookup("#reflash");
        $reflash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                $show.setText("");
                reflashBtns();
            }
        });

        $exit = (Button) r.lookup("#exit");
        $exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        $show = (Label) r.lookup("#show");

    }

    public void reflashBtns(){

        Random _rand = new Random(System.currentTimeMillis());
        int _count = 0;
        boolean _flag = true;

        //清空按鈕文字
        for (int i=0; i<10; i++){
            $btnArr[i].setText("");
        }

        while (_count != 10){

            int _randInt = _rand.nextInt(10);

            for (int i = _count; i >= 0; i--){

                if (Integer.toString(_randInt) == ($btnArr[i].getText())){
                   _flag = false;
                }

            }

            if (_flag){
                $btnArr[_count].setText(Integer.toString(_randInt));
                _count++;
            }

        }



    }


    public static void main(String[] args) {
        launch(args);
    }

}
