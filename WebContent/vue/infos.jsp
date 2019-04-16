<%-- 
    Document   : infos
    Created on : 1 juin 2017, 17:40:30
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<section>
    <c:url var="listeC" value="Controleur">
        <c:param name="action" value="listerCodifiers"/>
    </c:url>
    <h1>Infos</h1><hr/>
    
        <div class="slider">
            <div><img src="img/tslide3.jpg" alt="slide3" width="560px"/></div>
        </div>
    <div class="contenueAccueil">
    <p class="para">
    <h2> Existence d'une commission d'attribution des lits</h2>
        La commission technique charg�e de l'affectation des lits (codification) a pour mission de 
        Proc�der �  une r�partition et une affectation juste, �quitable et transparente des lits aux b�n�ficiaires
       des oeuvres. Elle est compose des responsables de l'administration et de tous les pr�sidents d'amicales et
       de commissions sociales des facult�s et �coles et instituts. Ensemble ils d�terminant la r�partition des
       lits selon les quotas allouer � chaque facult� et �cole et instituts 
       rattaches a l'UCAD Ensuite vient l'attribution proprement dite des lits qui donne la priorit� aux anciens �tudiants.  
       <h2>Les codifiers de l'ann�e</h2>
       Vous pouvez voir tout les �l�ves qui ont droit a une codification cette annn�e.
       Ces �l�ves ont �t� design� grace a leur resultat effectuer l'ann�e pass� ou par ce que ce sont des cas sociales.
       </br>
      La liste des codifiers de L'ann�e: ${annee}
      <a href="${listeC}">Consulter la liste</a>
      
    </p>
    
    </div>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 