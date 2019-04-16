<%-- 
    Document   : listerChambr
    Created on : 24 mai 2017, 18:45:59
    Author     : youssou
--%>
<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Les occupants</h1><hr/>
    <div class="champ">
        <div class="pav">
            <form action="Controleur" method="POST">
                <h3>Veuillez choisir un des pavillon:</h3>
                <label for="nomPavion">Pavillon:</label>
                <select id="nomPavion" name="nomPavion">
                    <c:if test="${!empty param.nomPavion}">
                     <option value="${param.nomPavion}">${param.nomPavion}</option>   
                    </c:if>
                    <c:forEach var="pv" items="${listePavion}">  
                        <option value="${pv.nomPavion}">${pv.nomPavion}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="action" value="validerPv1"/>
                <input type="hidden" name="sec" value="${param.sec}"/>
                <input type="submit" value="valider"/>
            </form>
        </div>
        <c:if test="${!empty listeChambre}">
            <div class="recherche">
                <form action="Controleur" method="POST" class="2eChamp">
                    <input type="text" name="numCh" id="ch" value="${param.numCh}" placeholder="Numero de chambre" required />
                    <input type="hidden" name="nomPavion" value="${param.nomPavion}" required />
                    <input type="hidden" name="action" value="RechercherCh"/>
                    <input type="hidden" name="sec" value="${param.sec}"/>
                    <input type="submit"  value="Rechercher"/>
                </form>
            </div>
        </c:if>
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
    <c:if test="${!empty listeChambre}">
        <c:if test="${empty param.numCh && empty eo}">
            <div class="laTable">
                <table border="1">
                    <tr>
                        <th>Numero chambre</th> <th>resever</th>
                    </tr>
                    <tr>
                        <c:if test="${sessionScope.profile eq 'Chef de residence'}">
                            <c:set var="listeCh" value="${listeChambreNonOccuper}"/>
                        </c:if>
                        <c:if test="${sessionScope.profile eq 'Secretaire'}">
                            <c:set var="listeCh" value="${listeChambre}"/>
                        </c:if>
                        <c:forEach var="ch" items="${listeCh}">
                            <td>${ch.id_chambre}</td>
                            <c:if test="${ch.reserver}">
                                <td>OUI</td>
                                <c:if test="${sessionScope.profile eq 'Chef de residence'}">
                                    <td><c:url var="res" value="Controleur?action=publier&idChambre=${ch.id_chambre}&idPavion=${ch.id_pavillon}&nomPavion=${param.nomPavion}"/><a href="${res}">Publier</a></td>
                                </c:if>
                            </c:if>
                            <c:if test="${!ch.reserver}">
                                <td>NON</td>
                                <c:if test="${sessionScope.profile eq 'Chef de residence'}">
                                    <td><c:url var="res" value="Controleur?action=reserver&idChambre=${ch.id_chambre}&idPavion=${ch.id_pavillon}&nomPavion=${param.nomPavion}"/><a href="${res}">reserver</a></td>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.profile eq 'Secretaire'}">
                                <c:if test="${param.sec eq 'atChambre'}">
                                    <c:choose>
                                        <c:when test="${!ch.reserver}">
                                            <td><c:url var="res2" value="Controleur?action=occupeEtudiant&idChambre=${ch.id_chambre}&nomPavion=${param.nomPavion}&sec=atChambre"/><a href="${res2}">+Etudiant</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Réservée</td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                                <c:if test="${param.sec eq 'voc'}">
                                    <td><c:url var="res4" value="Controleur?action=listeEtuOccupe&idChambre=${ch.id_chambre}&nomPavion=${param.nomPavion}&idPavion=${ch.id_pavillon}&sec=voc"/><a href="${res4}">Occupant</a></td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
                Nombre de chambre du pavillon : ${nbC}
            </div>
        </c:if>
        <c:if test="${!empty param.numCh && !empty chambre}">
            <div class="laTable">
                <table border="1">
                    <tr>
                        <th>Numero chambre</th> <th>resever</th>
                    </tr>
                    <tr>
                        <td>${chambre.id_chambre}</td>
                        <c:if test="${chambre.reserver}">
                            <td>OUI</td>
                            <c:if test="${sessionScope.profile eq 'Chef de residence'}">
                                <td><c:url var="res" value="Controleur?action=publier&idChambre=${chambre.id_chambre}&idPavion=${chambre.id_pavillon}&nomPavion=${param.nomPavion}"/><a href="${res}">Publier</a></td>
                            </c:if>
                        </c:if>
                        <c:if test="${!chambre.reserver}">
                            <td>NON</td>
                            <c:if test="${sessionScope.profile eq 'Chef de residence'}">
                                <td><c:url var="res" value="Controleur?action=reserver&idChambre=${chambre.id_chambre}&idPavion=${chambre.id_pavillon}&nomPavion=${param.nomPavion}"/><a href="${res}">reserver</a></td>
                            </c:if>
                        </c:if>
                        <c:if test="${sessionScope.profile eq 'Secretaire'}">
                            <c:if test="${param.sec eq 'atChambre'}">
                                <c:choose>
                                    <c:when test="${!chambre.reserver}">
                                        <td><c:url var="res2" value="Controleur?action=occupeEtudiant&idChambre=${chambre.id_chambre}&nomPavion=${param.nomPavion}&sec=atChambre&numCh=${param.numCh}"/><a href="${res2}">+Etudiant</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Réservée</td>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${param.sec eq 'voc'}">
                                <td><c:url var="res4" value="Controleur?action=listeEtuOccupe&idChambre=${chambre.id_chambre}&nomPavion=${param.nomPavion}&idPavion=${chambre.id_pavillon}&sec=voc"/><a href="${res4}">Occupant</a></td>
                            </c:if>
                        </c:if>
                    </tr>

                </table>
            </div>
        </c:if>

    </c:if>
    <c:if test="${eo eq 'ok'}">
        <div class="champ">
            <form action="Controleur" method="POST">
                <h3>Veuillez entrer l'année d'occupation</h3>
                <label for="annee">Année</label>
                <input type="text" name="annee" id="annee" value="${param.annee}"/>
                <input type="hidden" name="action" value="validerAnnee"/>
                <input type="hidden" name="numCh"  value="${param.numCh}"/>
                <input type="hidden" name="sec" value="${param.sec}"/>
                <input type="hidden" name="nomPavion" value="${param.nomPavion}"/>
                <input type="hidden" name="idChambre" value="${param.idChambre}"/>
                <input type="submit" value="valider"/>
            </form>
        </div>
    </c:if>
    <c:if test="${!empty listeOccupeChambre}">
        <div class="laTable">
            <table border="1">
                <tr>
                    <th>Numero Etudiant</th> <th>Nom</th><th>premom</th>
                </tr>
                <c:forEach var="oc" items="${listeOccupeChambre}">
                    <tr>
                        <c:forEach var="etu" items="${listeEtu}">  
                            <c:if test="${etu.idEtudiant==oc.idEtudiant}">
                                <td>${etu.numeroEtudiant}</td>
                                <td>${etu.nom}</td>
                                <td>${etu.prenom}</td>
                                <td><c:url var="rese" value="Controleur?action=retirerEtudiant&idChambre=${oc.idChambre}&idPavion=${oc.idPavion}&annee=${param.annee}&nomPavion=${param.nomPavion}&idEtudiant=${etu.idEtudiant}"/><a href="javascript:confSup('${rese}')">-Retirer</a></td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</section>

<%@include file="../WEB-INF/footer.jspf" %> 