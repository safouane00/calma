/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Personne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.servicePersonne;
import static java.time.temporal.TemporalQueries.localDate;
import java.time.*;
import java.sql.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author safouane
 */
public class User1Controller implements Initializable {

    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btnupdate;

    private String mail;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private DatePicker tfdate;
    @FXML
    private RadioButton tffemme;
    @FXML
    private ToggleGroup gender1;
    @FXML
    private RadioButton tfhomme;
    @FXML
    private Label lback;
    @FXML
    private ImageView img;

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                Image image = new Image("/gui/hmmmllodoov.jpeg");
        img.setImage(image);
    }

    public void tafficher(String mail) throws SQLException {
        servicePersonne ser = new servicePersonne();
        ObservableList<Personne> list;

        list = (ObservableList<Personne>) ser.readall4(mail);
        Personne p = list.get(0);

        this.tfprenom.setText(p.getPrenom());
        this.tfnom.setText(p.getNom());
        this.tfmail.setText(p.getMail());
        this.tfmdp.setText(p.getMdp());
        LocalDate date = Instant.ofEpochMilli(p.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.tfdate.setValue(date);

    }

    @FXML
    private void update(ActionEvent event) {
        servicePersonne ser = new servicePersonne();
//        Date date = (Date) Date.from(tfdate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
      
          java.util.Date date = java.util.Date.from(tfdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
          Date sdate = new Date(date.getTime());
          System.out.println(sdate);
        try {
            if ((tffemme.isSelected())&&(!tfprenom.getText().isEmpty())&&(!tfnom.getText().isEmpty()) &&(!tfmdp.getText().isEmpty()) &&(!tfmail.getText().isEmpty()) ){
                ser.update(new Personne(tfnom.getText(), tfprenom.getText(), sdate, "femme", tfmdp.getText(), tfmail.getText(), null, "user"));
                 JOptionPane.showMessageDialog(null, "user updated succesfully");
            }
            else if ((tfhomme.isSelected())&&(!tfprenom.getText().isEmpty())&&(!tfnom.getText().isEmpty()) &&(!tfmdp.getText().isEmpty()) &&(!tfmail.getText().isEmpty()) ){
                ser.update(new Personne(tfnom.getText(), tfprenom.getText(), sdate, "homme", tfmdp.getText(), tfmail.getText(), null, "user"));
                 JOptionPane.showMessageDialog(null, "user updated succesfully");
                }
            else
                JOptionPane.showMessageDialog(null, "remplir tout les champs");

               
            
        } catch (SQLException ex) {
            System.out.println(ex);

            JOptionPane.showMessageDialog(null, "try again");
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/authentifier.fxml"));
        Parent root = loader.load();
        tfmail.getScene().setRoot(root);
    }

}
