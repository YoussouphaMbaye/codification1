/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import metier.*;
import modele.*;
import org.apache.taglibs.standard.functions.Functions;
import static org.apache.taglibs.standard.functions.Functions.substring;

/**
 *
 * @author youssou
 */
 @MultipartConfig
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {
   
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    GestPavion gestpv;
    GestChambre gestCh;
    GestEtudie gestEtudie;
    GestOccupe goccupe;
    GestPloyer gployer;
    GestEtudiant gestetu;
    GestEmployer gestemp;
    GestChoisir gestchoix;
    GestClasse gestclasse;
    GestUfr gestUfr;
    GestDepartement gestDept;
    GestFormation gestForm;
    GestFaire gestFaire;
    GestPCaution gestPCaution;
    @Override
    public void init()
            throws ServletException {
        super.init();
        gestUfr = new GestUfr();
        gestpv = new GestPavion();
        gestCh = new GestChambre();
        gestEtudie = new GestEtudie();
        goccupe = new GestOccupe();
        gployer = new GestPloyer();
        gestetu = new GestEtudiant();
        gestemp = new GestEmployer();
        gestchoix = new GestChoisir();
        gestclasse = new GestClasse();
        gestDept = new GestDepartement();
        gestForm = new GestFormation();
        gestFaire = new GestFaire();
        gestPCaution = new GestPCaution();
    }
 
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        String action = null;
        action = request.getParameter("action");
        RequestDispatcher rd = null;
        PCautionForm pCautionf = new PCautionForm();
        EtudiantForm etudiantf = new EtudiantForm();
        ChoisirForm choisirf = new ChoisirForm();
        EmployerForm employerf = new EmployerForm();
        PavionForm pavionf = new PavionForm();
        EtudieForm etudief = new EtudieForm();
        ChambreForm chambref = new ChambreForm();
        ClasseForm classef = new ClasseForm();
        OccupeForm occupef = new OccupeForm();
        PloyerForm ployerf = new PloyerForm();
        DepartementForm deptF = new DepartementForm();
        FormationForm formationf = new FormationForm();
        FaireForm fairef = new FaireForm();
        UfrForm ufrf = new UfrForm();
        
        EnvoyerEmail envoyerEmail=new EnvoyerEmail();
        String entete = null;
        String message = null;
        if (action == null || action.equals("accueil")) {
            entete = "Accueil";
            request.setAttribute("entete", entete);
            rd = request.getRequestDispatcher("vue/accueil.jsp");
        } 
        else {
                long VINGT_QUATRE_HEURES = 1000 * 60 * 60 * 24;
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 00);
                calendar.set(Calendar.MINUTE, 00);
                calendar.set(Calendar.SECOND, 0);
                java.util.Date time =calendar.getTime();
                Timer timer = new Timer();
                timer.schedule(new Matask(), time, VINGT_QUATRE_HEURES);

            if (action.equals("infos")) {
                entete = "Infos";
                rd = request.getRequestDispatcher("vue/infos.jsp");
            }
            else  if (action.equals("contacter") || action.equals("envoyerLeMail")) {
                entete = "Contact";
                if(action.equals("envoyerLeMail")){
                    String mail=request.getParameter("email");
                    String sujet=request.getParameter("sujet");
                    String lemessage=request.getParameter("message");
                    String monEmail="codificationuadb@gmail.com";
                    String userName="Youssoupha Mbaye Cisse";
                    String password="uadb2017";
                    String messageEnv=lemessage+"\n"+"email: "+mail;
                    int i=envoyerEmail.envoyezMessage(monEmail, userName, password, "reception.codification@gmail.com", sujet, messageEnv);
                   if(i==1)
                       request.setAttribute("reussi", "votre message a été bien envoyer");
                   else
                       request.setAttribute("erreur", "Erreur: votre message n'a pas été envoyer");
                }
                rd = request.getRequestDispatcher("vue/contact.jsp");
            } 
                    else if (action.equals("listerCodifiers")||action.equals("listeCodifiersDuForm")) {
                        Calendar now = Calendar.getInstance();
                        int annee=now.get(Calendar.YEAR);
                        if (action.equals("listeCodifiersDuForm")){
                            int a=0;
                            try{
                                 String [] tabF={"","L1","L2","L3","M1","M2","D1","D2","D3"};
                                 for(int i=0;i<9;i++){
                                     if(tabF[i].equalsIgnoreCase(request.getParameter("niveau"))){
                                         a=1;
                                     request.setAttribute("listeCodifiersDuForm",gestetu.listeDesCodifiersParForm(request.getParameter("formation"),i));   
                                 } 
                                 }
                           if(a==0){
                                         request.setAttribute("erreur", "aucun étudiant codifier pour la formation "+request.getParameter("formation")+" de niveau "+request.getParameter("niveau")+" n'a été touver");
                                     }
                            }catch(RuntimeException e){
                                request.setAttribute("erreur", e.getMessage());
                            }
                       
                        }
                        
                        try{
                            String [] tabF={"","L1","L2","L3","M1","M2","D1","D2","D3"};
                          request.setAttribute("listeCodifiers", gestetu.listeDesCodifiers()); 
                          request.setAttribute("tabF", tabF);
                        }catch(RuntimeException e){
                                request.setAttribute("erreur", e.getMessage());
                            }
                       
                       rd = request.getRequestDispatcher("vue/listeDesCodifiers.jsp");
                    }
           else if (action.equals("compteEtudiant") || action.equals("modifierCompte") || (action.equals("vmodifier"))) {
                if (action.equals("modifierCompte")){
                    request.setAttribute("comptActive", "active");
                }
                if(action.equals("vmodifier")){
                    String mactuel=request.getParameter("mactuel");
                    String mnouveau=request.getParameter("mnouveau");
                    String mconf=request.getParameter("mconf");
                    String mancien=request.getParameter("mancien");
                    int i=0;
                    if((mancien.equals(mactuel)) ){
                        if(mnouveau.equals(mconf)){
                          i=gestetu.modifierMotDePasse(Integer.parseInt(request.getParameter("idEtu")), mnouveau);
                          if(i>0)
                            request.setAttribute("reussi", "Votre mot de passe a été bien modifié");
                        else 
                            request.setAttribute("erreur", "Erreur de modification de mot de passe");
                        }
                        else{
                            request.setAttribute("comptActive", "active");
                            request.setAttribute("erreurconf", "Mot de passe non identique");
                        }
                        
                    }else{
                        request.setAttribute("comptActive", "active");
                        request.setAttribute("erreurvalide", "Mot de passe non valide");
                    }
                    }
                entete = "Mon compte";
                HttpSession session=request.getSession();
                etudiantf.setEtu(gestetu.rechercheEtudiantParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
                request.setAttribute("etudiant", etudiantf.getEtu());
                rd = request.getRequestDispatcher("vue/compteEtudiant.jsp");
            }
           else if(action.equals("compteEmployer") || action.equals("modifierCompteEmp")|| action.equals("vmodifierEmp")){
               if (action.equals("modifierCompteEmp")){
                    request.setAttribute("comptActive", "active");
                }
                if(action.equals("vmodifierEmp")){
                    String mactuel=request.getParameter("mactuel");
                    String mnouveau=request.getParameter("mnouveau");
                    String mconf=request.getParameter("mconf");
                    String mancien=request.getParameter("mancien");
                    int i=0;
                    if((mancien.equals(mactuel)) ){
                        if(mnouveau.equals(mconf)){
                          i=gestemp.modifierMotDePasse(request.getParameter("matricule"), mnouveau);
                          if(i>0)
                            request.setAttribute("reussi", "Votre mot de passe a été bien modifié");
                        else 
                            request.setAttribute("erreur", "Erreur de modification de mot de passe");
                        }
                        else{
                            request.setAttribute("comptActive", "active");
                            request.setAttribute("erreurconf", "Mot de passe non identique");
                        }
                        
                    }else{
                        request.setAttribute("comptActive", "active");
                        request.setAttribute("erreurvalide", "Mot de passe non valide");
                    }
                }
               HttpSession session=request.getSession();
               employerf.setEmp(gestemp.rechercheEmployerParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
               request.setAttribute("employer", employerf.getEmp());
               rd = request.getRequestDispatcher("vue/comptEmployer.jsp");
           }
            else if (action.equals("modifierEtu")) {
                if (action.equals("modifierEtu")) {
                    etudiantf.setNumeroEtudiant(Integer.parseInt(request.getParameter("numero")));
                    etudiantf.setEtu(gestetu.rechercheEtudiant(etudiantf.getNumeroEtudiant()));
                    request.setAttribute("etudiant", etudiantf.getEtu());
                }
                entete = "Modifier etudiant";
                rd = request.getRequestDispatcher("vue/modifierEtudiant.jsp");
            } 
            else if (action.equals("detailleEtu")) {
                String [] tabF={"","L1","L2","L3","M1","M2","D1","D2","D3"};
                          request.setAttribute("listeCodifiers", gestetu.listerEtudiant()); 
                          request.setAttribute("tabF", tabF);
                    etudiantf.setNumeroEtudiant(Integer.parseInt(request.getParameter("numero")));
                    etudiantf.setEtu(gestetu.rechercheEtudiant(etudiantf.getNumeroEtudiant()));
                    request.setAttribute("etudiant", etudiantf.getEtu());
                    entete = "detaille etudiant";
                rd = request.getRequestDispatcher("vue/detailleEtudiant.jsp");
            }
            else if (action.equals("insererEmployer")) {
                entete = "Ajout employer";
                rd = request.getRequestDispatcher("vue/insertionEmployer.jsp");
            } else if (action.equals("insertionPavion")) {
                entete = "Ajout pavion";
                rd = request.getRequestDispatcher("vue/insertionPavion.jsp");
            } else if (action.equals("supprimerPavion")||action.equals("supPavion")) {
                pavionf.setListePav(gestpv.listerPavion());
                request.setAttribute("lesPavion", pavionf.getListePav());
                if(action.equals("supPavion")){
                    int i=0;
                    int a=gestCh.supprimerLesChambresDuPavion(Integer.parseInt(request.getParameter("pavion")));
                    if(a>0){
                     i=gestpv.supprimerPavion(Integer.parseInt(request.getParameter("pavion")));
                    }
                    if(i>0){
                        request.setAttribute("reussi", "Pavillon supprimer avec succés");
                    }else if(i<=0){
                       request.setAttribute("erreur", "suppression non pris en compte"); 
                    }
                }
                rd = request.getRequestDispatcher("vue/supprimerPavion.jsp");
            } 
            else if (action.equals("insererEtudiant")) {
                entete = "Ajouter etudiant";
                rd = request.getRequestDispatcher("vue/insertionEtudiant.jsp");
            }
            else if (action.equals("payer")) {
                int idEtu=Integer.parseInt(request.getParameter("idEtudiant"));
                int annee=Integer.parseInt(request.getParameter("annee"));
                int i=0;
                try{
                i=gployer.leMoiAPayer(annee, idEtu);
                 System.out.println("PPPP"+i);
                if(i!=7){
                   i=gployer.leMoiAPayer(annee, idEtu)+1; 
                }
                else{
                   i=i+3; 
                }
                }catch(RuntimeException e){
                 i=1; 
                 System.out.println(e.getMessage());
                }
                request.setAttribute("leMoiAPayer", i);
                entete = "Payer loyer";
                rd = request.getRequestDispatcher("vue/insertionPloyer.jsp");
            } else if (action.equals("connexion")) {
                entete = "Connexion";
                rd = request.getRequestDispatcher("vue/connexion.jsp");
            } else if (action.equals("Se connecter")) {
                String pseudo = request.getParameter("pseudo");
                String motDePasse = request.getParameter("motDePasse");
                String profil = request.getParameter("profil");
                HttpSession session = request.getSession();
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                employerf.setListeEmployer(gestemp.listerEmployer());
                int teste = 0;
                System.out.println(motDePasse+"hhhhh"+pseudo);
                if(etudiantf.getListeEtudiant()!=null){
                   for (Etudiant etu : etudiantf.getListeEtudiant()) {
                    System.out.println(etu.getPseudo());
                    System.out.println(etu.getMot_de_passe());
                    System.out.println(etu.isDisponibilite());
                    if (etu.getPseudo().equals(pseudo) && etu.getMot_de_passe().equals(motDePasse) && profil.equals("Etudiant") && etu.isDisponibilite() == true) {
                        teste = 1;
                        session.setAttribute("pseudo", pseudo);
                        session.setAttribute("motDePasse", motDePasse);
                        session.setAttribute("profile", profil);
                        entete = "Accueil";
                        rd = request.getRequestDispatcher("vue/accueil.jsp");
                    }
                } 
                }
                if(employerf.getListeEmployer()!=null){
                  for (Employer emp : employerf.getListeEmployer()) {
                    if (emp.getPseudo().equals(pseudo) && emp.getMot_de_passe().equals(motDePasse) && emp.getProfile().equalsIgnoreCase(profil) && emp.isDisponibilite() == true) {
                        teste = 1;
                        session.setAttribute("pseudo", pseudo);
                        session.setAttribute("motDePasse", motDePasse);
                        session.setAttribute("profile", profil);
                        entete = "Accueil";
                        rd = request.getRequestDispatcher("vue/accueil.jsp");
                    }  
                }
                }

                if (teste == 0) {
                    entete = "Connexion";
                    request.setAttribute("erreurAuth", "Login ou mot de passe incorect");
                    rd = request.getRequestDispatcher("vue/connexion.jsp");
                }
            } else if (action.equals("deconnexion")) {
                HttpSession session = request.getSession();
                session.invalidate();
                entete = "Accueil";
                rd = request.getRequestDispatcher("vue/accueil.jsp");
            } else if (action.equals("inserez pavion")) {
                pavionf.setNomPavion(request.getParameter("nomPavion"));
                int nbchambre = Integer.parseInt(request.getParameter("nbchambre"));
                pavionf.setPav(new Pavion(pavionf.getNomPavion()));
                int k = 0;
                k = gestpv.ajouterPavion(pavionf.getPav());
                pavionf.setPav(gestpv.recherchePavion(request.getParameter("nomPavion")));
                entete = "Ajout pavion";
                int j = 0;
                if (k == 1) {
                    for (int i = 1; i <= nbchambre; i++) {
                        chambref.getChambre().setId_pavillon(pavionf.getPav().getIdPavion());
                        chambref.getChambre().setId_chambre(i);
                        chambref.getChambre().setReserver(false);
                        j = gestCh.ajouterChambre(chambref.getChambre());
                    }
                    if (j == 1) {
                        request.setAttribute("reussi", "ajout de Pavillon reussi");
                    } else {
                        request.setAttribute("erreur", "Erreur d'ajout de pavillon");
                    }
                } else {
                    request.setAttribute("erreur", "Erreur d'ajout de pavillon");
                }
                rd = request.getRequestDispatcher("vue/insertionPavion.jsp");
            }//les choix de chambre
            else if (action.equals("insererChoix") || action.equals("choixChambre")) {
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                choisirf.setListeChoisir(gestchoix.listerChoix());
                request.setAttribute("listerChoisir", choisirf.getListeChoisir());
                pavionf.setListePav(gestpv.listerPavion());
                request.setAttribute("listePavion", pavionf.getListePav());
                HttpSession session = request.getSession();
                String pseudo = (String) session.getAttribute("pseudo");
                etudiantf.setEtu(gestetu.rechercheParPseudo(pseudo));
                request.setAttribute("idEtu", etudiantf.getEtu().getIdEtudiant());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+etudiantf.getEtu().getIdEtudiant());
                occupef.setListeOccupe(goccupe.listerOccupe());
                for (Occupe oc: occupef.getListeOccupe()){
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+etudiantf.getEtu().getIdEtudiant());
                    System.out.println("bbbbbbbbbbbbbbbbbbbbb"+oc.getIdEtudiant());
                    if(etudiantf.getEtu().getIdEtudiant()==oc.getIdEtudiant()){
                        request.setAttribute("erreur", "Vous occupez déjà une chambre");
                    }
                }
                choisirf.setC(gestchoix.rechercheChoixParEtudiant(etudiantf.getEtu().getIdEtudiant()));
                request.setAttribute("choixEtudiant", choisirf.getC());
                if (action.equals("choixChambre")) {
                    pavionf.setNomPavion(request.getParameter("nomPavion"));
                    pavionf.setPav(gestpv.recherchePavion(pavionf.getNomPavion()));
                    chambref.setIdPavion(pavionf.getPav().getIdPavion());
                     Calendar now = Calendar.getInstance();
                    try {
                        chambref.setListeChambre(gestCh.chambreAChoisir(chambref.getIdPavion(),now.get(Calendar.YEAR)));
                        request.setAttribute("listeChoix", chambref.getListeChambre());
                    } catch (RuntimeException e) {
                        choisirf.setErreur(e.getMessage());
                        request.setAttribute("erreure", choisirf.getErreur());
                    }
                }

                entete = "Choix chambre";
                rd = request.getRequestDispatcher("vue/choisirChambre.jsp");
            } else if (action.equals("choisir")) {
                HttpSession session = request.getSession();
                etudiantf.setEtu(gestetu.rechercheParPseudo((String) session.getAttribute("pseudo")));
                choisirf.getC().setId_chambre(Integer.parseInt(request.getParameter("idChambre")));
                choisirf.getC().setId_pavillon(Integer.parseInt(request.getParameter("idPavion")));
                choisirf.getC().setId_etudiant(etudiantf.getEtu().getIdEtudiant());
                int a=gestchoix.effectuerChoix(choisirf.getC());
                if(a==0){
                    request.setAttribute("erreur", "Choix non pris en compte");
                }
                 if(a==1){
                    request.setAttribute("reussi", "choix éfféctuée avec succès");
                }
                message = "Choix reussie";
                entete = "Choix chambre";
                rd = request.getRequestDispatcher("vue/choisirChambre.jsp");
            } 
            else if (action.equals("Inserer etudiant")) {
                Part par=request.getPart("fichier");
                try{
                     int i=gestetu.insertionEtudiant(par.getInputStream());  
                     if(i>0){
                         request.setAttribute("reussi", "Enregistrement effectué avec succès");
                     }else{
                       request.setAttribute("erreur", "Erreure veuillez verifier si ces étudiants n'ont pas été déja enregistré ");
  
                     }
                     
                }catch(RuntimeException e){
                   request.setAttribute("erreur", "Le format de fichier n'est pas validé");
                }
                rd = request.getRequestDispatcher("vue/insertionEtudiant.jsp");
            } 
            else if (action.equals("Modifier etudiant")) {
            String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                int numEtu = Integer.parseInt(request.getParameter("numero"));
                etudiantf.getEtu().setNom(nom);
                etudiantf.getEtu().setPrenom(prenom);
                etudiantf.getEtu().setSexe(request.getParameter("sexe"));
                etudiantf.getEtu().setPseudo(request.getParameter("email"));
                etudiantf.getEtu().setNumeroEtudiant(numEtu);
                etudiantf.getEtu().setEmail(request.getParameter("email"));
                etudiantf.getEtu().setUfr(request.getParameter("ufr"));
                etudiantf.getEtu().setDepartement(request.getParameter("departement"));
                etudiantf.getEtu().setNiveau(Integer.parseInt(request.getParameter("niveau")));
                etudiantf.getEtu().setNiveau(Integer.parseInt(request.getParameter("niveau")));
                etudiantf.getEtu().setFormation(request.getParameter("formation"));
                etudiantf.getEtu().setLieuNai(request.getParameter("lieu"));
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  // United States style of format.
                String dateNai=request.getParameter("dateN");
                System.out.println(dateNai);
                char lettre = '-';
                String motD="";
                for (int i = 0; i < dateNai.length(); i++) {
                    if (dateNai.charAt(i)==lettre) {
                     motD=motD+"/";   
                    }
                    else{
                    motD=motD+dateNai.charAt(i); 
                    }
                }
                String motDate=substring(motD,motD.length()-2,motD.length())+"/";
                motDate=motDate+substring(motD,motD.length()-5,motD.length()-2);
                motDate=motDate+substring(motD,0,motD.length()-6);
                System.out.println(motDate);
                 int bien=0;
                try {
                    java.util.Date myDate = format.parse(motDate);
                    java.sql.Date sqlDate = new java.sql.Date( myDate.getTime() );
                     System.out.println(sqlDate);
                    etudiantf.getEtu().setDateNai(sqlDate);
                    System.out.println(etudiantf.getEtu().getDateNai());
                    bien=1;
                } catch (ParseException ex) {
                    Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                etudiantf.setIdEtudiant(Integer.parseInt(request.getParameter("idEtu")));
                    etudiantf.getEtu().setIdEtudiant(etudiantf.getIdEtudiant());
                    int k = gestetu.modifierEtudiant(etudiantf.getEtu());
                    if (k == 0) {
                        request.setAttribute("erreure", "Etudiant n'a pas était modifier");
                    }
                    if (k > 0) {
                        request.setAttribute("reussi", "Etudiant a était bien modifier ");
                    }
                     etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher("vue/ListerEtudiant.jsp");
                        }
            else if (action.equals("Inserer classe")) {
                classef.getClasse().setDepartement(request.getParameter("departemnt"));
                classef.getClasse().setFormation(request.getParameter("formation"));
                classef.getClasse().setNiveau(request.getParameter("niveau"));
                classef.getClasse().setUfr(request.getParameter("ufr"));
                gestclasse.ajouterClasse(classef.getClasse());
                entete = "Ajout classe";
                rd = request.getRequestDispatcher("vue/insertionClasse.jsp");
            } else if (action.equals("listerEtudiant") || action.equals("RechercherEtu")) {
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                if (action.equals("RechercherEtu")) {
                    try{
                    etudiantf.setEtu(gestetu.rechercheEtudiant(Integer.parseInt(request.getParameter("numEtu"))));
                    request.setAttribute("etudiant", etudiantf.getEtu());
                    }catch(RuntimeException e){
                        request.setAttribute("erreur", e.getMessage());
                    }
                }
                entete = "Gestion etudiant";
                rd = request.getRequestDispatcher("vue/ListerEtudiant.jsp");
            } else if (action.equals("supprimerEtu")) {
                entete = "Gestion etudiant";
                etudiantf.setIdEtudiant(Integer.parseInt(request.getParameter("idetu")));
                etudiantf.getEtu().setIdEtudiant(etudiantf.getIdEtudiant());
                int a = gestetu.supprimerEtudiant(etudiantf.getEtu());
                if (a > 0) {
                    request.setAttribute("reussi", "Etudiant supprimé avec succé");
                } else {
                    request.setAttribute("erreure", "imposible de supprimer cet(te) étudiant(e)");
                }
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher("vue/ListerEtudiant.jsp");
            } else if (action.equals("inscrire")) {
                fairef.getFaire().setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                fairef.getFaire().setIdUfr(Integer.parseInt(request.getParameter("idUfr")));
                fairef.getFaire().setIdDepartement(Integer.parseInt(request.getParameter("idDept")));
                fairef.getFaire().setIdFormation(Integer.parseInt(request.getParameter("idForm")));
                fairef.getFaire().setAnnee(Integer.parseInt(request.getParameter("annee")));
                int a = gestFaire.ajouterFaire(fairef.getFaire());
                if (a == 0) {
                    request.setAttribute("erreure", "etudiant n'a pas etait inscris");
                } else if (a > 0) {
                    request.setAttribute("reussi", "etudiant inscrite avec succes");
                }
                System.out.println(a);
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher("vue/ListerEtudiant.jsp");
            } else if (action.equals("debloquer")) {
                int idetu = Integer.parseInt(request.getParameter("idetu"));
                etudiantf.setIdEtudiant(idetu);
                gestetu.deBloquerCompteEtu(etudiantf.getIdEtudiant());
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("saclasse")) {
                ufrf.setListe(gestUfr.listerUfr());
                request.setAttribute("listeUfr", ufrf.getListe());
                deptF.setListeDept(gestDept.listerDepartement());
                request.setAttribute("listeDept", deptF.getListeDept());
                formationf.setListforms(gestForm.listerFormation());
                request.setAttribute("listeForm", formationf.getListforms());

                rd = request.getRequestDispatcher(response.encodeURL("vue/insertionfaire.jsp"));
            } else if (action.equals("bloquer")) {
                int idetu = Integer.parseInt(request.getParameter("idetu"));
                etudiantf.setIdEtudiant(idetu);
                gestetu.bloquerCompteEtu(etudiantf.getIdEtudiant());
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("decodifier")) {
                int idetu = Integer.parseInt(request.getParameter("idetu"));
                etudiantf.setIdEtudiant(idetu);
                gestetu.deCodifierEtu(etudiantf.getIdEtudiant());
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("codifier")) {
                int idetu = Integer.parseInt(request.getParameter("idetu"));
                etudiantf.setIdEtudiant(idetu);
                gestetu.codifierEtu(etudiantf.getIdEtudiant());
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("toutDec")) {
                gestetu.toutDeCodifierEtu();
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } 
            else if (action.equals("tbloquer")) {
                gestetu.toutBloquer();
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("dEca")) {
                Calendar now = Calendar.getInstance();
                gestetu.deBloquerLesCodifierDeLanneeDernier(now.get(calendar.YEAR)-1);
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            }
            else if (action.equals("bloquercod")) {
                gestetu.deBloquerCodifier();
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                rd = request.getRequestDispatcher(response.encodeURL("vue/ListerEtudiant.jsp"));
            } else if (action.equals("modEmp")) {
                employerf.setMatricule(request.getParameter("matricule"));
                employerf.setEmp(gestemp.rechercheEmployer(employerf.getMatricule()));
                request.setAttribute("employer", employerf.getEmp());
                rd = request.getRequestDispatcher("vue/insertionEmployer.jsp");
            } else if (action.equals("Inserer employer")||action.equals("Modifier employer")) {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                if (action.equals("Inserer employer")){
                employerf.getEmp().setDisponibilite(true);
                employerf.getEmp().setEmail(request.getParameter("email"));
                employerf.getEmp().setTelephone(Integer.parseInt(request.getParameter("telephone")));
                employerf.getEmp().setMatricule("M"+(gestemp.mettreDansListe(gestemp.listeMatricule())+1));
                employerf.getEmp().setMot_de_passe("UADB2017");
                employerf.getEmp().setNom(nom);
                employerf.getEmp().setPrenom(prenom);
                employerf.getEmp().setProfile(request.getParameter("profile"));
                employerf.getEmp().setPseudo(request.getParameter("email"));
                        int a=gestemp.ajouterEmployer(employerf.getEmp());
                if (a == 0) {
                    request.setAttribute("erreur", "ajout non pris en compte");
                } else if (a > 0) {
                    request.setAttribute("reussi", "Employer ajouter avec succés");
                }
                        }
                if (action.equals("Modifier employer")){
                employerf.getEmp().setDisponibilite(true);
                employerf.getEmp().setEmail(request.getParameter("email"));
                employerf.getEmp().setTelephone(Integer.parseInt(request.getParameter("telephone")));
                employerf.getEmp().setMatricule(request.getParameter("matricule"));
                employerf.getEmp().setMot_de_passe("UADB2017");
                employerf.getEmp().setNom(nom);
                employerf.getEmp().setPrenom(prenom);
                employerf.getEmp().setProfile(request.getParameter("profile"));
                employerf.getEmp().setPseudo(request.getParameter("email"));
                        int a=gestemp.modifierEmployer(employerf.getEmp());
                if (a == 0) {
                    request.setAttribute("erreur", "modification non pris en compte");
                } else if (a > 0) {
                    request.setAttribute("reussi", "Employer modifier avec succés");
                }
                        }
                
                entete = "Ajout Employer";
                rd = request.getRequestDispatcher("vue/insertionEmployer.jsp");
            } else if (action.equals("listerChoix") || action.equals("Modifier choix") || action.equals("supTous")|| action.equals("Payer caution")||action.equals("RecherCheChoixParEtu")) {
                if (action.equals("supTous")) {
                    gestchoix.toutSupprimerChoix();
                }
                if (action.equals("RecherCheChoixParEtu")) {
                 etudiantf.setNumeroEtudiant(Integer.parseInt(request.getParameter("numEtu")));
                 etudiantf.setEtu(gestetu.rechercheEtudiant(etudiantf.getNumeroEtudiant()));
                 choisirf.setC(gestchoix.rechercheChoixParEtudiant(etudiantf.getEtu().getIdEtudiant()));
                 request.setAttribute("rech", "active");
                 request.setAttribute("choix", choisirf.getC());
                }
                if (action.equals("Payer caution")) {
                    pCautionf.getPc().setIdPavion(Integer.parseInt(request.getParameter("nomPavion")));
                    pCautionf.getPc().setIdChambre(Integer.parseInt(request.getParameter("numChambre")));
                    pCautionf.getPc().setIdEtudiant(Integer.parseInt(request.getParameter("numEtud")));
                    pCautionf.getPc().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    pCautionf.getPc().setMontant(Integer.parseInt(request.getParameter("Mcaution")));
                    HttpSession session=request.getSession();
               employerf.setEmp(gestemp.rechercheEmployerParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
                    pCautionf.getPc().setMatriculeCompt(employerf.getEmp().getMatricule());
                    if (action.equals("Payer caution")) {
                        int i = gestPCaution.ajouterPCaution(pCautionf.getPc());
                        if (i == 0) {
                            request.setAttribute("erreur", "Payement du caution non pris en compte<br/> verifier si l'etudiant n'a pas deja payer pour cette année");
                        } else if (i > 0) {
                            request.setAttribute("reussi", "Payement du caution enregistrait avec succes");
                        }
                    }
                }
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                pavionf.setListePav(gestpv.listerPavion());
                choisirf.setListeChoisir(gestchoix.listerChoix());
                request.setAttribute("etudiant", etudiantf.getListeEtudiant());
                request.setAttribute("pavion", pavionf.getListePav());
                request.setAttribute("choisir", choisirf.getListeChoisir());
                entete = "Gestion des choix";
                rd = request.getRequestDispatcher("vue/ListerChoisir.jsp");
            } else if (action.equals("listerChambre") || action.equals("RechercherCh") || action.equals("validerPv1") || action.equals("inserez occupe") || action.equals("listeEtuOccupe") || action.equals("validerAnnee") || action.equals("reserver") || action.equals("publier") || action.equals("retirerEtudiant")) {
                pavionf.setListePav(gestpv.listerPavion());
                request.setAttribute("listePavion", pavionf.getListePav());
                entete = "Gestion chambre";
                if (action.equals("validerPv1") || action.equals("RechercherCh") || action.equals("inserez occupe") || action.equals("listeEtuOccupe") || action.equals("validerAnnee") || action.equals("reserver") || action.equals("publier") || action.equals("retirerEtudiant")) {
                    pavionf.setNomPavion(request.getParameter("nomPavion"));
                    pavionf.setPav(gestpv.recherchePavion(pavionf.getNomPavion()));
                    int idPavion = pavionf.getPav().getIdPavion();
                    int a=gestCh.nombreDeChambreDuPavion(idPavion);
                    request.setAttribute("nbC", a);
                    if (action.equals("reserver")) {
                        chambref.setIdPavion(Integer.parseInt(request.getParameter("idPavion")));
                        chambref.setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                        gestCh.reserver(true, chambref.getIdChambre(), chambref.getIdPavion());
                    }
                    if (action.equals("publier")) {
                        chambref.setIdPavion(Integer.parseInt(request.getParameter("idPavion")));
                        chambref.setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                        gestCh.reserver(false, chambref.getIdChambre(), chambref.getIdPavion());
                    }
                    try {
                        chambref.setListeChambre(gestCh.chambreDuPavion(idPavion));
                        request.setAttribute("listeChambre", chambref.getListeChambre());
                        request.setAttribute("listeChambreNonOccuper", gestCh.chambreNomOccupe(idPavion));
                    } catch (RuntimeException e) {
                        choisirf.setErreur(e.getMessage());
                        request.setAttribute("erreure", choisirf.getErreur());
                    }
                    if (action.equals("RechercherCh")) {
                        chambref.getChambre().setId_chambre(Integer.parseInt(request.getParameter("numCh")));
                        chambref.getChambre().setId_pavillon(idPavion);
                        try{
                        chambref.setChambre(gestCh.rechercheChambre(chambref.getChambre()));
                        request.setAttribute("chambre", chambref.getChambre());
                        }catch(RuntimeException e){
                            request.setAttribute("erreur", e.getMessage());
                        }
                    }
                    pavionf.setListePav(gestpv.listerPavion());
                    request.setAttribute("listePavion", pavionf.getListePav());
                    if (action.equals("inserez occupe")) {
                        occupef.getOccupe().setIdPavion(pavionf.getPav().getIdPavion());
                        occupef.getOccupe().setIdChambre(Integer.parseInt(request.getParameter("numChambre")));
                        etudiantf.setNumeroEtudiant(Integer.parseInt(request.getParameter("numEtudiant")));
                        etudiantf.setEtu(gestetu.rechercheEtudiant(etudiantf.getNumeroEtudiant()));
                        occupef.getOccupe().setIdEtudiant(etudiantf.getEtu().getIdEtudiant());
                        occupef.getOccupe().setAnnee(Integer.parseInt(request.getParameter("annee")));
                        int i = goccupe.ajouterOccupe(occupef.getOccupe());
                        if (i > 0) {
                            message = "Chambre attribuer à l'étudiant de numero carte permanant " + etudiantf.getNumeroEtudiant();
                        } else {
                            message = "Attribution chambre non pris en compte";
                        }
                    }
                    if (action.equals("listeEtuOccupe")) {
                        request.setAttribute("eo", "ok");
                        // chambref.setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                        //request.setAttribute("idChambre",chambref.getIdChambre() );
                    }
                    if (action.equals("validerAnnee") || action.equals("retirerEtudiant")) {
                        request.setAttribute("eo", "ok");
                        chambref.setIdPavion(pavionf.getPav().getIdPavion());
                        chambref.setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                        occupef.setAnnee(Integer.parseInt(request.getParameter("annee")));
                        try {
                            occupef.setListeOccupe(goccupe.occupeDuChambre(chambref.getIdPavion(), occupef.getAnnee(), chambref.getIdChambre()));
                            request.setAttribute("listeOccupeChambre", occupef.getListeOccupe());
                        } catch (RuntimeException e) {
                            choisirf.setErreur(e.getMessage());
                            request.setAttribute("erreur", choisirf.getErreur());
                        }
                        etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                        request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                        if (action.equals("retirerEtudiant")) {
                            request.setAttribute("eo", "ok");
                            occupef.getOccupe().setAnnee(Integer.parseInt(request.getParameter("annee")));
                            occupef.getOccupe().setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                            occupef.getOccupe().setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                            occupef.getOccupe().setIdPavion(pavionf.getPav().getIdPavion());
                            goccupe.supprimerOccupe(occupef.getOccupe());
                        }
                    }
                }
                entete = "Gestion chambre";
                rd = request.getRequestDispatcher("vue/listerChambre.jsp");
            }
            if (action.equals("occupeEtudiant")) {
                chambref.setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                pavionf.setNomPavion(request.getParameter("nomPavion"));
                request.setAttribute("idChambre", chambref.getIdChambre());
                request.setAttribute("nomPavion", pavionf.getNomPavion());
                entete = "Attribution chambre";
                rd = request.getRequestDispatcher("vue/insertionOccupe.jsp");
            } else if (action.equals("payerLoyer") || action.equals("validerPloyer") || action.equals("payer loyer") || action.equals("consulter") || action.equals("supPloyer") || action.equals("aeriere")) {
                pavionf.setListePav(gestpv.listerPavion());
                request.setAttribute("listePavion", pavionf.getListePav());
                rd = request.getRequestDispatcher("vue/payementLoyer.jsp");
                if (action.equals("validerPloyer") || action.equals("consulter") || action.equals("payer loyer") || action.equals("supPloyer") || action.equals("aeriere")) {
                    pavionf.setNomPavion(request.getParameter("nomPavion"));
                    pavionf.setPav(gestpv.recherchePavion(pavionf.getNomPavion()));
                    etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                    request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                    int idPavion = pavionf.getPav().getIdPavion();
                    occupef.setAnnee(Integer.parseInt(request.getParameter("annee")));
                    try{
                    occupef.setListeOccupe(goccupe.occupeDuPavion(idPavion, occupef.getAnnee()));
                    request.setAttribute("listeOccupe", occupef.getListeOccupe());
                    }catch(RuntimeException e){
                        request.setAttribute("erreur", e.getMessage());
                    }
                    pavionf.setListePav(gestpv.listerPavion());
                    request.setAttribute("listePavion", pavionf.getListePav());
                    entete = "Gestion loyer";
                     if (action.equals("payer loyer")) {
                    pavionf.setNomPavion(request.getParameter("nomPavion"));
                    pavionf.setPav(gestpv.recherchePavion(pavionf.getNomPavion()));
                    ployerf.getPloyer().setIdPavion(pavionf.getPav().getIdPavion());
                    ployerf.getPloyer().setIdChambre(Integer.parseInt(request.getParameter("numChambre")));
                    ployerf.getPloyer().setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                    ployerf.getPloyer().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    ployerf.getPloyer().setMoi(Integer.parseInt(request.getParameter("moi")));
                    ployerf.getPloyer().setPrixLoyer(Integer.parseInt(request.getParameter("prixLoyer")));
                    HttpSession session=request.getSession();
                    employerf.setEmp(gestemp.rechercheEmployerParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
                    ployerf.getPloyer().setMatriculeCompt(employerf.getEmp().getMatricule());
                    int i=gployer.ajouterPloyer(ployerf.getPloyer());
                    if(i>0)
                        request.setAttribute("reussi", "Paiement loyer effectuait avec succés");
                    else
                        request.setAttribute("erreur", "Paiement loyer non pris en compte");
                }
                    if (action.equals("aeriere")) {
                        occupef.setAnnee(Integer.parseInt(request.getParameter("annee")));
                        occupef.setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                        int moiNonPayer = -1;
                        String[] tab = {"", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre"};
                        request.setAttribute("nonMois", tab);
                        Calendar now = Calendar.getInstance();
                        request.setAttribute("dMoi", gployer.moiPayer(occupef.getIdEtudiant(), occupef.getAnnee()));
                        request.setAttribute("moiAct", now.get(Calendar.MONTH));
                        int [] table=new int[30];
                        int n=gployer.moiPayer(occupef.getIdEtudiant(), occupef.getAnnee());
                        int moiAct=now.get(Calendar.MONTH);
                        int j=0;
                        if(n<=12){
                        for(int i=n+1;i<moiAct+1;i++){
                            j++;
                           if(i==8){
                               i=i+2;
                               table[j]=i;
                           } else{
                               table[j]=i;
                           }
                        }
                        }else{
                            for(int i=n+1;i<moiAct+12+1;i++){
                            j++;
                           if(i==20){
                               i=i+2;
                               table[j]=i;
                           } else{
                               table[j]=i;
                           }
                        }
                        }
                        request.setAttribute("tab2", table);
                        
                        request.setAttribute("nombreP", moiNonPayer);
                    }
                    if (action.equals("consulter") || action.equals("supPloyer")) {
                        request.setAttribute("cons", "active");
                        occupef.setAnnee(Integer.parseInt(request.getParameter("annee")));
                        occupef.setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));

                        if (action.equals("supPloyer")) {
                            ployerf.getPloyer().setIdChambre(Integer.parseInt(request.getParameter("idChambre")));
                            ployerf.getPloyer().setIdPavion(Integer.parseInt(request.getParameter("idPavion")));
                            ployerf.getPloyer().setMoi(Integer.parseInt(request.getParameter("moi")));
                            ployerf.getPloyer().setAnnee(Integer.parseInt(request.getParameter("annee")));
                            ployerf.getPloyer().setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                            gployer.supprimerPloyer(ployerf.getPloyer());
                        }
                        etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                        request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                        try {
                            String[] tab = {"", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet", "", "", "Octobre", "Nonvembre", "Decembre","Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet", "", "", "Octobre", "Nonvembre", "Decembre"};
                            request.setAttribute("mois", tab);
                            ployerf.setListePloyer(gployer.recherchcherPloyerParEtudiant(occupef.getIdEtudiant(), occupef.getAnnee()));
                            request.setAttribute("listePloyer", ployerf.getListePloyer());
                        } catch (RuntimeException e) {
                            choisirf.setErreur(e.getMessage());
                            request.setAttribute("erreur", choisirf.getErreur());
                        }
                    }
                }
                rd = request.getRequestDispatcher("vue/payementLoyer.jsp");
            } 
            else if(action.equals("consulterArrieres")){
              HttpSession session=request.getSession();
                etudiantf.setEtu(gestetu.rechercheEtudiantParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
                request.setAttribute("etudiant", etudiantf.getEtu());
                        occupef.setAnnee(Integer.parseInt(request.getParameter("annee")));
                        occupef.setIdEtudiant(etudiantf.getEtu().getIdEtudiant());
                        int moiNonPayer = -1;
                        String[] tab = {"", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre"};
                        request.setAttribute("nonMois", tab);
                        Calendar now = Calendar.getInstance();
                        request.setAttribute("dMoi", gployer.moiPayer(occupef.getIdEtudiant(), occupef.getAnnee()));
                        request.setAttribute("moiAct", now.get(Calendar.MONTH));
                        int [] table=new int[30];
                        int n=gployer.moiPayer(occupef.getIdEtudiant(), occupef.getAnnee());
                        int moiAct=now.get(Calendar.MONTH);
                        int j=0;
                        if(n<=12){
                        for(int i=n+1;i<moiAct;i++){
                            j++;
                           if(i==8){
                               i=i+2;
                               table[j]=i;
                           } else{
                               table[j]=i;
                           }
                        }
                        }else{
                            for(int i=n+1;i<moiAct+12;i++){
                            j++;
                           if(i==20){
                               i=i+2;
                               table[j]=i;
                           } else{
                               table[j]=i;
                           }
                        }
                        }
                        request.setAttribute("tab2", table); 
                         rd = request.getRequestDispatcher("vue/compteEtudiant.jsp");
            }
            else if (action.equals("CautionNonV")){
                request.setAttribute("pasAnne", "active");
              etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                    request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                    pavionf.setListePav(gestpv.listerPavion());
                    request.setAttribute("listePavion", pavionf.getListePav());
                try {
                        Calendar now = Calendar.getInstance();
                        request.setAttribute("listePCauNV",gestPCaution.PCautionNonValider(now.get(Calendar.YEAR)));
                    } catch (RuntimeException e) {
                        request.setAttribute("erreurSec", e.getMessage());
                    } 
                rd = request.getRequestDispatcher("vue/listerPCaution.jsp");
            }
            else if (action.equals("gestCaution") || action.equals("validerAnPCaution") || action.equals("supCaution")||action.equals("Modifier caution")) {
                if (action.equals("supCaution")) {
                    pCautionf.getPc().setIdPavion(Integer.parseInt(request.getParameter("idPavion")));
                    pCautionf.getPc().setIdChambre(Integer.parseInt(request.getParameter("idCh")));
                    pCautionf.getPc().setIdEtudiant(Integer.parseInt(request.getParameter("idEtudiant")));
                    pCautionf.getPc().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    int i = gestPCaution.supprimerPCaution(pCautionf.getPc());
                    if (i == 0) {
                        request.setAttribute("erreur", "Suppression non pris en compte");
                    } else if (i > 0) {
                        request.setAttribute("reussi", "Payement du caution supprimer avec succes");
                    }
                }
                     
                if (action.equals("validerAnPCaution")||action.equals("Modifier caution")) {
                    int som=gestPCaution.sommeTCaution(Integer.parseInt(request.getParameter("annee")));
                    request.setAttribute("somme", som);
                    if (action.equals("Modifier caution")) {
                    pCautionf.getPc().setIdPavion(Integer.parseInt(request.getParameter("nomPavion")));
                    pCautionf.getPc().setIdChambre(Integer.parseInt(request.getParameter("numChambre")));
                    pCautionf.getPc().setIdEtudiant(Integer.parseInt(request.getParameter("numEtud")));
                    pCautionf.getPc().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    pCautionf.getPc().setMontant(Integer.parseInt(request.getParameter("Mcaution")));
                    HttpSession session=request.getSession();
               employerf.setEmp(gestemp.rechercheEmployerParCompte((String)session.getAttribute("pseudo"), (String)session.getAttribute("motDePasse")));
                    pCautionf.getPc().setMatriculeCompt(employerf.getEmp().getMatricule());
                    
                        int i = gestPCaution.modifierPCaution(pCautionf.getPc());
                        if (i == 0) {
                            request.setAttribute("erreur", "modification non pris en compte");
                        } else if (i > 0) {
                            request.setAttribute("reussi", "Payement du caution modifié avec succes");
                        }
                    }
                    etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                    request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                    pavionf.setListePav(gestpv.listerPavion());
                    request.setAttribute("listePavion", pavionf.getListePav());
                    pCautionf.getPc().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    try {
                        pCautionf.setListePCaution(gestPCaution.rechercheParAnnee(pCautionf.getPc().getAnnee()));
                        request.setAttribute("listePCaution", pCautionf.getListePCaution());
                    } catch (RuntimeException e) {
                        request.setAttribute("erreur", e.getMessage());
                    }
                }
                rd = request.getRequestDispatcher("vue/listerPCaution.jsp");
            } else if (action.equals("validerLeChoix")) {
                occupef.getOccupe().setIdPavion(Integer.parseInt(request.getParameter("idPavion")));
                occupef.getOccupe().setIdChambre(Integer.parseInt(request.getParameter("numCh")));
                occupef.getOccupe().setIdEtudiant(Integer.parseInt(request.getParameter("idEtu")));
                occupef.getOccupe().setAnnee(Integer.parseInt(request.getParameter("annee")));
                int i = goccupe.ajouterOccupe(occupef.getOccupe());
                if (i > 0) {
                    request.setAttribute("reussi", "Choix validé avecv succés");
                    choisirf.getC().setId_pavillon(Integer.parseInt(request.getParameter("idPavion")));
                    choisirf.getC().setId_chambre(Integer.parseInt(request.getParameter("numCh")));
                    choisirf.getC().setId_etudiant(Integer.parseInt(request.getParameter("idEtu")));
                    gestchoix.supprimerChoix(choisirf.getC());
                } else {
                    request.setAttribute("erreur", "Validation choix non pris en compte");
                }

                rd = request.getRequestDispatcher("vue/listerPCaution.jsp");
            } else if (action.equals("payerLaCaution") || action.equals("modCaution")) {
                pavionf.setListePav(gestpv.listerPavion());
                request.setAttribute("listePavion", pavionf.getListePav());
                rd = request.getRequestDispatcher("vue/insertionCaution.jsp");
            } else if (action.equals("listerEmployer") || action.equals("supEmp") || action.equals("bloquerEmp") || action.equals("debloquerEmp")) {
                if (action.equals("bloquerEmp")) {
                    employerf.setMatricule(request.getParameter("matricule"));
                    gestemp.bloquer(employerf.getMatricule());
                }
                if (action.equals("debloquerEmp")) {
                    employerf.setMatricule(request.getParameter("matricule"));
                    gestemp.debloquer(employerf.getMatricule());
                }
                if (action.equals("supEmp")) {
                    employerf.setMatricule(request.getParameter("matricule"));
                    gestemp.supprimerEmployer(employerf.getMatricule());
                }
                employerf.setListeEmployer(gestemp.listerEmployer());
                request.setAttribute("employer", employerf.getListeEmployer());
                entete = "Gestion Employer";
                rd = request.getRequestDispatcher("vue/ListerEmployer.jsp");
            }
            else if(action.equals("ConCompt")||action.equals("efCaution")||action.equals("efComptable")){
                String act=request.getParameter("act");
                String matricule=request.getParameter("matricule");
                if(action.equals("ConCompt")){
                employerf.setListeEmployer(gestemp.listerComptable());
                request.setAttribute("employer", employerf.getListeEmployer());
                }
                else if(action.equals("efCaution")){
               request.setAttribute("efCompt", true);
               
           }
                else if(action.equals("efComptable")){
                System.out.println("???????"+matricule);
                int annee=Integer.parseInt(request.getParameter("annee"));
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                    request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                    pavionf.setListePav(gestpv.listerPavion());
                    request.setAttribute("listePavion", pavionf.getListePav());
                    pCautionf.getPc().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    if(act.equals("caution")){
                    try {
                             pCautionf.setListePCaution(gestPCaution.rechercheParAnneeMatri(annee, matricule));
                             request.setAttribute("listePCaution", pCautionf.getListePCaution());
                         } catch (RuntimeException e) {
                             request.setAttribute("erreur", e.getMessage());
                         }  
                    }
                         if(act.equals("loyer")){
                              String[] tab = {"", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre", "Janvier", "Fevier", "Mars", "Avril", "Mai", "Juin", "Juillet","vac","vac","Octobre", "Nonvembre", "Decembre"};
                        request.setAttribute("nonMois", tab);
                        try{
                    request.setAttribute("listLoyer", gployer.recherchcherPloyerParMatriculeCompt(matricule, annee));
                        }catch(RuntimeException e) {
                             request.setAttribute("erreur", e.getMessage());
                        }
                    }
              }
              rd = request.getRequestDispatcher("vue/controlCompt.jsp"); 
           }
             else if (action.equals("rechercherEmp")) {
              employerf.setMatricule(request.getParameter("matricule"));
              try{
               employerf.setEmp(gestemp.rechercheEmployer(employerf.getMatricule()));
               request.setAttribute("leEmployer", employerf.getEmp());
              }catch(RuntimeException e){
               request.setAttribute("erreur", "Aucun employer de matricule "+request.getParameter("matricule")+" n'a été trouvé");
             }
              rd = request.getRequestDispatcher("vue/ListerEmployer.jsp"); 
             }
            else if (action.equals("optionFormation")) {
                    rd = request.getRequestDispatcher("vue/optionFormation.jsp");
                }
            else if (action.equals("listerFaire") || action.equals("vAnneeFormation") || action.equals("supFaire") || action.equals("RechercherEtuForm")) {
                if (action.equals("supFaire")) {
                    fairef.getFaire().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    fairef.getFaire().setIdFormation(Integer.parseInt(request.getParameter("idForm")));
                    fairef.getFaire().setIdUfr(Integer.parseInt(request.getParameter("idUfr")));
                    fairef.getFaire().setIdDepartement(Integer.parseInt(request.getParameter("idDept")));
                    fairef.getFaire().setIdEtudiant(Integer.parseInt(request.getParameter("idEtu")));
                    int i = gestFaire.supprimerFaire(fairef.getFaire());
                    if (i > 0) {
                        request.setAttribute("reussi", " inscription retirer avec succés");
                    } else {
                        request.setAttribute("erreur", "retré non prit en compte");
                    }
                }
                if (action.equals("vAnneeFormation")) {
                    fairef.getFaire().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    fairef.getFaire().setIdFormation(Integer.parseInt(request.getParameter("idForm")));
                    request.setAttribute("rech", "active");
                    try {
                        fairef.setListeFaire(gestFaire.rechercheFaire(fairef.getFaire().getIdFormation(), fairef.getFaire().getAnnee()));
                        request.setAttribute("listeFaire", fairef.getListeFaire());
                    } catch (RuntimeException e) {
                        request.setAttribute("erreur", e.getMessage());
                    }
                }
                if (action.equals("RechercherEtuForm")) {
                    request.setAttribute("rech", "active");
                    etudiantf.setNumeroEtudiant(Integer.parseInt(request.getParameter("numEtu")));
                     try{
                    etudiantf.setEtu(gestetu.rechercheEtudiant(etudiantf.getNumeroEtudiant()));
                    fairef.getFaire().setAnnee(Integer.parseInt(request.getParameter("annee")));
                    fairef.getFaire().setIdEtudiant(etudiantf.getEtu().getIdEtudiant());
                    request.setAttribute("rech", "active");
                    try {
                        fairef.setFaire(gestFaire.rechercheFaireParEtudiant(fairef.getFaire().getIdEtudiant(), fairef.getFaire().getAnnee()));
                        request.setAttribute("faire2", fairef.getFaire());
                    } catch (RuntimeException e) {
                        request.setAttribute("erreur", e.getMessage());
                    }
                }catch(RuntimeException e){
                        request.setAttribute("erreur", e.getMessage());
                        }
                }
                ufrf.setListe(gestUfr.listerUfr());
                request.setAttribute("listeUfr", ufrf.getListe());
                deptF.setListeDept(gestDept.listerDepartement());
                request.setAttribute("listeDept", deptF.getListeDept());
                formationf.setListforms(gestForm.listerFormation());
                request.setAttribute("listeForm", formationf.getListforms());
                etudiantf.setListeEtudiant(gestetu.listerEtudiant());
                request.setAttribute("listeEtu", etudiantf.getListeEtudiant());
                entete = "Gestion classe";
                rd = request.getRequestDispatcher("vue/listerFaire.jsp");
            }
            request.setAttribute("entete", entete);
            request.setAttribute("message", message);
        }
        if (rd != null) {
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description occupef the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
