<%-- 
    Document   : ListerEmployer
    Created on : 30 mai 2017, 11:39:23
    Author     : Side Sarr
--%>

<%@include file="../WEB-INF/entete.jspf"%>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Liste des employer</h1>
    <div class="champ">
        <div class="recherche">
            <form action="Controleur" method="POST">
                <input type="text" name="matricule" id="ch" value="${param.matricule}" placeholder="Matricule" required/>
                <input type="hidden" name="action" value="rechercherEmp"/>
                <input type="hidden" name="adm" value="${param.adm}"/>
                <input type="submit"  value="Rechercher"/>
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
    </div>

    <c:if test="${! empty employer}">
        <div class="laTable">            
            <table border="1">
                <tr><th>MATRICULE</th><th>NOM</th><th>PRENOM</th><th>PSEUDO</th><th>Mot de passe</th><th>EMAIL</th><th>TELEPHONE</th><th>Compte</th><th>PROFILE</th></tr>
                        <c:forEach items="${employer}" var="emp">
                    <tr>
                        <td>${emp.matricule}</td>
                        <td>${emp.nom}</td><td>${emp.prenom}</td>
                        <td>${emp.pseudo}</td>
                        <td>${emp.mot_de_passe}</td>
                        <td>${emp.email}</td>
                        <td>${emp.telephone}</td>
                        <td><c:choose><c:when test="${emp.disponibilite}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose></td>
                        <td>${emp.profile}</td>
                        <c:if test="${param.adm eq 'aSup'}">
                            <td><c:url var="supemp" value="Controleur?action=supEmp&matricule=${emp.matricule}&adm=aSup"/><a href="javascript:confSup('${supemp}')">Supprimer</a></td>
                        </c:if>
                        <c:if test="${param.adm eq 'aMod'}">
                            <td><c:url var="modemp" value="Controleur?action=modEmp&matricule=${emp.matricule}&profile=${emp.profile}"/><a href="${modemp}">modifier</a></td>
                        </c:if>
                        <c:if test="${param.adm eq 'aBlo'}">
                            <td><c:choose>
                                    <c:when test="${emp.disponibilite}"><c:url var="bloqu" value="Controleur?action=bloquerEmp&matricule=${emp.matricule}&adm=aBlo"/><a href="${bloqu}">Bloquer</a></c:when>
                                    <c:otherwise><c:url var="debloqu" value="Controleur?action=debloquerEmp&matricule=${emp.matricule}&adm=aBlo"/><a href="${debloqu}">Debloquer</a></c:otherwise>
                                </c:choose></td>
                            </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${! empty leEmployer}">
        <div class="laTable">
            <table border="1">
                <tr><th>MATRICULE</th><th>NOM</th><th>PRENOM</th><th>PSEUDO</th><th>Mot de passe</th><th>EMAIL</th><th>TELEPHONE</th><th>Compte</th><th>PROFILE</th></tr>
                <tr>
                    <td>${leEmployer.matricule}</td>
                    <td>${leEmployer.nom}</td><td>${leEmployer.prenom}</td>
                    <td>${leEmployer.pseudo}</td>
                    <td>${leEmployer.mot_de_passe}</td>
                    <td>${leEmployer.email}</td>
                    <td>${leEmployer.telephone}</td>
                    <td><c:choose><c:when test="${leEmployer.disponibilite}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose></td>
                    <td>${leEmployer.profile}</td>
                    <c:if test="${param.adm eq 'aSup'}">
                        <td><c:url var="supemp" value="Controleur?action=supEmp&matricule=${leEmployer.matricule}&adm=aSup"/><a href="javascript:confSup('${supemp}')">Supprimer</a></td>
                    </c:if>
                    <c:if test="${param.adm eq 'aMod'}">
                        <td><c:url var="modemp" value="Controleur?action=modEmp&matricule=${leEmployer.matricule}&profile=${emp.profile}"/><a href="${modemp}">modifier</a></td>
                    </c:if>
                    <c:if test="${param.adm eq 'aBlo'}">
                        <td><c:choose>
                                <c:when test="${leEmployer.disponibilite}"><c:url var="bloqu" value="Controleur?action=bloquerEmp&matricule=${leEmployer.matricule}&adm=aBlo"/><a href="${bloqu}">Bloquer</a></c:when>
                                <c:otherwise><c:url var="debloqu" value="Controleur?action=debloquerEmp&matricule=${leEmployer.matricule}&adm=aBlo"/><a href="${debloqu}">Debloquer</a></c:otherwise>
                            </c:choose></td>
                        </c:if>
                </tr>
            </table>
        </div>
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 