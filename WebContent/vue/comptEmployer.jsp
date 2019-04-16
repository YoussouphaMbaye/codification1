

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <c:url var="compt" value="Controleur">
        <c:param name="action" value="modifierCompteEmp"/>
        <c:param name="matricule" value="${employer.matricule}"/>
    </c:url>
    <h1>Mon compte  <a class="compte" href="${compt}" ><img width="40" src="img/icone.jpg" alt="Icone" title="Modifier mon mot de passe" /></a></h1>
    <h3>Bienvenu : ${employer.prenom} ${employer.nom}</h3>
    <c:if test="${!empty comptActive}">
        <form action="Controleur" method="post">
            <table>
                <tr><th colspan="2"><h3>Modification de mot de passe</h3></th></tr>
                <tr>
                    <td><label for="mactuel">Mot de passe actuel :</label></td>
                    <td><input type="password" name="mactuel" id="mactuel" required /></td>
                    <c:if test="${!empty erreurvalide}"><td><p class="erreur">${erreurvalide}</p></td></c:if>
                </tr>
                <tr>
                    <td><label for="mnouveau">Nouveau mot de passe :</label></td>
                    <td><input type="password" name="mnouveau" id="mnouveau" required /></td>
                </tr>
                <tr>
                    <td><label for="mconf">Confirmer mot de passe :</label></td>
                    <td><input type="password" name="mconf" id="mconf" required/></td>
                    <c:if test="${!empty erreurconf}"><td><p class="erreur">${erreurconf}</p></td></c:if>
                <input type="hidden" name="action" value="vmodifierEmp"/>
                <input type="hidden" name="mancien" value="${employer.mot_de_passe}"/>
                <input type="hidden" name="matricule" value="${param.matricule}"/>
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
</section>


<%@include file="../WEB-INF/footer.jspf" %> 

