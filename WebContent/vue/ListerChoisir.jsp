<%-- 
    Document   : ListerChoisir
    Created on : 25 mai 2017, 18:28:31
    Author     : Side Sarr
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>

    <h1>Liste des choix</h1><hr/>
    <div class="champ">
        <div class="recherche">
            <form action="Controleur" method="POST">
                <input type="text" name="numEtu" id="ch" value="${param.numEtu}" required placeholder="Code permanant"/>
                <input type="hidden" name="action" value="RecherCheChoixParEtu"/>
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
    <c:if test="${!empty choisir and empty rech}">
        <div class="laTable">
            <table border="1">
                <tr><th>ID ETUDIANT</th><th>NOM</th><th>NOM PAVILLON</th><th>ID CHAMBRE</th><th>DATE CHOIX</th></tr>
                        <c:forEach items="${choisir}" var="c">
                    <tr>

                        <c:forEach items="${etudiant}" var="etu">
                            <c:if test="${etu.idEtudiant==c.id_etudiant}" >
                                <td>${etu.numeroEtudiant}</td>
                                <td>${etu.prenom} ${etu.nom}</td>
                                <c:set var="numEtu" value="${etu.numeroEtudiant}" scope="page"/>

                            </c:if>
                        </c:forEach>


                        <c:forEach items="${pavion}" var="pv">
                            <c:if test="${pv.idPavion==c.id_pavillon}" >
                                <td>${pv.nomPavion}</td>
                                <c:set var="nomPv" value="${pv.nomPavion}"/>
                            </c:if>
                        </c:forEach>

                        <td>${c.id_chambre}</td>
                        <td>${c.date}</td>
                        <td><c:url var="pCaution" value="Controleur?action=payerLaCaution&idEtu=${c.id_etudiant}&numEtu=${numEtu}&idPavion=${c.id_pavillon}&idCh=${c.id_chambre}&nomPv=${nomPv}"/><a href='${pCaution}'>Payer Caution</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <c:if test="${!empty choix}" > 
        <div class="laTable">
            <table border="1">
                <tr><th>ID ETUDIANT</th><th>NOM</th><th>NOM PAVILLON</th><th>ID CHAMBRE</th><th>DATE CHOIX</th></tr>
                <tr>

                    <c:forEach items="${etudiant}" var="etu">
                        <c:if test="${etu.idEtudiant==choix.id_etudiant}" >
                            <td>${etu.numeroEtudiant}</td>
                            <td>${etu.prenom} ${etu.nom}</td>
                            <c:set var="numEtu" value="${etu.numeroEtudiant}" scope="page"/>

                        </c:if>
                    </c:forEach>


                    <c:forEach items="${pavion}" var="pv">
                        <c:if test="${pv.idPavion==choix.id_pavillon}" >
                            <td>${pv.nomPavion}</td>
                            <c:set var="nomPv" value="${pv.nomPavion}"/>
                        </c:if>
                    </c:forEach>

                    <td>${choix.id_chambre}</td>
                    <td>${choix.date}</td>
                    <td><c:url var="pCaution" value="Controleur?action=payerLaCaution&idEtu=${choix.id_etudiant}&numEtu=${numEtu}&idPavion=${choix.id_pavillon}&idCh=${choix.id_chambre}"/><a href='${pCaution}'>Payer Caution</a></td>
                </tr>
            </table>
        </div>
    </c:if>
    <c:url var="suptous" value="Controleur?action=supTous"/><a href="javascript:confSup('${suptous}')">Tous supprimer</a>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 