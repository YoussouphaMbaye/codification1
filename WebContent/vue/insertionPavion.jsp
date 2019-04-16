<%-- 
    Document   : insertionPavion
    Created on : 17 mai 2017, 17:15:40
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Ajout pavillon</h1><hr/>
    <form action="Controleur" method="POST">
        <table>
            <tr>
                <td><label for="nomPavion">Nom pavillon:</label></td>
                <td><input type="text" name="nomPavion" id="nomPavion" required /></td>
                <td><label for="nbchambre">Nombre de chambre :</label></td>
                <td><input type="text" name="nbchambre" id="nbchambre" required /></td>
            </tr>
            <tr>
                <td> <input type="submit" name="action" value="inserez pavion"/></td>
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