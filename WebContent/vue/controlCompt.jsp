<%-- 
    Document   : controlCompt
    Created on : 27 déc. 2017, 10:39:16
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf"%>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
        <h1>Controle Comptable</h1><hr/>
        <c:if test="${! empty employer}">
        <div class="laTable">            
            <table border="1">
                <tr><th>MATRICULE</th><th>NOM</th><th>PRENOM</th><th>PSEUDO</th><th>Mot de passe</th><th>Compte</th><th>PROFILE</th></tr>
                        <c:forEach items="${employer}" var="emp">
                    <tr>
                        <td>${emp.matricule}</td>
                        <td>${emp.nom}</td><td>${emp.prenom}</td>
                        <td>${emp.pseudo}</td>
                        <td>${emp.mot_de_passe}</td>
                        <td><c:choose><c:when test="${emp.disponibilite}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose></td>
                            <td><c:url var="loyer" value="Controleur?action=efCaution&matricule=${emp.matricule}&act=loyer"/><a href="${loyer}">Loyer</a></td>
                            <td><c:url var="caution" value="Controleur?action=efCaution&matricule=${emp.matricule}&act=caution"/><a href="${caution}">Caution</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
        <c:if test="${efCompt eq true}">
        <div class="champ">
            <form action="Controleur" method="POST">
                <label for="annee"><h3>Veuillez entrer l'année de codification:</h3></label>
                <input type="text" name="annee" value="${param.annee}" id="annee" placeholder="Donner l'année..." required/>
                <input type="hidden" value="efComptable" name="action"/>
                <input type="hidden" value="${param.act}" name="act"/>
                <input type="hidden" value="${param.matricule}" name="matricule"/>
                <input type="submit" value="Validez"/>
            </form>
        </div>
        </c:if>
        <c:if test="${!empty listePCaution}">
            <div class="laTable">
                <table border="1">
                    <tr><th>Pavillon</th><th>Numero de chambre</th><th>Date</th><th>Montant</th><th>Code Permenant</th><th>Nom</th></tr>

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
                                </c:if>
                            </c:forEach>
                            
                        </tr>
                    </c:forEach>

                </table><br/><br/>
            </div>     
        </c:if>
          <c:if test="${!empty listLoyer}">
            <div class="laTable">
                <table border="1">
                    <tr><th>Pavillon</th><th>Numero de chambre</th><th>Date</th><th>Montant</th><th>Moi</th><th>Code Permenant</th><th>Nom</th></tr>

                    <c:forEach var="pc" items="${listLoyer}">
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
                            <td>${pc.datePloyer}</td>
                            <td>${pc.prixLoyer}</td>
                            <td>${nonMois[pc.moi]}</td>
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
                            
                        </tr>
                    </c:forEach>

                </table><br/><br/>
            </div>     
        </c:if>
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
</section>
<%@include file="../WEB-INF/footer.jspf" %>
