<%-- 
    Document   : insertionPloyer
    Created on : 24 mai 2017, 00:02:53
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
       <section>
        <h1>Paiement loyer</h1><hr/>
        <form action="Controleur" method="POST">
            <table>
                <tr>
                    <td><label for="nomPavion">Nom pavion:</label></td>
                    <td>${param.nomPavion}<input type="hidden" name="nomPavion" id="nomPavion" value="${param.nomPavion}"/></td>
                </tr>
                <tr>
                    <td><label for="numchambre">Numero chambre:</label></td>
                    <td>${param.idChambre}<input type="hidden" name="numChambre" id="numchambre" value="${param.idChambre}"/></td>
                </tr>
                 <tr>
                    <td><label for="numEtu">Numero de carte permanant:</label></td>
                    <td>${param.numEtu}<input type="hidden" name="idEtudiant" id="numEtu" value="${param.idEtudiant}"/></td>
                </tr>
                 <tr>
                    <td><label for="anne">Annee:</label></td>
                    <td>${param.annee}<input type="hidden" name="annee" id="annee" value="${param.annee}"/></td>
                </tr>
                <tr>
                    <td><label for="moi">Moi:</label></td>
                    <c:if test="${leMoiAPayer!=1}">
                    <td>${leMoiAPayer}<input type="hidden" name="moi" id="moi" value="${leMoiAPayer}"/></td>
                    </c:if>
                    <c:if test="${leMoiAPayer==1}">
                    <td><input type="text" name="moi" id="moi"/></td>
                    </c:if>
                </tr>
                <tr>
                    <td><label for="prixLoyer">Prix loyer:</label></td>
                    <td><input type="text" name="prixLoyer" id="prixLoyer" required /></td>
                </tr>
                 <tr>
                    <td><input type="submit" name="action" value="payer loyer"/></td>
                </tr>
            </table>
        </form>
    </section>


<%@include file="../WEB-INF/footer.jspf" %> 