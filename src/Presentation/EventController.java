/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import connex.Connexion;
import entities.Comment;
import entities.Event;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.JSType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author kouss
 */
public class EventController implements Initializable {

    
    EventServices eventserv ;
    Event e ; 
    Comment c ; 

    Connection cn= Connexion.getInstance().getConnection() ; 
    Statement st ; 
    PreparedStatement pst ; 
    
    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, Integer> col_id;
    @FXML
    private TableColumn<Event, String> col_titre;
    @FXML
    private TableColumn<Event, String> col_image1;
    @FXML
    private TableColumn<Event, String> col_date;
    @FXML
    private TableColumn<Event, String> col_description;
    @FXML
    private TableColumn<Event, String> col_etat;
    @FXML
    private TableColumn<Event, String> col_lieu;
     @FXML
    private TableColumn<Event, Integer> col_nbre_participants;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField input_recherche;
    @FXML
    private Button btnconsulter;
   
    public static Event myVariable;
    public static Event myVariable_comment;
    public static Event getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(Event myVariable) {
        EventController.myVariable = myVariable;
    }
    
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button find_event;
    @FXML
    private AnchorPane refresh;
    @FXML
    private Button consulter_comm_btn;
    @FXML
    private Button send_sms_button;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

             try {
                 
                 
                 eventserv = new EventServices() ;
                 e=new Event() ;
                 col_id.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
                 col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 col_image1.setCellValueFactory(new PropertyValueFactory<>("image1"));
                 col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
                 col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
                 col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                 col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                 col_nbre_participants.setCellValueFactory(new PropertyValueFactory<>("nb_participants"));
                 
                 table.setEditable(true); //Rend le tableau "editable"
                 col_titre.setCellFactory(TextFieldTableCell.forTableColumn());
                 col_description.setCellFactory(TextFieldTableCell.forTableColumn());
                // col_date.setCellFactory(TextFieldTableCell.forTableColumn());
                 col_etat.setCellFactory(TextFieldTableCell.forTableColumn());
                 col_lieu.setCellFactory(TextFieldTableCell.forTableColumn());
              //   col_nbre_participants.setCellFactory(TextFieldTableCell.forTableColumn());

                 table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
                 try {
                     table.setItems(eventserv.afficherEvent());
                 } catch (SQLException ex) {
                     Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
                 ObservableList<Event> data;
                 data = eventserv.afficherEvent();
                 
                 
                 /////////// recherche
                 data = FXCollections.observableArrayList();
                 // TicketServices ticks = new TicketServices() ;
                 ObservableList<Event> lista = eventserv.afficherEvent();
                 
                 for(Event s:lista){
                     System.out.println(s);
                     data.add(s);
                 }
                 table.setItems(data);
                 FilteredList<Event> FilteredData= new FilteredList<>(data , e-> true);
                 input_recherche.setOnKeyReleased(e->{
                     input_recherche.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                         FilteredData.setPredicate((Predicate<? super Event>) eve -> {
                             if (newValue == null || newValue.isEmpty()) {
                                 return true;
                             }
                             String lowerCaseFilter = newValue.toLowerCase();
                             if ((eve.getTitre().contains(newValue)) || (eve.getTitre().toLowerCase().contains(newValue))){
                                 return true;
                             }
                             else if(eve.getTitre().toLowerCase().contains(lowerCaseFilter)){
                                 return true ;
                             }
                             
                             if (((eve.getDescription()).contains(newValue)) || (eve.getDescription().toLowerCase().contains(newValue))){
                                 return true;
                             }
                             else if(eve.getDescription().toLowerCase().contains(lowerCaseFilter)){
                                 return true ;
                             }
                             
                             else if(JSType.toString(eve.getNb_participants()).contains(newValue)){
                                 return true ;
                             }
                             
                             return false;
                             
                         });
                     });
                     SortedList<Event> SortedData = new SortedList<>(FilteredData)  ;
                     SortedData.comparatorProperty().bind(table.comparatorProperty());
                     table.setItems(SortedData);
                 });
                 
                 
                 
                 
                 
             } catch (SQLException ex) {
                 Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
             }

         
         
         
         
    }    


    @FXML
    private void handleButtonAddAction(MouseEvent event) {
        
        
       
    }

    @FXML
    private void ajoutAction(ActionEvent event) throws IOException, SQLException {
        
        
            System.out.println("You clicked add button!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Ajout_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
       
           table.setItems(eventserv.afficherEvent());

    }

    @FXML
    private void handleButtonDeleteAction(MouseEvent event) {
    }

    @FXML
    private void supprimerAction(ActionEvent event) throws SQLException {
         System.out.println("You clicked delete button!");

      e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       eventserv.supprimerEvent(e);
        table.setItems(eventserv.afficherEvent());
    }
    
    
     @FXML
    private void SingleEventAction(ActionEvent event) throws IOException {
        
        System.out.println("You clicked Single_event button!");

       e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       myVariable=e;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Single_Event.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }

      @FXML
    private void Consulter_commentAction(ActionEvent event) throws IOException {
        e = new Event () ;
       e=table.getSelectionModel().getSelectedItem();
       myVariable_comment=e;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Comment.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        
    }

   
     @FXML
    private void confirmerAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked confirmer button!");

      e = new Event () ;
      e=table.getSelectionModel().getSelectedItem();
       eventserv.confirmerEvent(e);
      System.out.println("l'event"+e.getTitre()+"a ete confirmer");

        table.setItems(eventserv.afficherEvent());
    }
   
    @FXML
    private void handleInputSearchAction(KeyEvent event) throws IOException {
        
    }

    @FXML
    private void ExportToExcel(ActionEvent event) throws FileNotFoundException, IOException {
        
         DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        
        if(selectedDirectory == null){
            Alert null_alert = new Alert(Alert.AlertType.ERROR);
            null_alert.setTitle(null);
            null_alert.setHeaderText(null);
            null_alert.setContentText("Aucun dossier séléctionné!");
            null_alert.showAndWait();
        }
        else{
            System.out.println(selectedDirectory.getAbsolutePath());
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");

            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

            for (int j = 0; j < table.getColumns().size(); j++) {
                row.createCell(j).setCellValue(table.getColumns().get(j).getText());
            }

            for (int i = 0; i < table.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < table.getColumns().size(); j++) {
                    if(table.getColumns().get(j).getCellData(i) != null) { 
                        row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString()); 
                    }
                    else {
                        row.createCell(j).setCellValue("");
                    }   
                }
            }

            FileOutputStream fileOut = new FileOutputStream(selectedDirectory.getAbsolutePath()+"\\Les events.xls");
            workbook.write(fileOut);
            fileOut.close();


            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("EXPORTÉ!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le tableau a été exporté en Excel avec succès!");
            Confirmation_Alert.show();
        }       
    }

  
    @FXML
    private void ExportToPDF(ActionEvent event) throws FileNotFoundException, IOException, SQLException, DocumentException { 
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        
        if(selectedDirectory == null){
            Alert null_alert = new Alert(Alert.AlertType.ERROR);
            null_alert.setTitle(null);
            null_alert.setHeaderText(null);
            null_alert.setContentText("Aucun dossier séléctionné!");
            null_alert.showAndWait();
        }
        else{
            System.out.println(selectedDirectory.getAbsolutePath());
            String req = "SELECT * FROM event";
            PreparedStatement st;
            ResultSet rs;

            st = cn.prepareStatement(req);
          //  st.setInt(1,10);
            rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNb = rsmd.getColumnCount();
            PdfPTable NamesRow = new PdfPTable(3);
            Document d = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(d, new FileOutputStream(selectedDirectory.getAbsolutePath()+"\\Event.pdf"));

            d.open();

            d.add(new Paragraph("Les events:\n\n\n\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100,70,50});
            NamesRow.addCell("Titre");
            NamesRow.addCell("description");
            NamesRow.addCell("Date");
           


            d.add(NamesRow);
            while(rs.next()){
                PdfPTable pt = new PdfPTable(3);
                pt.setWidthPercentage(100);
                pt.setTotalWidth(new float[]{100,70,50});

                pt.addCell(""+ rs.getString(3));
                pt.addCell(""+ rs.getString(4));
                pt.addCell(""+ rs.getString(5));
                d.add(pt);
            }
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("EXPORTÉ!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le tableau a été exporté en PDF avec succès!");
            Confirmation_Alert.show();

            d.close();
            }
        
    }
    
    @FXML
    private void search_event(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/Event_map.fxml"));    
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }
    
     @FXML
 
private void refresh_action(ActionEvent event) throws SQLException {
    System.out.println("You clicked refresh button!");
      table.setItems(eventserv.afficherEvent());
    }
   
        @FXML
private void edit_titre(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
          Event e = table.getSelectionModel().getSelectedItem(); 
        e.setTitre(event.getNewValue());
        eventserv.modifier_Titre(e);
        table.setItems(eventserv.afficherEvent());
    }
   
        @FXML
private void edit_Desc(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
          Event e = table.getSelectionModel().getSelectedItem(); 
        e.setDescription(event.getNewValue());
        eventserv.modifier_Descr(e);
        table.setItems(eventserv.afficherEvent());
    }
        @FXML

    private void edit_lieu(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
         Event e = table.getSelectionModel().getSelectedItem(); 
        e.setLieu(event.getNewValue());
        eventserv.modifier_Lieu(e);
        table.setItems(eventserv.afficherEvent());
    }
        @FXML

    private void edit_nb_part(TableColumn.CellEditEvent<Event, String> event) throws SQLException {
         Event e = table.getSelectionModel().getSelectedItem(); 
        e.setLieu(event.getNewValue());
        eventserv.modifier_nb(e);
        table.setItems(eventserv.afficherEvent());
    }
    @FXML
    private void front_events_action(ActionEvent event) throws IOException {
        System.out.println("aaaaaaaaaaa");
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/testEvent.fxml"));  

            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
                             System.out.println("bbbbbbbbbbbbbb");

    }

   
    @FXML
    private void edit_date(TableColumn.CellEditEvent event) {
    }


    

   public void sendsms(String receiver, String msg) {
        try {
           
            String apiKey = "apikey=6yYU9e0AMWg-uvL29XnMKHOYCYzZ0w95pyQAEu80ot";
            String message = "&message=" + msg;
            String sender = "&sender=";
            String numbers = "&numbers=+216" + receiver;
           // User u = new User() ; 
           // u = LoginController.u ; 
           // System.out.println(u.getNumTel()) ; 
           // String numbers = "&numbers=+216" +u.getNumTel();
        //  String msg="Reservation confirmé pour la date"+reserv.getDateSortie()+"Merci de récuperer le materiel demandé";
         // sendsms(numero,msg);

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                //stringBuffer.append(line);
                JOptionPane.showMessageDialog(null, "message" + line);
                
            }
            rd.close();

            //return stringBuffer.toString();
        } catch (Exception e) {
            //System.out.println("Error SMS "+e)
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    @FXML
    private void SendsmsAction(ActionEvent event) {
        
          String numero="24824798";
         // String numero=numTel.getText();
          String msg="ahla ";
          sendsms(numero,msg);
          System.out.println("tebaath ci bon");
            

    }


   
  
    
    }



