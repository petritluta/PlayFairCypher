package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.util.Scanner;


public class Main extends Application  implements  EventHandler<ActionEvent>{
    private TextField key;
    private Label keyExplain;
    private TextField text;
    private Label result;
    private Button encrypt;
    private Button decrypt;
    private VBox root ;
    private Label grid;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PlayFairCypher");
        this.root = new VBox();
        this.root.setSpacing(10);
        this.root.setPadding(new Insets(10,10,10,10));

        this.key=new TextField();
        this.key.setPromptText("Enter your secret key");

        this.keyExplain=new Label();
        this.keyExplain.setFont(new Font("Arial",10));
        this.keyExplain.setText("Çelsi duhet te permbaj vetem shkronja, te përmbaj 1 deri 26 karaktere ose më pak");


        this.text=new TextField();
        this.text.setPromptText("Enter text to be encrypted/decrypted");


        this.result= new Label();


        this.encrypt=new Button();
        this.encrypt.setText("Enkripto");
        this.encrypt.setOnAction(this::handle);


        this.decrypt=new Button();
        this.decrypt.setText("Dekripto");
        this.decrypt.setOnAction(this::handle);

        this.grid=new Label();
        this.grid.setFont(new Font("Arial",20));

        this.root.getChildren().add(this.key);
        this.root.getChildren().add(this.keyExplain);
        this.root.getChildren().add(this.text);
        this.root.getChildren().add(this.result);
        this.root.getChildren().add(this.encrypt);
        this.root.getChildren().add(this.decrypt);
        this.root.getChildren().add(this.grid);

        primaryStage.setScene(new Scene(this.root, 400, 400));

        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.print("Hi welcome to playfair cypher to continue on the cmd  version write number 1 to continue on desktop write number 2:");
        Scanner input =new Scanner(System.in);
        int initChoice=input.nextInt();
        if(initChoice == 1)
        {
            PlayFairCipher pf=new PlayFairCipher('Q',"DEATH");
            pf.printGrid();
            int choice=1;
            do {
                System.out.print("Hi welcome to playfair cypher write 1 to encrypt 2 to decrypt 0 to quit:");
                choice=input.nextInt();
                if(choice==1)
                {
                    System.out.print("Write string to be encrypted:");
                    input.nextLine();
                    String text=input.nextLine();
                    System.out.println();
                    System.out.println("Encrypted string is="+pf.encrypt(text));

                }
                else if(choice==2)
                {
                    System.out.print("Write string to be decrypted:");
                    input.nextLine();
                    String text=input.nextLine();
                    System.out.println();
                    System.out.println("Decrypted string is="+pf.decrypt(text));

                }
            }
            while(choice!=0);
            System.out.println("Its over");
        }
        else if(initChoice == 2)
        {
            launch(args);

        }
        else{
            System.out.println("Oops wrong command!");

        }
        System.exit(0);


    }


    @Override
    public void handle(ActionEvent actionEvent) {
        String encryptionKey = this.key.getText();
        PlayFairCipher pf = new PlayFairCipher('Q',encryptionKey);
        if(pf.validateKey())
        {
            this.grid.setText("Tabela:\n"+pf.getStringGrid());
            String inputText=text.getText();

            if(actionEvent.getSource()==encrypt)
            {
                System.out.println(pf.encrypt(inputText));

                this.result.setText("Teksti i enkriptuar="+pf.encrypt(inputText));
            }
            else if (actionEvent.getSource()==decrypt)
            {
                System.out.println(pf.decrypt(inputText));
                this.result.setText("Teksti i dekriptuar="+pf.decrypt(inputText));

            }
        }
        else{
            Alert error= new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Key is not valid");
            error.setContentText("Çelsi duhet te permbaj vetem shkronja, te përmbaj 1 deri 26 karaktere ose më pak");
            error.showAndWait();
        }

    }
}
