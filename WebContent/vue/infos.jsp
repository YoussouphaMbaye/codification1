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
        La commission technique chargée de l'affectation des lits (codification) a pour mission de 
        Procéder à  une répartition et une affectation juste, équitable et transparente des lits aux bénéficiaires
       des oeuvres. Elle est compose des responsables de l'administration et de tous les présidents d'amicales et
       de commissions sociales des facultés et écoles et instituts. Ensemble ils déterminant la répartition des
       lits selon les quotas allouer à chaque faculté et école et instituts 
       rattaches a l'UCAD Ensuite vient l'attribution proprement dite des lits qui donne la priorité aux anciens étudiants.  
       <h2>Les codifiers de l'année</h2>
       Vous pouvez voir tout les éléves qui ont droit a une codification cette annnée.
       Ces éléves ont été designé grace a leur resultat effectuer l'année passé ou par ce que ce sont des cas sociales.
       </br>
      La liste des codifiers de L'année: ${annee}
      <a href="${listeC}">Consulter la liste</a>
      
    </p>
    
    </div>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 