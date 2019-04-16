<%-- 
    Document   : insertionCaution
    Created on : 2 juil. 2017, 15:24:36
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Paiement caution</h1><hr/>
    <form action="Controleur" method="POST">
        <table>
            <tr>
                <td><label for="nomPavion">NomPavion:</label></td>
                <td>${param.nomPv}<input type="hidden" name="nomPavion" value="${param.idPavion}"/></td>
            </tr>
            <tr>
                <td><label for="numChambre">Numero de chambre:</label></td>
                <td>
                    ${param.idCh}<input type="hidden" name="numChambre" id="numchambre" value="${param.idCh}"/>
                </td>
            </tr>
            </tr>
            <tr>
                <td><label for="numEtu">Code permenant:</label></td>
                <td>
                    ${param.numEtu}<input type="hidden" name="numEtud" id="numEtu" value="${param.idEtu}"/>
                </td>
            </tr>
            <tr>
                <td><label for="annee">Annee:</label></td>
                <td>
                    <input type="text" name="annee" id="annee" value="${param.annee}"/>
                </td>
            </tr>
            <tr>
                <td><label for="Mcaution">Montant Caution:</label></td>
                <td>
                    <input type="text" name="Mcaution" id="Mcaution" value="${param.montant}" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <c:if test="${!empty param.montant}">
                        <input type="submit" value="Modifier caution" name="action" id="Mcaution"/>
                    </c:if>
                    <c:if test="${empty param.montant}">
                        <input type="submit" value="Payer caution" name="action" id="Mcaution"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form>
    <c:if test="${!empty reussi}">
        <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
        </c:if>
        <c:if test="${!empty erreur}">
        <p class="erreur">${erreur}</p>
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 
