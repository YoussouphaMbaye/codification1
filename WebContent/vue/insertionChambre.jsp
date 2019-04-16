<%-- 
    Document   : insertionChambre
    Created on : 23 mai 2017, 15:19:44
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
    <form action="Controleur" method="POST">
        <table>
            <tr>
                <td><label for="nomPavion">Nom pavillon:</label></td>
                <td>
                    <select id="nomPavion" name="nomPavion">
                        <c:forEach var="pv" items="${listePavion}">  
                            <option>${pv.nomPavion}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="numchambre">Numero chambre:</label></td>
                <td><input type="text" name="numChambre" id="numchambre"/></td>
            </tr>
            <tr>
                <td><input type="submit" name="action" value="inserez chambre"/></td>
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
