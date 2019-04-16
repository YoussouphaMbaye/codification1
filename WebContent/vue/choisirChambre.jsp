<%-- 
    Document   : choisirChambre
    Created on : 4 juin 2017, 09:43:32
    Author     : youssou
--%>
<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Choix de chambres</h1><hr/>

    <c:forEach var="ch" items="${listerChoisir}"> 
        <c:forEach var="etu" items="${listeEtu}"> 
            <c:if test="${ch.id_etudiant==etu.idEtudiant && etu.idEtudiant==idEtu}">
                <c:set var="i" value="1" scope="page"/>
            </c:if>
        </c:forEach>
    </c:forEach>
    <c:if test="${i==1 && !empty idEtu}">
        <p class="erreur">
            Vous avez déja choisi une chambre
            <c:forEach var="pav" items="${listePavion}">
                <c:if test="${pav.idPavion == 1}">
                    dans le <i>pavion</i> ${pav.nomPavion} de <i>numero</i> ${choixEtudiant.id_chambre}
                </c:if>
            </c:forEach>
        </p>
    </c:if>
    <c:if test="${i!=1 && !empty idEtu && empty erreur}">
        <h3>Veuillez choisir un des pavions</h3>
        <form action="Controleur" method="POST">
            <label for="nomPavion">Nom du Pavion:</label>
            <select id="nomPavion" name="nomPavion">
                <c:if test="${!empty param.nomPavion}">
                    <option value="${param.nomPavion}">${param.nomPavion}</option>   
                </c:if>
                <c:forEach var="pv" items="${listePavion}">  
                    <option value="${pv.nomPavion}">${pv.nomPavion}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="choixChambre"/>
            <input type="hidden" name="numEtu" value="${param.numEtu}"/>
            <input type="submit" value="valider"/>
        </form>
        <p class="erreur">${erreur}</p>
    </c:if>
    <c:if test="${!empty listeChoix}">
        <table border="1">
            <tr>
                <th>Numero de chambre</th>
            </tr>
            <c:forEach var="ch" items="${listeChoix}">  
                <tr>
                    <td>${ch.id_chambre}</td> 
                    <td><c:url var="choix" value="Controleur?action=choisir&idChambre=${ch.id_chambre}&idPavion=${ch.id_pavillon}&numEtu=${param.numEtu}"/><a href="${choix}">Choisir</a></td>
                </tr>
            </c:forEach>   
        </table>
    </c:if>
    <c:if test="${!empty reussi}">
        <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
        </c:if>
        <c:if test="${!empty erreur}">
        <p class="erreur">${erreur}</p>
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 

