/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.iut2.genconf.vue;

import fr.uga.iut2.genconf.modele.Conference;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Asmaa
 */
public class VueCreationSession extends javax.swing.JPanel {
        private final GUI gui;
        private Set<String> titresExistSess;
        private boolean valide, validNom;
    /**
     * Creates new form VueCreationSessions
     */
    public VueCreationSession(GUI gui, Map<String, Conference> conferences) {
        this.gui = gui;
        this.titresExistSess = new HashSet<>();
        this.valide = false;
        this.validNom = false;
        
        // création de l'interface générée
        this.initComponents();
        
        updateView(gui,conferences);
        
        this.creer.setEnabled(false);
    
    }

        public void setTitresExistSess(final Set<String> titresExistSess) {
            assert titresExistSess != null;
            this.titresExistSess = titresExistSess;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        titre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nomSalle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel ms =
        new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);
        heureDebut = new javax.swing.JSpinner(ms);
        Date dat = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(dat, null, null, Calendar.HOUR_OF_DAY);
        heureFin = new javax.swing.JSpinner(sm);
        annuler = new javax.swing.JButton();
        creer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        nomConf = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("Création d'une session");

        jLabel2.setText("Titre :");

        titre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValidAction(evt);
            }
        });

        jLabel3.setText("Nom de la salle :");

        nomSalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValidAction(evt);
            }
        });

        jLabel4.setText("Heure de début :");

        jLabel5.setText("Heure de fin :");

        JSpinner.DateEditor ed = new JSpinner.DateEditor(heureDebut,"HH:mm");
        heureDebut.setEditor(ed);

        JSpinner.DateEditor de = new JSpinner.DateEditor(heureFin,"HH:mm");
        heureFin.setEditor(de);

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerBoutonActionPerformed(evt);
            }
        });

        creer.setText("Créer");
        creer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerBoutonActionPerformed(evt);
            }
        });

        jLabel6.setText("Sélectionner la conférence :");

        nomConf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(annuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomConf, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(heureDebut)
                                                .addComponent(heureFin, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(nomSalle, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 78, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomSalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heureDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heureFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annuler)
                    .addComponent(creer))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void annulerBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerBoutonActionPerformed
        this.gui.creerSession(Optional.empty());
    }//GEN-LAST:event_annulerBoutonActionPerformed

    private void creerBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerBoutonActionPerformed
        // TODO add your handling code here:
        Date Debut = (Date) this.heureDebut.getModel().getValue();
        
        LocalTime heureDebut = Instant.ofEpochMilli(Debut.getTime())
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalTime();
        Date Fin = (Date) this.heureFin.getModel().getValue();
        
        LocalTime heureFin = Instant.ofEpochMilli(Fin.getTime())
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalTime();
        //Infos sur la nouvelle session
        IHM.InfosNouvelleSession nlleSess = new IHM.InfosNouvelleSession(
            this.nomConf.getSelectedItem().toString(),
            this.titre.getText(),
            this.nomSalle.getText(),
            heureDebut,
            heureFin
        ); 
        
        
       this.gui.creerSession(Optional.of(nlleSess));
    }//GEN-LAST:event_creerBoutonActionPerformed

    private void ValidAction(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValidAction

        String nomS, titr;
        
        titr = this.titre.getText().trim();
        nomS = this.nomSalle.getText().trim();
        

        validNom = true
                 && (nomS != null) && (nomS.length() > 0)
                 && (titr != null) && (titr.length() > 0);

        this.valide = !titresExistSess.contains(titre.getText());
        this.creer.setEnabled(this.valide && this.validNom);
    }//GEN-LAST:event_ValidAction

    public final void updateView(GUI gui, Map<String, Conference> conferences){
        nomConf.removeAllItems();
        //on récupère la liste des conférences
        if(conferences.isEmpty()){
            this.nomConf.addItem("Aucune conférence");
            this.nomConf.setEnabled(false);
        }else{
            for (Map.Entry<String, Conference> conf : conferences.entrySet()) {
                this.nomConf.addItem(conf.getKey());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JButton creer;
    private javax.swing.JSpinner heureDebut;
    private javax.swing.JSpinner heureFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> nomConf;
    private javax.swing.JTextField nomSalle;
    private javax.swing.JTextField titre;
    // End of variables declaration//GEN-END:variables
}
