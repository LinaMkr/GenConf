package fr.uga.iut2.genconf.controleur;

import fr.uga.iut2.genconf.modele.GenConf;
import fr.uga.iut2.genconf.vue.GUI;
import fr.uga.iut2.genconf.vue.IHM;


public class Controleur {

    private final GenConf genconf;
    private final IHM ihm;

    public Controleur(GenConf genconf) {
        this.genconf = genconf;

        // choisir la classe CLI ou GUI
//        this.ihm = new CLI(this);
        this.ihm = new GUI(this);
    }

    public void demarrer() {
        this.ihm.demarrerInteraction();
    }

    public void saisirUtilisateur() {
        this.ihm.saisirUtilisateur();
    }

    public void creerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenConf.",
                    false
            );
        }
    }

    public void saisirConference() {
        this.ihm.saisirNouvelleConference(this.genconf.getConferences().keySet());
        
    }

    public void creerConference(IHM.InfosNouvelleConference infos) {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genconf.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genconf.nouvelleConference(
                infos.nom,
                infos.dateDebut,
                infos.dateFin,
                infos.admin.email
        );
        this.ihm.informerUtilisateur(
                "Nouvelle conférence : " + infos.nom + ", administrée par " + infos.admin.email,
                true
        );
    }
    
    public void saisirSession() {
        this.ihm.saisirNouvelleSession();
    }
    
    public void creerSession(IHM.InfosNouvelleSession infos){
        boolean nouvelleSession = this.genconf.nouvelleSesssion(
                infos.nomConf,
                infos.titre,
                infos.nomSalle,
                infos.heureDebut,
                infos.heureFin
        );
        
        if(nouvelleSession){
            this.ihm.informerUtilisateur(
                    "Nouvelle Session : " + infos.titre + ", dans la salle : " + infos.nomSalle + "  de " + infos.heureDebut + " à " + infos.heureFin 
                    , true
            );
        }else{
            this.ihm.informerUtilisateur("La session " + infos.titre + " existe déjà ", false);
        }
        
    }
    
    public void saisirCommunication() {
        this.ihm.saisirNouvelleCommunication();
    }
    
    
    public void creerCommunication(IHM.InfosNouvelleCommunication infos){
       boolean nouvelleComm = this.genconf.nouvelleCommunication(
               infos.nomSess, 
               infos.titreComm,
               infos.emailC,
               infos.emailO
       );
       if(nouvelleComm){
           this.ihm.informerUtilisateur("Nouvelle Communication : " + infos.titreComm + " dirigé par " + infos.emailC + " appartenant à la session " + infos.nomSess, true);
       }else{
           this.ihm.informerUtilisateur("Le titre de la communication existe deja ou le correspondant ou l'orateur n'existent pas", false);
       }
    }
    
    public void ajouterOrateur(IHM.InfosNouveauOrateur infos){
        boolean nouvOrateur = this.genconf.nouveauOrateur(infos.titreComm,infos.emailO);
        //nouvOrateur = utilisateur existe mais orateur existe pas déjà
        if(nouvOrateur){
            this.ihm.informerUtilisateur(infos.emailO + " a été ajouté", true);
        }else{
            this.ihm.informerUtilisateur(infos.emailO + " n'existe pas ou est déjà orateur", false);
        }
    }
    
    
    //-------------------------------------------------------------------------
    
    
    public void saisieDonnerDroit() {
        this.ihm.donnerDroitAdministrateur();
    }
    
    public void donnerDroit(IHM.InfosDonnerDroit infos) {
        boolean estUtilisateur = this.genconf.donnerDroitAdmin(
                infos.nomConf,
                infos.email
        );
        if (estUtilisateur) {
            this.ihm.informerUtilisateur("L'utilisa·teur/trice : <" + infos.email + "> a les droits d'administration de la conférencce " + infos.nomConf + ".",
                    true
            );
        }else {
            this.ihm.informerUtilisateur("L'utilisa·teur/trice : <" + infos.email + "> n'existe pas ou est déjà administrateur.",
                    false
            );
        }
        
    }
    
    //--------------------pour les vues d'affichage, pas besoin de faire de feedback donc on laisse vide--------------------
    public void saisieAfficherUtilisateurs(){
        this.ihm.afficherUtilisateurs();
    }
    public void afficheUsers(IHM.InfosAfficherUtilisateurs infos){}
    
    
    public void saisieSelectionnerConference(){
        this.ihm.selectionnerConference();
    }
    public void selectionnerConf(IHM.InfosSelectionnerConference infos){}
    
    
    public void saisieConsulterInfosConference(){
        this.ihm.consulterInfosConference();
    }
    public void consulterInfosConference(IHM.InfosConsulterInfosConference infos){}
    //-------------------------------------------------------------------------------------------------------------------------
    
    public void saisieSupprimerUtilisateur(){
        this.ihm.supprimerUtilisateur();
    }
    public void supprimerUser(IHM.InfosSupprimerUtilisateur infos){
        boolean estUtilisateur = this.genconf.supprimerUtilis(
                infos.email
        );
        if (estUtilisateur){
            this.ihm.informerUtilisateur("L'utilisateur : <" + infos.email + "> a été supprimé.", 
                    true
            );
        }else{
            this.ihm.informerUtilisateur("L'utilisa·teur/trice : <" + infos.email + "> n'existe pas ou est administrateur d'une conférence.",
                    false
            );
        }
    }
    
    
    public void saisieSupprimerConférence(){
        this.ihm.supprimerConference();
    }
    
    public void supprimerConf(IHM.InfosSupprimerConference infos) {
        this.genconf.supprimerConfer(infos.nomConf);
        this.ihm.informerUtilisateur("La conférence : <" + infos.nomConf + "> a été supprimé.", true); 
    }
    
    public GenConf getGenConf(){
        return this.genconf;
    }
    
}
