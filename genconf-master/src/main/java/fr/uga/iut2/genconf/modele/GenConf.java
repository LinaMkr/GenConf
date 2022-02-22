package fr.uga.iut2.genconf.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class GenConf implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    
    private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    private final Map<String, Conference> conferences;  // association qualifiée par le nom
    private final Map<String, Utilisateur> administrateurs; // association qualifiée par le nom de la conférence
    private final Map<String, Session> sessions; // association qualifiée par le nom de la conférence à laquel il appartient
    private final Map<String, Communication> communications; // association qualifiée par le nom de la sessions à laquel il appartient
    private final Map<String, Utilisateur> orateurs; //association par 
    
    public GenConf() {
        this.utilisateurs = new HashMap<>();
        this.conferences = new HashMap<>();
        this.administrateurs = new HashMap<>();
        this.sessions = new HashMap<>();
        this.communications = new HashMap<>();
        this.orateurs = new HashMap<>();
    }

    public Map<String, Utilisateur> getUtilisateurs(){
        return this.utilisateurs;
    }
    public Map<String, Conference> getConferences() {
        return this.conferences;
    }
    public Map<String, Utilisateur> getAdministrateurs(){
        return this.administrateurs;
    }
    public Map<String, Session> getSessions(){
        return this.sessions;
    }
    public Map<String, Communication> getCommunications(){
        return this.communications;
    }
        
    public Map<String,Utilisateur> getOrateurs(){
        return this.orateurs;
    }
    
    public boolean ajouteUtilisateur(String email, String nom, String prenom) {
        if (this.utilisateurs.containsKey(email)) {
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom));
            return true;
        }
    }

    public void nouvelleConference(String nom, LocalDate dateDebut, LocalDate dateFin, String adminEmail) {
        assert !this.conferences.containsKey(nom);
        assert this.utilisateurs.containsKey(adminEmail);
        Utilisateur admin = this.utilisateurs.get(adminEmail);
        Conference conf = Conference.initialiseConference(this, nom, dateDebut, dateFin, admin);
        this.conferences.put(nom, conf);
        
        this.administrateurs.put(nom, admin);
        
    }
    
    public boolean nouvelleSesssion(String nomConf, String titre, String nomSalle, LocalTime heureDebut, LocalTime heureFin){
        if(this.sessions.containsKey(nomConf)){
            return false;
        }else{
            this.sessions.put(titre, new Session(nomConf,nomSalle,heureDebut,heureFin));
            return true;
        }
    }
    
    //vérifie si la communication existe pas, le correspondant existe et l'orateur existe
    public boolean nouvelleCommunication(String nomSess, String titreComm, String emailC, String emailO){ 
        if(!(this.communications.containsKey(nomSess)) && this.utilisateurs.containsKey(emailC) && this.utilisateurs.containsKey(emailO)){ //verifier si le titre exsite déjà et su l'email du correspondant existe
            //on ajoute la comm titreComm à la liste des communications avec pour valeurs titreComm, emailC et emailO
            this.communications.put(nomSess, new Communication(titreComm,emailC,emailO));
            //on ajoute l'orateur emailO dans la liste des orateurs
            String nomO = utilisateurs.get(emailO).getNom();
            String prenomO = utilisateurs.get(emailO).getPrenom();
            this.orateurs.put(titreComm, new Utilisateur(emailO, nomO, prenomO));
            return true;
        }else{
            return false;
        }
    }
    
    //verifie si l'utilisateur existe et qu'il n'est pas déjà orateur et que la session n'existe pas)
    public boolean nouveauOrateur(String titreComm, String emailO){
        if (this.utilisateurs.containsKey(emailO) && !(this.orateurs.containsKey(emailO)) && !(this.sessions.containsKey(titreComm))){
            String nomO = utilisateurs.get(emailO).getNom();
            String prenomO = utilisateurs.get(emailO).getPrenom();
            this.orateurs.put(titreComm, new Utilisateur(emailO, nomO, prenomO));
            return true;
        }else{
            return false;
        }
    }
    
    public boolean donnerDroitAdmin(String nomConf, String email){
        if (this.administrateurs.get(nomConf).getEmail().equals(email)){ // Si l'utilisateur est deja admin pour cette conference => return false
            return false;
        }else if (this.conferences.containsKey(nomConf) && this.utilisateurs.containsKey(email)){ //si la conférence et l'utilisateur existent
            String nomU = utilisateurs.get(email).getNom();
            String prenomU = utilisateurs.get(email).getPrenom();
            this.administrateurs.remove(nomConf);
            this.administrateurs.put(nomConf, new Utilisateur(email, nomU, prenomU));
            return true;
        }else{
            return false;
        }
    }
    
    public boolean afficherUtilis(String email, String nom, String prenom){
        return this.utilisateurs.containsKey(email);
    }

    public boolean supprimerUtilis(String email){
        
        if (this.utilisateurs.containsKey(email)){
            if (!(this.administrateurs.containsValue(this.utilisateurs.get(email)))){ //si c'est pas un admin
                this.utilisateurs.remove(email);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public void supprimerConfer(String nomConf){
        String emailU = administrateurs.get(nomConf).getEmail();
        String nomU = administrateurs.get(nomConf).getNom();
        String prenomU = administrateurs.get(nomConf).getPrenom();
        this.administrateurs.remove(nomConf);
        this.conferences.remove(nomConf);
        this.utilisateurs.put(emailU, new Utilisateur(emailU, nomU, prenomU));
    }
}