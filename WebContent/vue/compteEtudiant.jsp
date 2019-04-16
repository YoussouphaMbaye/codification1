<%-- 
    Document   : compteEtudiant
    Created on : 6 juil. 2017, 10:58:00
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <c:url var="compt" value="Controleur">
        <c:param name="action" value="modifierCompte"/>
        <c:param name="idEtu" value="${etudiant.idEtudiant}"/>
    </c:url>
    <h1>Mon compte  <a class="compte" href="${compt}" ><img width="40" src="img/icone.jpg" alt="Icone" title="Modifier mon mot de passe"/></a></h1>
    <h3>Bienvenu : ${etudiant.prenom} ${etudiant.nom}</h3>
    <c:if test="${!empty comptActive}">

        <form action="Controleur" method="post">
            <table>
                <tr><th colspan="2"><h3>Modification de mot de passe</h3></th></tr>
                <tr>
                    <td><label for="mactuel">Mot de passe actuel :</label></td>
                    <td><input type="password" name="mactuel" id="mactuel"/></td>
                    <c:if test="${!empty erreurvalide}"><td><p class="erreur">${erreurvalide}</p></td></c:if>
                </tr>
                <tr>
                    <td><label for="mnouveau">Nouveau mot de passe :</label></td>
                    <td><input type="password" name="mnouveau" id="mnouveau"/></td>
                </tr>
                <tr>
                    <td><label for="mconf">Confirmer mot de passe :</label></td>
                    <td><input type="password" name="mconf" id="mconf"/></td>
                <c:if test="${!empty erreurconf}"><td><p class="erreur">${erreurconf}</p></td></c:if>
                <input type="hidden" name="action" value="vmodifier"/>
                <input type="hidden" name="mancien" value="${etudiant.mot_de_passe}"/>
                <input type="hidden" name="idEtu" value="${param.idEtu}"/>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Modifier" id="valider"/></td>
                </tr>
            </table>
        </form>
    </c:if>
    <c:if test="${!empty reussi}">
        <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
        </c:if>
        <c:if test="${!empty erreur}">
        <p class="erreur">${erreur}</p>
    </c:if>
        <div class="champ">
        <form action="Controleur" method="POST">
            <h3>Consulter mes arriérés<h3>
            <input type="text" name="annee" value="${param.annee}"/>
            <input type="hidden" name="action" value="consulterArrieres"/>
            <input type="submit" value="Consulter"/>
        </form>
         <c:if test="${!empty tab2}">
            <h3> Vous devez payer les mois suivantes:<br/></h3>
            
                <c:forEach var="i" items="${tab2}">
                    <c:if test="${i!=0}">
                      ${nonMois[i]}  
                    </c:if>
                </c:forEach>
            </c:if>
            </div>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 
