<%-- 
    Document   : payementLoyer
    Created on : 31 mai 2017, 09:35:09
    Author     : youssou
--%>
<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Gestion Loyer</h1><hr/>
    <div class="champ">
        <form action="Controleur" method="POST">
            <table>
                <tr>
                    <td><label for="nomPavion">NomPavion:</label></td>
                    <td>
                        <select id="nomPavion" name="nomPavion">
                            <c:forEach var="pv" items="${listePavion}">  
                                <option value="${pv.nomPavion}">${pv.nomPavion}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><label for="annee">Année:</label></td>
                    <td><input type="text" name="annee" id="annee" value="${param.annee}" required /></td>
                <input type="hidden" name="action" value="validerPloyer"/><br/>
                <td><input type="submit" value="valider"/></td>
                </tr>
            </table>
        </form>
    </div>
     <c:if test="${!empty reussi || !empty erreur}">
        <div class="champ">

            <c:if test="${!empty reussi}">
                <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
                </c:if>
                <c:if test="${!empty erreur}">
                <p class="erreur">${erreur}</p>
            </c:if>

        </div>
    </c:if>
    <c:if test="${!empty nonMois}">
            <div class="champ">
            <h3> Vous devez payer les mois suivantes:<br/></h3>
            
                <c:forEach var="i" items="${tab2}">
                    <c:if test="${i!=0}">
                      ${nonMois[i]}  
                    </c:if>
                </c:forEach>
            </div>
            </c:if>
            
    <c:if test="${!empty listeOccupe and empty cons}">
        <div class="laTable">
            <table border="1">
                <tr>
                    <th>Numero chambre</th> <th>Numero Etudiant</th><th>Nom</th>
                </tr>
                <c:forEach var="oc" items="${listeOccupe}">
                    <tr>
                        <td>${oc.idChambre}</td>
                        <c:forEach var="etu" items="${listeEtu}">  
                            <c:if test="${etu.idEtudiant==oc.idEtudiant}">
                                <td>${etu.numeroEtudiant}</td>
                                <td>${etu.prenom} ${etu.nom} </td>
                                <td><c:url var="pay" value="Controleur?action=payer&idChambre=${oc.idChambre}&numEtu=${etu.numeroEtudiant}&annee=${oc.annee}&nomPavion=${param.nomPavion}&idEtudiant=${oc.idEtudiant}&idPavion=${oc.idPavion}"/><a href="${pay}">Payer loyer</a></td>
                                <td><c:url var="consulter" value="Controleur?action=consulter&idChambre=${oc.idChambre}&idPavion=${oc.idPavion}&idEtudiant=${oc.idEtudiant}&annee=${oc.annee}&nomPavion=${param.nomPavion}"/><a href="${consulter}">Consulter</a></td>
                                <td><c:url var="ar" value="Controleur?action=aeriere&idChambre=${oc.idChambre}&idPavion=${oc.idPavion}&idEtudiant=${oc.idEtudiant}&annee=${oc.annee}&nomPavion=${param.nomPavion}"/><a href="${ar}">Arriérés</a></td>
                            </c:if>
                        </c:forEach>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    
        <c:if test="${!empty listePloyer}">
        <div class="laTable">
            <h3>Les mois payés</h3>
            <table border="1">
                <tr>
                    <th>Moi</th> <th>Date</th><th>Montant</th>
                </tr>
                <c:forEach var="pl" items="${listePloyer}">
                    <tr>
                        <td>${mois[pl.moi]}</td><td>${pl.datePloyer}</td><td>${pl.prixLoyer}</td>
                        <td><c:url var="sup" value="Controleur?action=supPloyer&idPavion=${pl.idPavion}&idChambre=${pl.idChambre}&annee=${pl.annee}&moi=${pl.moi}&idEtudiant=${pl.idEtudiant}&idEtudiant=${oc.idEtudiant}&annee=${oc.annee}&nomPavion=${param.nomPavion}"/><a href="javascript:confSup('${sup}')">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 
