package fr.uga.iut2.genconf.vue;

import fr.uga.iut2.genconf.controleur.Controleur;
import fr.uga.iut2.genconf.modele.Utilisateur;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class GUI extends IHM {
    private final Controleur controleur;
    private final CountDownLatch eolBarrier;

    private final VuePrincipale vuePrincipale;
    private final VueCreationConference vueCreationConf;
    private final VueCreationUtilisateur vueCreationUser;
    private final VueEtat vueEtat;
    private final VueDonnerDroitAdministrateur VueDonnerDroitAdmin;
    private final VueCreationSession vueCreationSess;
    private final VueCreationCommunication vueCreationComm;
    private final VueAfficherUtilisateurs vueAfficheUsers;
    private final VueSelectionnerConference vueSelectionnerConf;
    private VueConsulterInfosConference vueConsulterInfosConf;
    private final VueSupprimerUtilisateur vueSupprimerUser;
    private final VueSupprimerConference vueSupprimerConf;

    // identifiants uniques pour les vues composant la vue principale
    private static final String VUE_ETAT = "etat";
    private static final String VUE_CREATION_CONF = "creation_conf";
    private static final String VUE_CREATION_USER = "creation_user";
    private static final String VUE_DONNER_DROIT_ADMIN = "donner_droit_admin";
    private static final String VUE_CREATION_COMM = "creation_comm";
    private static final String VUE_CREATION_SESS = "creation_sess";
    private static final String VUE_AFFICHER_USER = "afficher_user";
    private static final String VUE_SELECTIONNER_CONF = "selectionner_conf";
    private static final String VUE_CONSULTER_INFOS_CONF = "consulter_infos_conf";
    private static final String VUE_SUPPRIMER_USER = "supprimer_user";
    private static final String VUE_SUPPRIMER_CONF = "supprimer_conf";


    public GUI(Controleur controleur) {
        this.controleur = controleur;

        // initialisé à 1 pour attendre l'évènement correspondant à la fin de vie de GUI
        this.eolBarrier = new CountDownLatch(1);

        // création de l'interface
        this.vueEtat = new VueEtat(this);
        this.vueCreationConf = new VueCreationConference(this);
        this.vueCreationUser = new VueCreationUtilisateur(this);
        this.VueDonnerDroitAdmin = new VueDonnerDroitAdministrateur(this, controleur.getGenConf().getConferences());
        this.vueCreationSess = new VueCreationSession(this, controleur.getGenConf().getConferences());
        this.vueCreationComm = new VueCreationCommunication(this, controleur.getGenConf().getSessions(), controleur.getGenConf().getOrateurs());
        this.vueAfficheUsers = new VueAfficherUtilisateurs(this, controleur.getGenConf().getAdministrateurs() , controleur.getGenConf().getUtilisateurs());
        this.vueSelectionnerConf = new VueSelectionnerConference(this, controleur.getGenConf().getConferences());
        this.vueSupprimerUser = new VueSupprimerUtilisateur(this, controleur.getGenConf().getUtilisateurs(), controleur.getGenConf().getConferences(), controleur.getGenConf().getAdministrateurs());
        this.vueSupprimerConf = new VueSupprimerConference(this, controleur.getGenConf().getConferences());
        
        this.vuePrincipale = new VuePrincipale(this);
        this.vuePrincipale.ajouterVue(this.vueEtat, GUI.VUE_ETAT);
        this.vuePrincipale.ajouterVue(this.vueCreationConf, GUI.VUE_CREATION_CONF);
        this.vuePrincipale.ajouterVue(this.vueCreationUser, GUI.VUE_CREATION_USER);
        this.vuePrincipale.ajouterVue(this.vueCreationSess, GUI.VUE_CREATION_SESS);
        this.vuePrincipale.ajouterVue(this.vueCreationComm, GUI.VUE_CREATION_COMM);
        this.vuePrincipale.ajouterVue(this.VueDonnerDroitAdmin, GUI.VUE_DONNER_DROIT_ADMIN);
        this.vuePrincipale.ajouterVue(this.vueAfficheUsers, GUI.VUE_AFFICHER_USER);
        this.vuePrincipale.ajouterVue(this.vueSelectionnerConf, GUI.VUE_SELECTIONNER_CONF);
        this.vuePrincipale.ajouterVue(this.vueSupprimerUser, GUI.VUE_SUPPRIMER_USER);
        this.vuePrincipale.ajouterVue(this.vueSupprimerConf, GUI.VUE_SUPPRIMER_CONF);
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
    }

//-----  Éléments du dialogue  -------------------------------------------------

    protected void actionCreerConference() {
        this.controleur.saisirConference();
    }

    protected void actionCreerUtilisateur() {
        this.controleur.saisirUtilisateur();
    }
    
    protected void actionCreerSession() {
        this.controleur.saisirSession();
    }
    
    protected void actionCreerCommunication() {
        this.controleur.saisirCommunication();
    }
    
    protected void actionDonnerDroitAdmin() {
        this.controleur.saisieDonnerDroit();
    }
    
    protected void actionAfficherUtilisateurs(){
        this.controleur.saisieAfficherUtilisateurs();
    }
    
    protected void actionSelectionnerConference(){
        this.controleur.saisieSelectionnerConference();
    }
    
    protected void actionConsulterInfosConference(String nomConf){
        this.vueConsulterInfosConf = new VueConsulterInfosConference(this, nomConf, controleur.getGenConf().getConferences(), controleur.getGenConf().getSessions(), controleur.getGenConf().getCommunications(), controleur.getGenConf().getOrateurs());
        this.vuePrincipale.ajouterVue(this.vueConsulterInfosConf, GUI.VUE_CONSULTER_INFOS_CONF);
        this.vuePrincipale.afficherVue(GUI.VUE_CONSULTER_INFOS_CONF);
        this.controleur.saisieConsulterInfosConference();
    }
    
    protected void actionSupprimerUtilisateur(){
        this.controleur.saisieSupprimerUtilisateur();
    }
    
    protected void actionSupprimerConference(){
        this.controleur.saisieSupprimerConférence();
    }
    
    protected void actionTerminer() {
        this.vuePrincipale.fermer();

        // On notifie la fin de vie de GUI pour rendre la main au contrôleur
        this.eolBarrier.countDown();
    }

    protected void creerUtilisateur(Optional<InfosUtilisateur> nouvelUtilisateur) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nouvelUtilisateur.ifPresentOrElse(
                infos -> this.controleur.creerUtilisateur(infos),
                () -> this.vueEtat.setEtat("")
        );
    }

    protected void creerConference(Optional<InfosNouvelleConference> nlleConf) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nlleConf.ifPresentOrElse(
                infos -> this.controleur.creerConference(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void creerSession(Optional<InfosNouvelleSession> nlleSess){
        this.vuePrincipale.afficherVue(VUE_ETAT);
        nlleSess.ifPresentOrElse(
                infos -> this.controleur.creerSession(infos),
                () -> this.vueEtat.setEtat(""));
    }
    
    
    protected void creerCommunication(Optional<IHM.InfosNouvelleCommunication> nlleComm) {
       this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
       nlleComm.ifPresentOrElse(
             infos ->  this.controleur.creerCommunication(infos),
               () -> this.vueEtat.setEtat("")
       );   
    }
    
    protected void ajouterOrateur(Optional<IHM.InfosNouveauOrateur> nvOrateur) {
       this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
       nvOrateur.ifPresentOrElse(
             infos ->  this.controleur.ajouterOrateur(infos),
               () -> this.vueEtat.setEtat("")
       );   
    }    
    
    protected void selectConference(Optional<InfosSelectionnerConference> confSelected) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        confSelected.ifPresentOrElse(
                infos -> this.controleur.selectionnerConf(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void donnerDroit(Optional<InfosDonnerDroit> nvDroit) {
        this.vuePrincipale.afficherVue(GUI.VUE_ETAT);
        nvDroit.ifPresentOrElse(
                infos -> this.controleur.donnerDroit(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void supprUtilisateur(Optional<InfosSupprimerUtilisateur> delUtilisateur){
        this.vuePrincipale.afficherVue(GUI.VUE_SUPPRIMER_USER);
        delUtilisateur.ifPresentOrElse(
                infos -> this.controleur.supprimerUser(infos),
                () -> this.vueEtat.setEtat("")
        );
    }
    
    protected void supprConference(Optional<InfosSupprimerConference> delUtilisateur){
        this.vuePrincipale.afficherVue(GUI.VUE_SUPPRIMER_CONF);
        delUtilisateur.ifPresentOrElse(
                infos -> this.controleur.supprimerConf(infos),
                () -> this.vueEtat.setEtat("")
        );
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void demarrerInteraction() {
        this.vuePrincipale.afficher();

        // On attend que GUI ait fini avant de rendre la main au contrôleur
        // (c'est à dire au moment de l'appel de `actionTerminer`)
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void informerUtilisateur(final String msg, final boolean succes) {
        this.vueEtat.setEtat(msg + (succes ? " [SUCCES]" : " [ÉCHEC]"));
    }

    @Override
    public void saisirUtilisateur() {
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_USER);
    }

    @Override
    public void saisirNouvelleConference(final Set<String> nomsExistants) {
        this.vueCreationConf.setNomsExistants(nomsExistants);
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_CONF);
    }
    
    @Override
    public void saisirNouvelleSession() { 
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_SESS);
        //this.updateViews();
    }
        
    @Override
    public void saisirNouvelleCommunication() {
        this.vuePrincipale.afficherVue(GUI.VUE_CREATION_COMM);
        this.updateViews();
    }
    
    @Override
    public void donnerDroitAdministrateur() {
        this.vuePrincipale.afficherVue(GUI.VUE_DONNER_DROIT_ADMIN);
        this.updateViews();
    }
    
    @Override
    public void afficherUtilisateurs(){
        this.vuePrincipale.afficherVue(GUI.VUE_AFFICHER_USER);
        this.updateViews();
    }
    
    @Override
    public void selectionnerConference(){
        this.vuePrincipale.afficherVue(GUI.VUE_SELECTIONNER_CONF);
    }
    
    @Override
    public void consulterInfosConference(){
        this.vuePrincipale.afficherVue(GUI.VUE_CONSULTER_INFOS_CONF);
    }
    
    @Override
    public void supprimerUtilisateur(){
        this.vuePrincipale.afficherVue(GUI.VUE_SUPPRIMER_USER);
        this.updateViews();
    }
    
    @Override
    public void supprimerConference(){
        this.vuePrincipale.afficherVue(GUI.VUE_SUPPRIMER_CONF);
        this.updateViews();
    }
    
//-----  Mise à jour des vues  -------------------------------
    
    public void updateViews(){
        this.VueDonnerDroitAdmin.updateView(this, this.controleur.getGenConf().getConferences());
        this.vueAfficheUsers.updateView(this, this.controleur.getGenConf().getAdministrateurs(), this.controleur.getGenConf().getUtilisateurs());
        this.vueSupprimerConf.updateView(this, this.controleur.getGenConf().getConferences());
        this.vueCreationSess.updateView(this, this.controleur.getGenConf().getConferences());
        this.vueCreationComm.updateView(this, this.controleur.getGenConf().getSessions());
    }
    
    
}
