/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import services.EventServices;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import entities.Event;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.log4j.BasicConfigurator;
import utils.OpenStreetMapUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author kouss
 */
public class Event_mapController implements Initializable,MapComponentInitializedListener {

    @FXML
    private ComboBox date_list_CB;
     EventServices eventserv ;

        @FXML
    private GoogleMapView mapView;
    private GoogleMap map ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        eventserv = new EventServices();
        date_list_CB.setItems(FXCollections.observableArrayList(eventserv.afficherListeDateEventSansRedondance()));
        mapView.addMapInializedListener(this);
    }  
    
          @Override
        public void mapInitialized() {
            BasicConfigurator.configure();
            Map<String, Double> TunisiaCoords;
            TunisiaCoords = OpenStreetMapUtils.getInstance().getCoordinates("Tunisia");
            System.out.println("Latitude de la localisation Tunisia: " + TunisiaCoords.get("lat"));
            System.out.println("Longitude de la localisation Tunisia: " + TunisiaCoords.get("lon"));
            //Get Tunisia coords and center it on the map
            
          
            
//            LatLong joeSmithLocation = new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon"));
//            LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//            LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//            LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//            LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);


            //Set the initial properties of the map fi tounes.
            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon")))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(true)
                    .zoomControl(false)
                    .zoom(8);
            
            map = mapView.createMap(mapOptions);
            System.out.println("aaaaaaaaaaaaaaaa");
           
            //Add markers to the map
//            MarkerOptions markerOptions1 = new MarkerOptions();
//            markerOptions1.position(joeSmithLocation);

//            MarkerOptions markerOptions2 = new MarkerOptions();
//            markerOptions2.position(joshAndersonLocation);
//
//            MarkerOptions markerOptions3 = new MarkerOptions();
//            markerOptions3.position(bobUnderwoodLocation);
//
//            MarkerOptions markerOptions4 = new MarkerOptions();
//            markerOptions4.position(tomChoiceLocation);
//
//            MarkerOptions markerOptions5 = new MarkerOptions();
//            markerOptions5.position(fredWilkieLocation);
//
//            Marker joeSmithMarker = new Marker(markerOptions1);
//            Marker joshAndersonMarker = new Marker(markerOptions2);
//            Marker bobUnderwoodMarker = new Marker(markerOptions3);
//            Marker tomChoiceMarker= new Marker(markerOptions4);
//            Marker fredWilkieMarker = new Marker(markerOptions5);

//            map.addMarker( joeSmithMarker );
//            map.addMarker( joshAndersonMarker );
//            map.addMarker( bobUnderwoodMarker );
//            map.addMarker( tomChoiceMarker );
//            map.addMarker( fredWilkieMarker );

//            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//            infoWindowOptions.content("<h2>Fred Wilkie</h2>"
//                                    + "Current Location: Safeway<br>"
//                                    + "ETA: 45 minutes" );
//
//            InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//            fredWilkeInfoWindow.open(map, fredWilkieMarker);
            
         
    }   

    @FXML
    private void afficher_events_parDate(ActionEvent event) throws SQLException {
        
        ObservableList<Event> listeEvents = eventserv.afficherListeEvent_ParDate(Date.valueOf(date_list_CB.getValue().toString()));
        System.out.println("Date séléctionnée: " + Date.valueOf(date_list_CB.getValue().toString()));
        
        
        BasicConfigurator.configure();
        Map<String, Double> TunisiaCoords;
        TunisiaCoords = OpenStreetMapUtils.getInstance().getCoordinates("Tunisia");
        System.out.println("Latitude de la localisation Tunisia: " + TunisiaCoords.get("lat"));
        System.out.println("Longitude de la localisation Tunisia: " + TunisiaCoords.get("lon"));
        //Get Tunisia coords and center it on the map



//            LatLong joeSmithLocation = new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon"));
//            LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
//            LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
//            LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
//            LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);


            //Set the initial properties of the map.
            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(TunisiaCoords.get("lat"), TunisiaCoords.get("lon")))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(false)
                    .rotateControl(false)
                    .scaleControl(false)
                    .streetViewControl(true)
                    .zoomControl(false)
                    .zoom(8);
            
            map = mapView.createMap(mapOptions);
            System.out.println("aaaaaaaaaaaaaaaa");
           
              //Add markers to the map
//            MarkerOptions markerOptions1 = new MarkerOptions();
//            markerOptions1.position(joeSmithLocation);

//            MarkerOptions markerOptions2 = new MarkerOptions();
//            markerOptions2.position(joshAndersonLocation);
//
//            MarkerOptions markerOptions3 = new MarkerOptions();
//            markerOptions3.position(bobUnderwoodLocation);
//
//            MarkerOptions markerOptions4 = new MarkerOptions();
//            markerOptions4.position(tomChoiceLocation);
//
//            MarkerOptions markerOptions5 = new MarkerOptions();
//            markerOptions5.position(fredWilkieLocation);
//
//            Marker joeSmithMarker = new Marker(markerOptions1);
//            Marker joshAndersonMarker = new Marker(markerOptions2);
//            Marker bobUnderwoodMarker = new Marker(markerOptions3);
//            Marker tomChoiceMarker= new Marker(markerOptions4);
//            Marker fredWilkieMarker = new Marker(markerOptions5);

//            map.addMarker( joeSmithMarker );
//            map.addMarker( joshAndersonMarker );
//            map.addMarker( bobUnderwoodMarker );
//            map.addMarker( tomChoiceMarker );
//            map.addMarker( fredWilkieMarker );

//            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
//            infoWindowOptions.content("<h2>Fred Wilkie</h2>"
//                                    + "Current Location: Safeway<br>"
//                                    + "ETA: 45 minutes" );
//
//            InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
//            fredWilkeInfoWindow.open(map, fredWilkieMarker);
            
        for(Event ev : listeEvents){
            BasicConfigurator.configure();
            Map<String, Double> ExpertCoords;
            ExpertCoords = OpenStreetMapUtils.getInstance().getCoordinates(ev.getLieu());
            LatLong LatEvent = new LatLong(ExpertCoords.get("lat"), ExpertCoords.get("lon"));
            //System.out.println("Latitude de la localisation "+ disp.getLocalisation()+": " + ExpertCoords.get("lat"));
            //System.out.println("Longitude de la localisation: " + disp.getLocalisation()+": " +ExpertCoords.get("lon"));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(LatEvent);
            Marker LatEventMarker = new Marker(markerOptions);
            map.addMarker(LatEventMarker);
            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
            infoWindowOptions.content("<h3>"+ ev.getTitre()+" " +ev.getDescription()+ "</h3>"
                                    + "Disponible à: "+ ev.getLieu()+ "<br>"
                                    + "Capacité: " + ev.getNb_participants() );

            InfoWindow eventInfo = new InfoWindow(infoWindowOptions);
            eventInfo.open(map, LatEventMarker);
            
        }
    
    }
    
}
