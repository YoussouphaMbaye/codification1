<%-- 
    Document   : ListerEtudiant
    Created on : 25 mai 2017, 10:18:51
    Author     : Side Sarr
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>La liste des étudiants</h1><hr/>
    <div class="champ">
        <div class="recherche">
            <form action="Controleur" method="POST">
                <input type="text" name="numEtu" id="ch" value="${param.numEtu}" required placeholder="code permanant"/>
                <input type="hidden" name="action" value="RechercherEtu"/>
                <input type="hidden" name="adm" value="${param.adm}"/>
                <input type="submit"  value="Rechercher"/>
            </form>
        </div>
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
    <c:url var="tb" value="Controleur">
        <c:param name="action" value="tbloquer"/>
        <c:param name="adm" value="aBloEtu"/>
    </c:url>
    <c:url var="bc" value="Controleur">
        <c:param name="action" value="bloquercod"/>
        <c:param name="adm" value="aBloEtu"/>
    </c:url>
    <c:url var="ac" value="Controleur">
    </c:url>
    <c:if test="${empty etudiant}">
        <div class="laTable">
            <table border="1">
                <tr><th>NUMERO</th><th>NOM</th><th>PRENOM</th><th>CODIFIER</th><th>COMPTE</th><th></th><th></th></tr>
                        <c:forEach items="${listeEtu}" var="liste">
                    <tr><td>${liste.numeroEtudiant}</td>
                        <td>${liste.nom}</td>
                        <td>${liste.prenom}</td>
                        <td>
                            <c:choose><c:when test="${liste.codifier}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose>
                                </td>
                                <td>
                            <c:choose><c:when test="${liste.disponibilite}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose>
                                </td>
                        <c:url value="Controleur" var="bloquer">
                            <c:param name="action" value="bloquer"/>
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                            <c:param name="adm" value="aBloEtu"/>
                        </c:url>
                        <c:url value="Controleur" var="debloquer">
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                            <c:param name="action" value="debloquer"/>
                            <c:param name="adm" value="aBloEtu"/>
                        </c:url>
                        <c:url value="Controleur" var="codifier">
                            <c:param name="action" value="codifier"/>
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                        </c:url>
                        <c:url value="Controleur" var="decodifier">
                            <c:param name="action" value="decodifier"/>
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                        </c:url>
                        <c:url value="Controleur" var="modifierEtu">
                            <c:param name="action" value="modifierEtu"/>
                            <c:param name="numero" value="${liste.numeroEtudiant}"/>
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                            <c:param name="adm" value="amodEtu"/>
                        </c:url>
                        <c:url value="Controleur" var="detaille">
                            <c:param name="action" value="detailleEtu"/>
                            <c:param name="numero" value="${liste.numeroEtudiant}"/>
                            <c:param name="idetu" value="${liste.idEtudiant}"/>
                        </c:url>
                        <c:url value="Controleur" var="classe">
                            <c:param name="action" value="saclasse"/>
                            <c:param name="idEtu" value="${liste.idEtudiant}"/>
                            <c:param name="numero" value="${liste.numeroEtudiant}"/>
                            <c:param name="adm" value="doClasse"/>
                        </c:url>
                        <c:if test="${param.adm eq 'aBloEtu'}">
                            <td>
                                <c:choose>
                                    <c:when test="${liste.disponibilite}"><a class="w3-btn" href="${bloquer}" >Bloquer</a></c:when>
                                    <c:otherwise><a class="w3-btn" href="${debloquer}" >Debloquer</a></c:otherwise>
                                </c:choose>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope.profile eq 'Secretaire'}">
                            <td>
                                <c:choose>
                                    <c:when test="${!liste.codifier}"><a class="w3-btn" href="${codifier}" >Codifier</a></c:when>
                                    <c:otherwise><a class="w3-btn" href="${decodifier}" >Décodifier</a></c:otherwise>
                                </c:choose>
                            </td>
                        </c:if>
                        <c:if test="${param.adm eq 'doClasse'}">
                            <td>
                                &nbsp;&nbsp;<a href="${classe}" >Donner sa classe</a>
                            </td>
                        </c:if>
                        <c:if test="${param.adm eq 'amodEtu'}">
                            <td>
                                <a href="${modifierEtu}">Modifier</a>
                            </td>
                        </c:if>
                        <td>
                            <a href="${detaille}">Détail</a>
                        </td>
                        <c:if test="${param.adm eq 'asupEtu'}">
                            <td>
                                <c:url var="supEtudiant" value="Controleur?action=supprimerEtu&idetu=${liste.idEtudiant}&adm=asupEtu"/>
                                <a href="javascript:confSup('${supEtudiant}')">Supprimer</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${!empty param.numEtu and !empty etudiant}">
        <div class="laTable">
            <table border="1">
                <tr><th>NUMERO</th><th>NOM</th><th>PRENOM</th><th>CODIFIER</th><th>COMPTE</th><th></th><th></th></tr>

                <tr><td>${etudiant.numeroEtudiant}</td>
                    <td>${etudiant.nom}</td>
                    <td>${etudiant.prenom}</td>
                    <td>
                        <c:choose><c:when test="${etudiant.codifier}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose>
                            </td>
                            <td>
                        <c:choose><c:when test="${etudiant.disponibilite}">OUI</c:when><c:otherwise>NON</c:otherwise></c:choose>
                            </td>
                    <c:url value="Controleur" var="bloquer">
                        <c:param name="action" value="bloquer"/>
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="adm" value="aBloEtu"/>
                    </c:url>
                    <c:url value="Controleur" var="detaille">
                        <c:param name="action" value="detailleEtu"/>
                        <c:param name="numero" value="${etudiant.numeroEtudiant}"/>
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                    </c:url>
                    <c:url value="Controleur" var="debloquer">
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="action" value="debloquer"/>
                        <c:param name="adm" value="aBloEtu"/>
                    </c:url>
                    <c:url value="Controleur" var="codifier">
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="action" value="codifier"/>
                    </c:url>
                    <c:url value="Controleur" var="decodifier">
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="action" value="decodifier"/>
                    </c:url>
                    <c:url value="Controleur" var="suprimerEtu">
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="action" value="supprimerEtu"/>
                    </c:url>  
                    <c:url value="Controleur" var="modifierEtu">
                        <c:param name="action" value="modifierEtu"/>
                        <c:param name="numero" value="${etudiant.numeroEtudiant}"/>
                        <c:param name="idetu" value="${etudiant.idEtudiant}"/>
                        <c:param name="adm" value="amodEtu"/>
                    </c:url>
                    <c:url value="Controleur" var="classe">
                        <c:param name="action" value="saclasse"/>
                        <c:param name="idEtu" value="${etudiant.idEtudiant}"/>
                        <c:param name="numero" value="${etudiant.numeroEtudiant}"/>
                        <c:param name="adm" value="doClasse"/>
                    </c:url>
                    <c:if test="${param.adm eq 'aBloEtu'}">
                        <td>
                            <c:choose>
                                <c:when test="${etudiant.disponibilite}"><a class="w3-btn" href="${bloquer}" >Bloquer</a></c:when>
                                <c:otherwise><a class="w3-btn" href="${debloquer}" >Debloquer</a></c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.profile eq 'Secretaire'}">
                        <td>
                            <c:choose>
                                <c:when test="${!etudiant.codifier}"><a class="w3-btn" href="${codifier}" >Codifier</a></c:when>
                                <c:otherwise><a class="w3-btn" href="${decodifier}" >Decodifier</a></c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
                         <td>
                            <a href="${detaille}">Déttaille</a>
                        </td>
                    <c:if test="${param.adm eq 'doClasse'}">
                        <td>
                            &nbsp;&nbsp;<a class="w3-btn" href="${classe}" >Donner sa classe</a>
                        </td>
                    </c:if>
                    <c:if test="${param.adm eq 'amodEtu'}">
                        <td>
                            <a href="${modifierEtu}">Modifier</a>
                        </td>
                    </c:if>
                    <c:if test="${param.adm eq 'asupEtu'}">
                        <td>
                            <a href="javascript:confSup('${suprimerEtu}')" >Supprimer</a>
                        </td>
                    </c:if>
                </tr>
            </table>
        </div>
    </c:if>
    <br/>
    <c:url value="Controleur" var="ToutDec">
        <c:param name="action" value="toutDec"/>
        <c:param name="adm" value="chef"/>
    </c:url>
    <c:if test="${sessionScope.profile eq 'Chef de residence'}">
        <a href="javascript:confDec('${ToutDec}')" class="LIndependant" >Tout décodifier</a>
    </c:if>

    <c:url value="Controleur" var="dbeA">
        <c:param name="action" value="dEca"/>
        <c:param name="adm" value="aBloEtu"/>
    </c:url>
    <c:if test="${sessionScope.profile eq 'admine' and param.adm eq 'aBloEtu'}">
        <a href='${tb}' class="LIndependant" >Tout bloquer</a>
        <a href="${bc}" class="LIndependant">Debloquer codifier</a> 
        <a href="${dbeA}" class="LIndependant">Activer la prioritée</a> 
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 
