<%-- 
    Document   : InsererEmployer
    Created on : 24 mai 2017, 09:17:27
    Author     : Side Sarr
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Ajout employé</h1><hr/>
    <form action="Controleur" method="POST">
        <table>
            <tr><td><label for="nom">Nom</label></td>
                <td><input id="nom" type="text" name="nom" value="${employer.nom}"  required /></td></tr>
            <tr><td><label for="prenom">Prenom</label></td>
                <td><input id="prenom" type="text" name="prenom" value="${employer.prenom}"  required /></td></tr>
            <c:if test="${!empty employer}"><input  type="hidden" name="matricule" value="${param.matricule}"/></c:if>
            <tr><td><label for="profil">Profile</label></td>
                <td><select  id="profil" name="profile">
                    <c:if test="${!empty employer}">
                        <option value="${param.profile}" selected>${param.profile}</option>   
                    </c:if>
                            <option value="Secretaire">Secretaire</option>
                            <option value="Comptable">Comptable</option>
                            <option value="Chef de residence">Chef de residence</option>
                            <option value="admine">Administrateur</option>
                        </select></td>
            </tr>
            <tr><td><label for="email">Email</label></td>
                <td><input id="email" type="text" name="email" value="${employer.email}"  required /></td></tr>
            <tr>
            <tr><td><label for="telephone">Telephone</label></td>
                <td><input id="telephone" type="text" name="telephone" value="${employer.telephone}"  required /></td></tr>
            
                <c:if test="${empty employer}"><td colspan="2"><input type="submit" name="action" value="Inserer employer" /></td></c:if>
                <c:if test="${!empty employer}"><td colspan="2"><input type="submit" name="action" value="Modifier employer" /></td></c:if>
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