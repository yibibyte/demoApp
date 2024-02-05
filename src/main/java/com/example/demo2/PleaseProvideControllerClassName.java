package com.example.demo2;

/**
 * Sample Skeleton for 'Untitled' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 */
public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="RenameFile"
    private Button RenameFile; // Value injected by FXMLLoader

    @FXML // fx:id="butChooseDirectory"
    private Button butChooseDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="buttonChooseFile"
    private Button buttonChooseFile; // Value injected by FXMLLoader

    @FXML // fx:id="textFiledChooseDirectory"
    private TextField textFiledChooseDirectory; // Value injected by FXMLLoader

    /**
     * @param resources
     */
    public PleaseProvideControllerClassName(ResourceBundle resources) {
        this.resources = resources;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert RenameFile != null : "fx:id=\"RenameFile\" was not injected: check your FXML file 'Untitled'.";
        assert butChooseDirectory != null : "fx:id=\"butChooseDirectory\" was not injected: check your FXML file 'Untitled'.";
        assert buttonChooseFile != null : "fx:id=\"buttonChooseFile\" was not injected: check your FXML file 'Untitled'.";
        assert textFiledChooseDirectory != null : "fx:id=\"textFiledChooseDirectory\" was not injected: check your FXML file 'Untitled'.";

    }

}
