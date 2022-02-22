package fr.uga.iut2.genconf.vue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


public abstract class IHM {
    /**
     * Classe conteneur pour les informations saisies à propos d'un
     * {@link fr.uga.iut2.genconf.modele.Utilisateur}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosUtilisateur {
        public final String email;
        public final String nom;
        public final String prenom;

        public InfosUtilisateur(final String email, final String nom, final String prenom) {
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
        }
    }
    
    public static class InfosDonnerDroit {
        public final String nomConf;
        public final String email;;
    
    
        public InfosDonnerDroit (final String nomConf, final String email){
            this.nomConf = nomConf;
            this.email = email;
        }
    }
    
    public static class InfosAfficherUtilisateurs{
        public final String email;
        public final String nom;
        public final String prenom;
        
        public InfosAfficherUtilisateurs(final String email, final String nom, final String prenom) {
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
        }
    }
    
    public static class InfosSelectionnerConference{
        public final String nomConf;
        
        public InfosSelectionnerConference(final String nomConf){
            this.nomConf = nomConf;
        }
    }
    
    public static class InfosConsulterInfosConference{
        
        public final String nomConf;
        public final LocalDate dateDebut, dateFin;
        public final InfosNouvelleSession session;//infos d'une session
        public final InfosNouvelleCommunication communication;//infos d'une communication
        public final InfosNouveauOrateur orateur;//infos d'un orateur
        
            public InfosConsulterInfosConference(final String nomConf, final LocalDate dateDebut, final LocalDate dateFin, final InfosNouvelleSession session, final InfosNouvelleCommunication communication, final InfosNouveauOrateur orateur){
                this.nomConf = nomConf;
                this.dateDebut = dateDebut;
                this.dateFin = dateFin;                
                this.session = session;
                this.communication = communication;
                this.orateur = orateur;
            }
    }
    
    public static class InfosSupprimerUtilisateur{
        public final String email;
        
        public InfosSupprimerUtilisateur(final String email) {
            this.email = email;
        }
    }
    
    public static class InfosSupprimerConference{
        public final String nomConf;
        
        public InfosSupprimerConference(final String nomConf) {
            this.nomConf = nomConf;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour une nouvelle
     * {@link fr.uga.iut2.genconf.modele.Conference}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosNouvelleConference {
        public final String nom;
        public final LocalDate dateDebut;
        public final LocalDate dateFin;
        public final InfosUtilisateur admin;

        public InfosNouvelleConference(final String nom, final LocalDate dateDebut, final LocalDate dateFin, final InfosUtilisateur admin) {
            assert !dateDebut.isAfter(dateFin);
            this.nom = nom;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.admin = admin;
        }
    }
    
    public static class InfosNouvelleSession {
        public final String nomConf;
        public final String titre;
        public final String nomSalle;
        public final LocalTime heureDebut;
        public final LocalTime heureFin;
    
        public InfosNouvelleSession(final String nomConf, final String titre, final String nomSalle, final LocalTime heureDebut, final LocalTime heureFin){
            assert !heureDebut.isAfter(heureFin);
            this.nomConf = nomConf;
            this.nomSalle = nomSalle;
            this.heureDebut = heureDebut;
            this.heureFin = heureFin;
            this.titre = titre;
        }
    }
    
    
    public static class InfosNouvelleCommunication{
        public final String nomSess;
        public final String titreComm;
        public final String emailC;
        public final String emailO;
        
        public InfosNouvelleCommunication(final String nomSess,final String titreComm, final String emailC, final String emailO){
            this.nomSess = nomSess;
            this.titreComm = titreComm;
            this.emailC =emailC;
            this.emailO = emailO;
        } 
    }
    
    public static class InfosNouveauOrateur{
        public final String titreComm;
        public final String emailO;
        
        public InfosNouveauOrateur (final String titreComm, final String emailO){
            this.titreComm = titreComm;
            this.emailO = emailO;
        }
    }
    
    
    
    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void demarrerInteraction();

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);

    /**
     * Récupère les informations à propos d'un
     * {@link fr.uga.iut2.genconf.modele.Utilisateur}.
     *
     */
    public abstract void saisirUtilisateur();

    /**
     * Récupère les informations nécessaires à la création d'une nouvelle
     * {@link fr.uga.iut2.genconf.modele.Conference}.
     *
     * @param nomsExistants L'ensemble des noms de conférences qui ne sont plus
     *     disponibles.
     *
     */
    public abstract void saisirNouvelleConference(final Set<String> nomsExistants);
    
    public abstract void saisirNouvelleSession();
    
    public abstract void saisirNouvelleCommunication();
    
    public abstract void donnerDroitAdministrateur();
    
    public abstract void afficherUtilisateurs();
    
    public abstract void selectionnerConference();
    
    public abstract void consulterInfosConference();
    
    public abstract void supprimerUtilisateur();
    
    public abstract void supprimerConference();
}
