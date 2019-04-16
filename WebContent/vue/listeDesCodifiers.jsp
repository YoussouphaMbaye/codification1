<%-- 
    Document   : listeDesCodifiers
    Created on : 13 juil. 2017, 13:26:51
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>

<section>
    <c:url var="listeC" value="Controleur">
        <c:param name="action" value="listerCodifiers"/>
    </c:url>
    <h1>Les codifiés de l'année</h1>
    <div class="champ">
        <div class="recherche">
            <form action="Controleur" method="POST">
                <input type="text" name="formation" id="ch" value="${param.formation}" placeholder="Formation" required />
                <input type="text" name="niveau" id="ch" value="${param.niveau}" placeholder="niveau" required />
                <input type="hidden" name="action" value="listeCodifiersDuForm"/>
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
    <div class="laTable">
        <c:if test="${!empty listeCodifiers and empty listeCodifiersDuForm}">
            <table border="1">
                <tr><th>NUMERO</th><th>NOM</th><th>PRENOM</th><th>Nom formatiom</th><th>Niveau</th></tr>
                        <c:forEach items="${listeCodifiers}" var="liste">
                    <tr>
                        <td>${liste.numeroEtudiant}</td>
                        <td>${liste.nom}</td>
                        <td>${liste.prenom}</td>
                        <td>${liste.formation}</td>
                        <td>${tabF[liste.niveau]}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>


        <c:if test="${!empty listeCodifiersDuForm}">
            <table border="1">
                <tr><th>NUMERO</th><th>NOM</th><th>PRENOM</th><th>Nom formatiom</th><th>Niveau</th></tr>
                        <c:forEach items="${listeCodifiersDuForm}" var="liste">
                    <tr>
                        <td>${liste.numeroEtudiant}</td>
                        <td>${liste.nom}</td>
                        <td>${liste.prenom}</td>
                        <td>${liste.formation}</td>
                        <td>${tabF[liste.niveau]}</td>
                    </tr>
                </c:forEach>
            </table><br/><br/>
            <a class="LIndependant" href="${listeC}">Toutes la liste</a>
        </c:if>
    </div>
        
</section>
<%@include file="../WEB-INF/footer.jspf" %> 