<%-- 
    Document   : listerPCaution
    Created on : 2 juil. 2017, 23:36:52
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Gestion Caution</h1><hr/>
    <c:if test="${empty pasAnne}">
        <div class="champ">
            <form action="Controleur" method="POST">
                <label for="annee"><h3>Veuillez entrer l'année de codification:</h3></label>
                <input type="text" name="annee" value="${param.annee}" id="annee" placeholder="Donner l'année..." required/>
                <input type="hidden" value="validerAnPCaution" name="action"/>
                <input type="submit" value="Validez"/>
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
    </c:if>
    <c:if test="${sessionScope.profile eq 'Comptable'}">
        <c:if test="${!empty listePCaution}">
            <div class="laTable">
                <table border="1">
                    <tr><th>Pavion</th><th>Numero de chambre</th><th>Date</th><th>Montant</th><th>Code Permenant</th><th>Nom</th></tr>

                    <c:forEach var="pc" items="${listePCaution}">
                        <tr>
                            <td>
                                <c:forEach var="pv" items="${listePavion}">
                                    <c:if test="${pv.idPavion==pc.idPavion}">
                                        ${pv.nomPavion}
                                        <c:set var="nomPv" value="${pv.nomPavion}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${pc.idChambre}</td>
                            <td>${pc.dateCaution}</td>
                            <td>${pc.montant}</td>
                            <c:forEach var="etu" items="${listeEtu}">
                                <c:if test="${etu.idEtudiant==pc.idEtudiant}">
                                    <c:set var="numEtu" value="${pc.idEtudiant}"/>
                                    <c:set var="idPavion" value=""/>
                                    <c:set var="idCh" value=""/>
                                    <td>
                                        ${etu.numeroEtudiant}

                                    </td>
                                    <td>
                                        ${etu.prenom} ${etu.nom}
                                    </td>
                                    <td><c:url var="sCaution" value="Controleur?action=modCaution&idEtu=${pc.idEtudiant}&montant=${pc.montant}&idCh=${pc.idChambre}&idPavion=${pc.idPavion}&numEtu=${etu.numeroEtudiant}&annee=${pc.annee}&nomPv=${nomPv}"/><a href="${sCaution}">Modifier</a></td>
                                </c:if>
                            </c:forEach>
                            <td><c:url var="sCaution" value="Controleur?action=supCaution&idEtudiant=${pc.idEtudiant}&idCh=${pc.idChambre}&idPavion=${pc.idPavion}&annee=${pc.annee}"/><a href="javascript:confSup('${sCaution}')">Supprimer</a></td>
                        </tr>
                    </c:forEach>

                </table><br/><br/>
                <p>Montant total des cautions : ${somme} CFA</p>
            </div>     
        </c:if>
    </c:if>

    <c:if test="${sessionScope.profile eq 'Secretaire'}">
        <c:if test="${!empty listePCauNV}">
            <table border="1">
                <tr><th>Pavion</th><th>Numero de chambre</th><th>Date</th><th>Montant</th><th>Code Permenant</th><th>Nom</th></tr>

                <c:forEach var="pc" items="${listePCauNV}">
                    <tr>
                        <td>
                            <c:forEach var="pv" items="${listePavion}">
                                <c:if test="${pv.idPavion==pc.idPavion}">
                                    ${pv.nomPavion}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${pc.idChambre}</td>
                        <td>${pc.dateCaution}</td>
                        <td>${pc.montant}</td>
                        <c:forEach var="etu" items="${listeEtu}">
                            <c:if test="${etu.idEtudiant==pc.idEtudiant}">
                                <c:set var="numEtu" value="${pc.idEtudiant}"/>
                                <c:set var="idPavion" value=""/>
                                <c:set var="idCh" value=""/>
                                <td>
                                    ${etu.numeroEtudiant}

                                </td>
                                <td>
                                    ${etu.prenom} ${etu.nom}
                                </td>
                            </c:if>
                        </c:forEach>

                        <td><c:url var="vchoi" value="Controleur?action=validerLeChoix&idEtu=${pc.idEtudiant}&numCh=${pc.idChambre}&idPavion=${pc.idPavion}&annee=${pc.annee}"/><a href='${vchoi}'>Validation choix</a></td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${!empty erreurSec}">
            <p class="erreur">${erreurSec}</p>
        </c:if>
    </c:if>
   
</section>

<%@include file="../WEB-INF/footer.jspf" %> 
